package com.example;

import com.example.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class WifiLocator extends Activity {

	WifiManager wifi;
	StringBuilder status = new StringBuilder();

	TextView textStatus;
	TextView WifiState;
	//List<ScanResult> wifiList;
	Fingerprint fingerprint;
	
	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		WifiState = (TextView)findViewById(R.id.wifistate);
	    ToggleButton OnOff = (ToggleButton) findViewById(R.id.onoff);
	    Button Scan = (Button)findViewById(R.id.scan);
	    this.registerReceiver(this.WifiStateChangedReceiver,
	               new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));

		// Setup UI
		textStatus = (TextView) findViewById(R.id.textStatus);

		// Setup WiFi
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		//Switch WiFi        
	    OnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	            if (isChecked) {
	            	wifi = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
	 			    wifi.setWifiEnabled(false);
	            } else {
	            	wifi = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
				    wifi.setWifiEnabled(true);
	            }
	        }
	    });

		// Start Scanning WiFi		
		status = new StringBuilder();
		Scan.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {	   
				// TODO Auto-generated method stub	   
				status.delete(0, status.length());
				if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED){
					if (wifi.startScan() == false){
						status.append("Scan failed!!!") ;
					} else {
						status.append("Scanning...");
					}
				} else{
					status.append("WiFi is off, turn it on now !!! \n");
				}
				textStatus.setText(status);
			}
		});

		//List WiFi
		status = new StringBuilder();
		registerReceiver(new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent){
				status.delete(0, status.length());
				wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
				fingerprint = new Fingerprint(wifi.getScanResults());
				//status.delete(0, status.length());
				if (fingerprint.getSize() == 0){
					status.append("No wifi connection");
				} else {
					status.append("List of available WiFi: \n\n");
					status.append(fingerprint.toString());
				}
				textStatus.setText(status);
			}
		}, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	}
	
	// WiFi Status
	private BroadcastReceiver WifiStateChangedReceiver
    	= new BroadcastReceiver(){

		@Override	
	    public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	   
		    int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
		    		WifiManager.WIFI_STATE_UNKNOWN);
		   
		    switch(extraWifiState){
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
		    }
		}
    };
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    }
}
