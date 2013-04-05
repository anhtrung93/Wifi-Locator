package com.example;

import android.net.wifi.ScanResult;
import android.app.Activity;

/**
 * @author anhtrung93
 * 
 *         Class WiFiSignature Overview: 
 *         - Keep information of a wifi access points including 
 *         	+ its basicServiceSetIdentifier 
 *         	+ its receivedSignalStrenth
 *         	+ its serviceSetIdentifier
 * 
 */
public class WifiSignature extends Activity implements
		Comparable<WifiSignature> {
	private String basicServiceSetIdentifier; // Address of the access point
	private int receivedSignalStrength; // Signal strength
	private String serviceSetIdentifier; // Name of the access point

	/**
	 * Constructor: WifiSignature(ScanResult)
	 * Input: a ScanResult object
	 * Output: this object(with fields are initialized)
	 * Description: initialize this object 
	 * - with a ScanResult object taken by scanning
	 */
	public WifiSignature(ScanResult scanResult) {
		this.serviceSetIdentifier = scanResult.SSID;
		this.basicServiceSetIdentifier = scanResult.BSSID;
		this.receivedSignalStrength = scanResult.level;
	}
	
	/**
	 * Constructor: WifiSignature(String, int, String)
	 * Input: 2 strings + an integer
	 * - First String: in the form of "XX:XX:XX:XX:XX:XX"
	 * 	 	MAC address of the access point (X is an alpha-number)
	 * - The integer: the current signal strength measured in dBm
	 * - Second String: the name of the access point
	 * Output: this object(with fields are initialized)
	 * Note: this object method is used to test!!!
	 */
	public WifiSignature(String initBasicServiceSetIdentifier,
			int initReceivedSignalStrength, String initServiceSetIdentifier) {
		this.basicServiceSetIdentifier = initBasicServiceSetIdentifier;
		this.receivedSignalStrength = initReceivedSignalStrength;
		this.serviceSetIdentifier = initServiceSetIdentifier;
	}

	/**
	 * Method: compareTo(WifiSignature)
	 * Input: this object + another WifiSignature object
	 * Output: an integer
	 * - is the lexicographic distance between
	 * - the basicServiceSetIdentifier strings of both
	 * - this object and the other WifiSignature object
	 */
	@Override
	public int compareTo(WifiSignature otherWifiSignature) {
		return this.getBasicServiceSetIdentifier().compareTo(
				otherWifiSignature.getBasicServiceSetIdentifier());
	}

	/**
	 * Method: getBasicServiceSetIdentifier()
	 * Input: this object
	 * Output: a string 
	 * - in the form of a six-byte MAC address 
	 * - "XX:XX:XX:XX:XX:XX" with 'X' is an alpha-number
	 */
	public String getBasicServiceSetIdentifier() {
		return this.basicServiceSetIdentifier;
	}

	/**
	 * Method: getReceivedSignalStrength()
	 * Input: this object
	 * Output: an integer 
	 * - shows the signal strength
	 * - in range from -100 to ???
	 */
	public int getReceivedSignalStrength() {
		return this.receivedSignalStrength;
	}

	/**
	 * Method: toString()
	 * Input: this object
	 * Output: a string with 3 lines
	 * - First line: the name
	 * - Second line: the MAC address
	 * - Third line: the signal strength
	 */
	@Override
	public String toString() {
		String strTemp = "SSID: " + this.serviceSetIdentifier + "\nBSSID: "
				+ this.basicServiceSetIdentifier + "\nRSS: "
				+ this.receivedSignalStrength + "\n";
		return strTemp;
	}
}
