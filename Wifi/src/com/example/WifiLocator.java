package com.example;

import com.example.share.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Timer;
import java.util.TimerTask;
import com.example.R;
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
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * 
 * @author braveWolf
 * 
 *         WifiLocator class is the main activity class on Android device. It
 *         shows the user interface with some buttons and text areas:
 *         <ul>
 *         <li>OnOffButton: button to turn on and turn off wifi</li>
 *         <li>AutoScanButton: button to start scanning automatically</li>
 *         <li>scanButton: button to start a scan</li>
 *         <li>savedLabelButton: button to assign a label to a Fingerprint and
 *         add it to the database</li>
 *         <li></li>
 *         <li>wifiState: text area shows the wifi status
 *         (Enabled/Disabled/Enabling/Disabling)</li>
 *         <li>showWifiArea: text area shows the list of Wifi Access Point</li>
 *         <li></li>
 *         <li>timer: to set the start automatically after a while</li>
 *         <li>scanOnClick:</li>
 *         <li></li>
 *         <li>generalFingerprint</li>
 *         </ul>
 */
public class WifiLocator extends Activity {
	private Button scanButton;
	private CheckBox autoScanButton;
	private ToggleButton onOffButton;
	private Button savedLabelButton;

	private TextView wifiState;
	private TextView showWifiArea;

	Timer timer;
	Boolean scanOnClick;

	public static Fingerprint generalFingerprint;

	/**
	 * Called when the activity is first created. Shows user interface by
	 * linking xml parts with variables. Registers receivers. Initializes button
	 * listeners.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.initialize();

		this.setUpInterface();
		// Register Receivers && Listeners
		this.registerReceiver(this.WifiStateChangedReceiver, new IntentFilter(
				WifiManager.WIFI_STATE_CHANGED_ACTION));
		this.registerReceiver(this.WifiScanAvailableReceiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		this.setUpButtonListeners();
	}

	/**
	 * 
	 */
	private void initialize() {
		scanOnClick = false;
		generalFingerprint = new Fingerprint();
		WifiManager wifi = (WifiManager) getBaseContext().getSystemService(
				Context.WIFI_SERVICE);
		wifi.setWifiEnabled(true);
	}

	/**
	 * Links xml parts to variables. Allows using variables (button and text
	 * area variables)to control the user interface
	 * 
	 */
	private void setUpInterface() {
		setContentView(R.layout.main);
		onOffButton = (ToggleButton) findViewById(R.id.onoff);
		wifiState = (TextView) findViewById(R.id.wifistate);
		scanButton = (Button) findViewById(R.id.scan);
		showWifiArea = (TextView) findViewById(R.id.textStatus);
		autoScanButton = (CheckBox) findViewById(R.id.autoscan);
		savedLabelButton = (Button) findViewById(R.id.saveButton);
	}

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
	 * 
	 */
	private BroadcastReceiver WifiStateChangedReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
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
				wifiState.setText("Error: Unknown extraWifiState value!!!!");
				break;
			}
		}
	};

	/**
	 * Receives when a wifi scan finishes. Prints out the list of Wifi.
	 */
	private BroadcastReceiver WifiScanAvailableReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (autoScanButton.isChecked() || scanOnClick) {
				StringBuilder status = new StringBuilder();
				WifiManager wifi = (WifiManager) context
						.getSystemService(Context.WIFI_SERVICE);
				try {
					Fingerprint fingerprint = new Fingerprint(
							wifi.getScanResults());

					if (fingerprint.getSize() == 0) {
						status.append("No WiFi connection!!!");
					} else {
						if (autoScanButton.isChecked()) {
							scanButton.setEnabled(false);
						} else {
							scanButton.setEnabled(true);
						}
						generalFingerprint = fingerprint;
						status.append("List of available WiFi: \n\n");
						status.append(generalFingerprint.toString());
					}
					showWifiArea.setText(status);
				} catch (Exception exception) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					exception.printStackTrace(pw);
					showWifiArea.setText(sw.toString());
				}
				scanOnClick = false;

			}

		}

	};

	/**
	 * Sets up Button Listeners including listeners for saveLabelButton,
	 * onOffButton, scanButton and autoscan.
	 * <p>
	 * <ul>
	 * <li>When saveLabelButton is clicked, this method will start the
	 * SavedLabelActivity allowing user to give the Fingerpint a name.</li>
	 * 
	 * <li>When onOffButton is clicked, this method will turn on and turn off
	 * wifi of the device.</li>
	 * 
	 * <li>When scanButton is clicked, a new wifi scan will be started.</li>
	 */
	private void setUpButtonListeners() {
		// savedLabel Listener -> go to saved label activity
		savedLabelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						SavedLabelActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});

		// OnOff Listener -> enable/disable WiFi
		onOffButton
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						WifiManager wifi;

						if (isChecked) {
							wifi = (WifiManager) getBaseContext()
									.getSystemService(Context.WIFI_SERVICE);
							wifi.setWifiEnabled(false);
							scanOnClick = false;
						} else {
							wifi = (WifiManager) getBaseContext()
									.getSystemService(Context.WIFI_SERVICE);
							wifi.setWifiEnabled(true);
							scanOnClick = false;
						}
					}
				});

		// Scan Listener -> Start scanning
		scanButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				StringBuilder status = new StringBuilder();
				WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

				if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
					if (wifi.startScan() == false) {
						status.append("Scan failed!!!");
					} else {
						scanButton.setEnabled(false);
						status.append("Scanning...");
					}
				} else {
					status.append("WiFi is off, turn it on now !!! \n");
				}
				showWifiArea.setText(status);
				scanOnClick = true;
			}
		});

		// Auto Listener -> Start auto scanning
		autoScanButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (autoScanButton.isChecked()) {
					timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
							wifi.startScan();
						}
					}, 0, 1000);
					scanButton.setEnabled(false);
				} else {
					timer.cancel();
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
		super.onDestroy();
		this.unregisterReceiver(this.WifiScanAvailableReceiver);
		this.unregisterReceiver(this.WifiStateChangedReceiver);
	}

}