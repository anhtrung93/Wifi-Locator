package com.example;

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
 */
public class WifiLocator extends Activity {
	Button ScanButton;
	CheckBox AutoScanButton;
	ToggleButton OnOffButton;
	Button savedLabelButton;

	TextView WifiState;
	TextView textStatus;

	Timer timer;
	Boolean scanOnClick;

	public static Fingerprint generalFingerprint;

	/**
	 * Called when the activity is first created.
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

	private void initialize() {
		scanOnClick = false;
		generalFingerprint = new Fingerprint();
		WifiManager wifi = (WifiManager) getBaseContext().getSystemService(
				Context.WIFI_SERVICE);
		wifi.setWifiEnabled(true);

	}

	private void setUpInterface() {
		setContentView(R.layout.main);
		OnOffButton = (ToggleButton) findViewById(R.id.onoff);
		WifiState = (TextView) findViewById(R.id.wifistate);
		ScanButton = (Button) findViewById(R.id.scan);
		textStatus = (TextView) findViewById(R.id.textStatus);
		AutoScanButton = (CheckBox) findViewById(R.id.autoscan);
		savedLabelButton = (Button) findViewById(R.id.saveButton);
	}

	// WiFi Status
	private BroadcastReceiver WifiStateChangedReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			int extraWifiState = intent.getIntExtra(
					WifiManager.EXTRA_WIFI_STATE,
					WifiManager.WIFI_STATE_UNKNOWN);

			switch (extraWifiState) {
			case WifiManager.WIFI_STATE_DISABLED:
				WifiState.setText("WIFI IS DISABLED");
				break;
			case WifiManager.WIFI_STATE_DISABLING:
				WifiState.setText("WIFI IS DISABLING");
				break;
			case WifiManager.WIFI_STATE_ENABLED:
				WifiState.setText("WIFI IS ENABLED");
				break;
			case WifiManager.WIFI_STATE_ENABLING:
				WifiState.setText("WIFI IS ENABLING");
				break;
			case WifiManager.WIFI_STATE_UNKNOWN:
				WifiState.setText("WIFI IS UNKNOWN");
				break;
			default:
				WifiState.setText("Error: Unknown extraWifiState value!!!!");
				break;
			}
		}
	};

	// List WiFi when Scan finishes
	private BroadcastReceiver WifiScanAvailableReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (AutoScanButton.isChecked() || scanOnClick) {
				StringBuilder status = new StringBuilder();
				WifiManager wifi = (WifiManager) context
						.getSystemService(Context.WIFI_SERVICE);
				try {
					Fingerprint fingerprint = new Fingerprint(
							wifi.getScanResults());

					if (fingerprint.getSize() == 0) {
						status.append("No WiFi connection!!!");
					} else {
						if (AutoScanButton.isChecked()) {
							ScanButton.setEnabled(false);
						} else {
							ScanButton.setEnabled(true);
						}
						generalFingerprint = fingerprint;
						status.append("List of available WiFi: \n\n");
						status.append(generalFingerprint.toString());
					}
					textStatus.setText(status);
				} catch (Exception exception) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					exception.printStackTrace(pw);
					textStatus.setText(sw.toString());
				}

				scanOnClick = false;

			}

		}

	};

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
		OnOffButton
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
		ScanButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				StringBuilder status = new StringBuilder();
				WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

				if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
					if (wifi.startScan() == false) {
						status.append("Scan failed!!!");
					} else {
						ScanButton.setEnabled(false);
						status.append("Scanning...");
					}
				} else {
					status.append("WiFi is off, turn it on now !!! \n");
				}
				textStatus.setText(status);
				scanOnClick = true;
			}
		});

		// Auto Listener -> Start auto scanning

		AutoScanButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (AutoScanButton.isChecked()) {
					timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
							wifi.startScan();
						}
					}, 0, 1000);
					ScanButton.setEnabled(false);
				} else {
					timer.cancel();
					ScanButton.setEnabled(true);
				}
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(this.WifiScanAvailableReceiver);
		this.unregisterReceiver(this.WifiStateChangedReceiver);
	}

}