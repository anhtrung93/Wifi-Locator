/**
 * 
 */
package com.example;

import android.net.wifi.ScanResult;

/**
 * @author trunganhdinh
 *
 */
public class WiFiSignature {
	private String BSSID;
	private int RSS;
	
	public WiFiSignature(ScanResult scanResult){
		this.BSSID = scanResult.BSSID;
		this.RSS = scanResult.level;
	}
	
	public String getBSSID(){
		return this.BSSID;
	}
	
	public int getRSS(){
		return this.RSS;
	}
	
	public String toString(){
		return "BSSID: " + this.BSSID + "\nRSS: " + this.RSS + "\n";
	}
	

}
