/**
 * 
 */
package com.example;

import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 * 
 * Class 'WiFiSignature' Overview:
 * Describe information about a detected access point
 *
 */
public class WiFiSignature {
	/********************Fields*****************************/
	private String BSSID;		//Address of the access point
	private int RSS;			//Signal Level in dBm
	
	/********************Methods*****************************/
	public WiFiSignature(ScanResult scanResult){
		this.BSSID = scanResult.BSSID;
		this.RSS = scanResult.level;
	}
	
	//
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
