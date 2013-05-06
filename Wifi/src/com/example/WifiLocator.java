package com.example;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.share.Fingerprint;

/**
 * 
 * @author bravewolf
 * 
 *         WifiLocator class is the main activity class on Android device. It
 *         shows the user interface with some buttons and text areas:
 *         <ul>
 *         <li>wifiManager: The wifi manager</li>
 *         <li>database: The saved list of Fingerprint</li>
 *         <li>currentFingerprint: The scanned fingerprint</li>
 *         <li></li>
 *         <li>switchWifiToggleButton: Toggle button to turn on and turn off
 *         wifi</li>
 *         <li>autoScanCheckBox: Check box to start scanning fingerprint
 *         automatically</li>
 *         <li>scanButton: Button to start a fingerprint scan</li>
 *         <li>addNewLabelButton: Button to assign a label (typed in
 *         showLocationArea) to a Fingerprint and add it to the database</li>
 *         <li></li>
 *         <li>wifiState: Text area shows the wifi status
 *         (Enabled/Disabled/Enabling/Disabling)</li>
 *         <li>showFingerprintArea: Text area shows the list of Wifi Access
 *         Point</li>
 *         <li>showLocationArea: Text area shows the current location. Users can
 *         change the location by typing in this area and press
 *         addNewLabelButton</li>
 *         <li></li>
 *         <li>scanButtonIsClicked: A boolean variable to check if "scanButton"
 *         is clicked or not</li>
 *         </ul>
 */
public class WifiLocator extends Activity {
	private final static int MAX_STRING = 100;

	private transient WifiManager wifiManager;
	private final transient Database database = Database.getInstance();
	private transient Fingerprint currentFingerprint;

	private transient Button scanButton;
	private transient CheckBox autoScanCheckBox;
	private transient ToggleButton switchWifiToggleButton;
	private transient Button addNewLabelButton;

	private transient TextView wifiState;
	private transient TextView showFingerprintArea;
	private transient EditText showLocationArea;

	transient Boolean scanButtonIsClicked;

	/**
	 * Receives the changes of the wifi state and prints it on the screen. There
	 * are different types of stauts:
	 * <p>
	 * <ul>
	 * <li>WIFI ENABLED</li>
	 * <li>WIFI ENABLING</li>
	 * <li>WIFI DISABLED</li>
	 * <li>WIFI DISABLING</li>
	 * <li>WIFI STATE UNKNOWN</li>
	 * </ul>
	 */
	private final transient BroadcastReceiver WifiStateChangedReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(final Context context, final Intent intent) {
			int extraWifiState = intent.getIntExtra(
					WifiManager.EXTRA_WIFI_STATE,
					WifiManager.WIFI_STATE_UNKNOWN);

			switch (extraWifiState) {
			case WifiManager.WIFI_STATE_DISABLED:
				wifiState.setText("WIFI IS DISABLED");
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				wifiState.setText("WIFI IS DISABLING");
				break;
			case WifiManager.WIFI_STATE_ENABLED:
				wifiState.setText("WIFI IS ENABLED");
				break;
			case WifiManager.WIFI_STATE_ENABLING:
				wifiState.setText("WIFI IS ENABLING");
				break;
			case WifiManager.WIFI_STATE_UNKNOWN:
				wifiState.setText("WIFI STATE IS UNKNOWN");
				break;
			default:
				wifiState.setText("ERROR!!!!");
				break;
			}
		}
	};

	/**
	 * Receives when a wifi scan finishes. Prints out the list of Wifi. Finds a
	 * suitable labeled Fingerprint in the database and shows the label in
	 * showLocationArea. Starts a new Scan if the autoScanCheckBox is checked.
	 */
	private final transient BroadcastReceiver WifiScanAvailableReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(final Context context, final Intent intent) {
			// runs when autoScanCheckBox is checked or scanButton is clicked
			if (autoScanCheckBox.isChecked() || scanButtonIsClicked) {
				StringBuilder wifiListString = new StringBuilder(MAX_STRING);
				try {
					// Gets scan results
					currentFingerprint = new Fingerprint(
							wifiManager.getScanResults());

					// Shows the list of wifiSignature
					wifiListString.append("List of available WiFi: \n\n");
					wifiListString.append(currentFingerprint.toString());
					showFingerprintArea.setText(wifiListString);

					// Gets the label from the database and prints it out
					if (currentFingerprint != null) {
						showLocationArea.setText(database.find(
								currentFingerprint).getLabel());
					}

					// Starts a new scan if autoScanCheckBox is checked and
					// allows
					// to add new label if it is not automatic scan
					if (autoScanCheckBox.isChecked()) {
						wifiManager.startScan();
						scanButton.setEnabled(false);
						addNewLabelButton.setEnabled(false);
					} else {
						scanButton.setEnabled(true);
						addNewLabelButton.setEnabled(true);
					}
				} catch (Exception exception) {
					StringWriter strWriter = new StringWriter();
					exception.printStackTrace(new PrintWriter(strWriter));
					showFingerprintArea.setText(strWriter.toString());
				}
				scanButtonIsClicked = false;
			}

		}

	};

	/**
	 * Called when the activity is first created. Shows user interface by
	 * linking xml parts with variables. Registers receivers. Initializes button
	 * listeners for addNewLabelButton, switchWifiToggleButton, scanButton and
	 * autoScanCheckBox.
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.initialize();
		// Registers Receivers
		this.registerReceiver(this.WifiStateChangedReceiver, new IntentFilter(
				WifiManager.WIFI_STATE_CHANGED_ACTION));
		this.registerReceiver(this.WifiScanAvailableReceiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		// Sets Listeners
		this.setAddNewLabelButtonListener();
		this.setWifiButtonListener();
		this.setScanButtonListener();
		this.setAutoScanButtonListener();
	}

	/**
	 * Initializes some instance variables by linking xml parts to them. Allows
	 * using variables (button and text area variables)to control the user
	 * interface.
	 */
	private void initialize() {
		setContentView(R.layout.main);

		showLocationArea = (EditText) findViewById(R.id.addLabelText);
		wifiState = (TextView) findViewById(R.id.wifiState);
		showFingerprintArea = (TextView) findViewById(R.id.textStatus);
		switchWifiToggleButton = (ToggleButton) findViewById(R.id.toggleButtonSwitchWifi);
		scanButton = (Button) findViewById(R.id.buttonScan);
		autoScanCheckBox = (CheckBox) findViewById(R.id.autoScan);
		addNewLabelButton = (Button) findViewById(R.id.buttonSave);

		scanButtonIsClicked = false;
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(true);
		addNewLabelButton.setEnabled(false);
	}

	/**
	 * When scanButton is clicked, this method will turn on/off wifi of the
	 * device.
	 */
	private void setWifiButtonListener() {
		switchWifiToggleButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(
							final CompoundButton buttonView,
							final boolean isChecked) {
						if (isChecked) {
							wifiManager.setWifiEnabled(false);
							scanButtonIsClicked = false;
						} else {
							wifiManager.setWifiEnabled(true);
							scanButtonIsClicked = false;
						}
					}
				});
	}

	/**
	 * When addNewLabelButton is clicked, this method will send the
	 * currentFingerprint with the newLabel(on showLocationArea) to the
	 * database.
	 */
	private void setAddNewLabelButtonListener() {
		addNewLabelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				String newLabel = showLocationArea.getText().toString();
				currentFingerprint.setLabel(newLabel);
				showLocationArea.setText(currentFingerprint.getLabel());
				try {
					database.add(currentFingerprint);
				} catch (Exception exception) {
					StringWriter strWriter = new StringWriter();
					exception.printStackTrace(new PrintWriter(strWriter));
					showFingerprintArea.setText(strWriter.toString());
				}
			}
		});
	}

	/**
	 * When scanButton is clicked, a new wifi scan will be started.
	 */
	private void setScanButtonListener() {
		scanButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(final View view) {
				StringBuilder status = new StringBuilder(MAX_STRING);
				if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
					if (!wifiManager.startScan()) {
						status.append("Scan failed!!!");
					} else {
						scanButton.setEnabled(false);
						status.append("Scanning...");
					}
				} else {
					status.append("WiFi is off, turn it on now !!! \n");
				}
				showFingerprintArea.setText(status);
				scanButtonIsClicked = true;
			}
		});
	}

	/**
	 * When the autoScanCheckBox is checked, the program will automatically scan
	 * for wifiList and show the current location automatically.
	 */
	private void setAutoScanButtonListener() {
		autoScanCheckBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View view) {
				if (autoScanCheckBox.isChecked()) {
					wifiManager.startScan();
				} else {
					scanButton.setEnabled(true);
				}
			}
		});
	}

	/**
	 * Called when the application is destroyed. Unregisters receivers
	 */
	@Override
	public void onDestroy() {
		this.unregisterReceiver(this.WifiScanAvailableReceiver);
		this.unregisterReceiver(this.WifiStateChangedReceiver);
		super.onDestroy();
	}
}