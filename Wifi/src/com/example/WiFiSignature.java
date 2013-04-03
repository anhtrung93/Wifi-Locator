package com.example;

import android.net.wifi.ScanResult;
import android.app.Activity;

/**
 * @author anhtrung93
 * 
 * Class 'WiFiSignature' Overview:
 * Describe information about a detected access point
 *
 */
<<<<<<< HEAD
public class WiFiSignature extends Activity{
=======
public class WiFiSignature implements Comparable<WiFiSignature> {
>>>>>>> ad0deb87f71fa9036666b06107ff0766f2dd7ec4
	/********************Fields*****************************/
	private String BSSID;		//Address of the access point
	private int RSS;			//Signal Level in dBm
	private String SSID;	
	
	/********************Private Methods********************/
	/********************Public Methods*********************/

	public WiFiSignature(String BSSID, int RSS){
		this.BSSID = BSSID;
		this.RSS = RSS;
	}
	
	public WiFiSignature(ScanResult scanResult){
		this.SSID = scanResult.SSID;
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
		return "SSID: " + this.SSID + "\nBSSID: " + this.BSSID + "\nRSS: " + this.RSS + "\n";
	}
	
	/**
	 * Method: compareTo()
	 * just to implement Comaprable interface
	 */
	 
	public int compareTo(WiFiSignature another) {
        	return this.getBSSID().compareTo(another.getBSSID());
	}
}
