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
	
	/********************Private Methods********************/
	/********************Public Methods*********************/
	public WiFiSignature(ScanResult scanResult){
		this.BSSID = scanResult.BSSID;
		this.RSS = scanResult.level;
	}
	
	/**
	 * Method: getBSSID()
	 * Input: this
	 * Output: String in the form of 
	 * - a six-byte MAC address: XX:XX:XX:XX:XX:XX
	 * - 'X : is an alpha-number
	 */
	public String getBSSID(){
		return this.BSSID;
	}
	
	/**
	 * Method: getRSS()
	 * Input: this
	 * Output: the signal strength integer
	 * -  in range from -100 to ???
	 */
	public int getRSS(){
		return this.RSS;
	}
	
	/**
	 * Method: toString()
	 * Input: this
	 * Output: a string with 2 lines
	 * - First line: the BSSID string
	 * - Second line: the signal strength
	 */
	public String toString(){
		return "BSSID: " + this.BSSID + "\nRSS: " + this.RSS + "\n";
	}	
}
