package com.example;

import com.example.share.Fingerprint;
import com.example.R;
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

/**
 * 
 * @author braveWolf
 * 
 *         WifiLocator class is the main activity class on Android device. It
 *         shows the user interface with some buttons and text areas:
 *         <ul>
 *         <li>wifi: the wifi manager</li>
 *         <li>database: the saved list of Fingerprint</li>
 *         <li>currentFingerprint: the fingerprint scanned</li>
 *         <li></li>
 *         <li>wifiButton: button to turn on and turn off wifi</li>
 *         <li>autoScanButton: button to start scanning automatically</li>
 *         <li>scanButton: button to start a scan</li>
 *         <li>addNewLabelButton: button to assign a label (typed in
 *         showLocationArea) to a Fingerprint and add it to the database</li>
 *         <li></li>
 *         <li>wifiState: text area shows the wifi status
 *         (Enabled/Disabled/Enabling/Disabling)</li>
 *         <li>showFingerprintArea: text area shows the list of Wifi Access
 *         Point</li>
 *         <li>showLocationArea: text area shows the current location. Users can
 *         change the location by typing in this area and press
 *         addNewLabelButton</li>
 *         <li></li>
 *         <li>scanOnClick:</li>
 *         </ul>
 */
public class WifiLocator extends Activity {
	private final static int MAX_STRING = 100;

	private transient WifiManager wifi;
	private final transient Database database = Database.getInstance();
	private transient Fingerprint currentFingerprint;

	private transient Button scanButton;
	private transient CheckBox autoScanButton;
	private transient ToggleButton wifiButton;
	private transient Button addNewLabelButton;

	private transient TextView wifiState;
	private transient TextView showFingerprintArea;
	private transient EditText showLocationArea;

	transient Boolean scanOnClick;

	/**
	 * Receives the changes of the wifi state and prints it on the screen. There
	 * are different types of stauts:
	 * <p>
	 * <ul>
	 * <li>WIFI ENABLED</li>
	 * <li>WIFI ENABLING</li>
	 * <li>WIFI DISABLED</li>
	 * <li>WIFI DISABLING</li>
	 * <li>WIFI UNKNOWN</li>
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
				wifiState.setText("WIFI IS UNKNOWN");
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
	 * showLocationArea. Starts a new Scan if the autoScanButton is checked.
	 */
	private final transient BroadcastReceiver WifiScanAvailableReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(final Context context, final Intent intent) {
			// runs when autoScanButton is checked or scanButton is clicked
			if (autoScanButton.isChecked() || scanOnClick) {
				StringBuilder wifiListString = new StringBuilder(MAX_STRING);
				try {
					// Gets scan results
					currentFingerprint = new Fingerprint(wifi.getScanResults());

					// Shows the list of wifiSignature
					wifiListString.append("List of available WiFi: \n\n");
					wifiListString.append(currentFingerprint.toString());
					showFingerprintArea.setText(wifiListString);

					// Gets the label from the database and prints it out
					if (currentFingerprint != null){
						showLocationArea.setText(database.find(currentFingerprint)
							.getLabel());
					}

					// Starts a new scan if autoScanButton is checked and allows
					// to add new label if it is not automatic scan
					if (autoScanButton.isChecked()) {
						wifi.startScan();
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
				scanOnClick = false;
			}

		}

	};

	/**
	 * Called when the activity is first created. Shows user interface by
	 * linking xml parts with variables. Registers receivers. Initializes button
	 * listeners for addNewLabelButton, wifiButton, scanButton and
	 * autoScanButton.
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
		wifiState = (TextView) findViewById(R.id.wifistate);
		showFingerprintArea = (TextView) findViewById(R.id.textStatus);
		wifiButton = (ToggleButton) findViewById(R.id.onoff);
		scanButton = (Button) findViewById(R.id.scan);
		autoScanButton = (CheckBox) findViewById(R.id.autoscan);
		addNewLabelButton = (Button) findViewById(R.id.saveButton);

		scanOnClick = false;
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		wifi.setWifiEnabled(true);
		addNewLabelButton.setEnabled(false);
	}

	/**
	 * When wifiButton is clicked, this method will turn on/off wifi of the
	 * device.
	 */
	private void setWifiButtonListener() {
		wifiButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(
							final CompoundButton buttonView,
							final boolean isChecked) {
						if (isChecked) {
							wifi.setWifiEnabled(false);
							scanOnClick = false;
						} else {
							wifi.setWifiEnabled(true);
							scanOnClick = false;
						}
					}
				});
	}

	/**
	 * When addNewButton is clicked, this method will send the
	 * currentFingerprint with the newLabel(on showLocationArea) to the
	 * database.
	 */
	private void setAddNewLabelButtonListener() {
		addNewLabelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				String newLabel = showLocationArea.getText().toString();
				currentFingerprint.addLabel(newLabel);
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
				if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
					if (!wifi.startScan()) {
						status.append("Scan failed!!!");
					} else {
						scanButton.setEnabled(false);
						status.append("Scanning...");
					}
				} else {
					status.append("WiFi is off, turn it on now !!! \n");
				}
				showFingerprintArea.setText(status);
				scanOnClick = true;
			}
		});
	}

	/**
	 * When the autoScanButton is checked, the program will automatically scan
	 * for wifiList and show the current location automatically.
	 */
	private void setAutoScanButtonListener() {
		autoScanButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View view) {
				if (autoScanButton.isChecked()) {
					wifi.startScan();
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