package com.example;

import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 * 
 *         A WifiSignature object encapsulates the state information of a wifi
 *         access points. This state information includes:
 *         <ul>
 *         <li>its Basic Service Set Identifier (BSSID)</li>
 *         <li>its Received Signal Strength (RSS)</li>
 *         <li>its Service Set Identifier (SSID)</li>
 *         </ul>
 */
public class WifiSignature implements Comparable<WifiSignature> {
	private String basicServiceSetIdentifier; // Address of the access point
	private int receivedSignalStrength; // Signal strength
	private String serviceSetIdentifier; // Name of the access point

	/**
	 * Constructs the start state of the WifiSignature object by using the
	 * result from a scan of the device. All the fields of WifiSignature object
	 * (including BSSID, RSS and SSID) are initialized by the respective fields
	 * in scanResult object.
	 * 
	 * @param scanResult
	 *            the result from a scan the device has done
	 */
	public WifiSignature(ScanResult scanResult) {
		this.serviceSetIdentifier = scanResult.SSID;
		this.basicServiceSetIdentifier = scanResult.BSSID;
		this.receivedSignalStrength = scanResult.level;
	}

	/**
	 * Constructs the start state of the WifiSignature object by setting all the
	 * fields of the class with some information from the parameters.
	 * <p>
	 * <b> Note: this object method is used to test!!! </b>
	 * 
	 * @param initBasicServiceSetIdentifier
	 *            the MAC address of the access point in the form of
	 *            "XX:XX:XX:XX:XX:XX" (X is an alpha-number)
	 * @param initReceivedSignalStrength
	 *            the signal strength measured in dBm
	 * @param initServiceSetIdentifier
	 *            the name of the access point
	 */
	public WifiSignature(String initBasicServiceSetIdentifier,
			int initReceivedSignalStrength, String initServiceSetIdentifier) {
		this.basicServiceSetIdentifier = initBasicServiceSetIdentifier;
		this.receivedSignalStrength = initReceivedSignalStrength;
		this.serviceSetIdentifier = initServiceSetIdentifier;
	}

	/**
	 * Compares this object with other WifiSignature object by comparing their
	 * basicServiceSetIdentifier string.
	 * 
	 * @param otherWifiSignature
	 *            another WifiSignature object which is used to compare with
	 *            this object
	 * @return the value which is the lexicographic distance between the
	 *         basicServiceSetIdentifier strings of both this object and the
	 *         other WifiSignature object
	 */
	@Override
	public int compareTo(WifiSignature otherWifiSignature) {
		return this.getBasicServiceSetIdentifier().compareTo(
				otherWifiSignature.getBasicServiceSetIdentifier());
	}

	/**
	 * Gets the Basic Service Set Identifier (BSSID or the MAC address of the
	 * access point).
	 * 
	 * @return the BSSID string in the form of "XX:XX:XX:XX:XX:XX" with 'X' is
	 *         an alpha-number
	 */
	public String getBasicServiceSetIdentifier() {
		return this.basicServiceSetIdentifier;
	}

	/**
	 * Gets the Received Signal Strength (RSS).
	 * 
	 * @return the RSS integer in range from -100 to 0 (measured in dBm)
	 */
	public int getReceivedSignalStrength() {
		return this.receivedSignalStrength;
	}

	/**
	 * Returns this WifiSignature object in the form of string.
	 * 
	 * @return a string with 3 lines:
	 *         <ul>
	 *         <li>First line: the name </li>
	 *         <li>Second line: the MAC address </li>
	 *         <li>Third line: the signal strength </li>
	 *         </ul>
	 */
	@Override
	public String toString() {
		String strTemp = "SSID: " + this.serviceSetIdentifier + "\nBSSID: "
				+ this.basicServiceSetIdentifier + "\nRSS: "
				+ this.receivedSignalStrength + "\n";
		return strTemp;
	}
}
