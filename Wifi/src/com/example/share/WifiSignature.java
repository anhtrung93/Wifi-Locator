package com.example.share;

import java.io.Serializable;

import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 * 
 *         A WifiSignature object encapsulates the state information of a wifi
 *         access points. This state information includes:
 *         <ul>
 *         <li>physicalAddress: its Basic Service Set Identifier (BSSID)</li>
 *         <li>signalStrength: its Received Signal Strength (RSS)</li>
 *         <li>networkName: its Service Set Identifier (SSID)</li>
 *         </ul>
 */
public class WifiSignature implements Comparable<WifiSignature>, Serializable {
	final static long serialVersionUID = 1L;

	private String physicalAddress;
	private int signalStrength;
	private final transient String networkName;

	/**
	 * Constructs the start state of the WifiSignature object by using the
	 * result from a scan of the device. All the fields of WifiSignature object
	 * (including BSSID, RSS and SSID) are initialized by assigning with the
	 * respective fields in scanResult object.
	 * 
	 * @param scanResult
	 *            the result from a scan the device has done
	 */
	public WifiSignature(final ScanResult scanResult) {
		this.networkName = scanResult.SSID;
		this.physicalAddress = scanResult.BSSID;
		this.signalStrength = scanResult.level;
	}

	/**
	 * Constructs the start state of the WifiSignature object by setting all the
	 * fields of the class with some information from the parameters.
	 * <p>
	 * <b> Note: this object method is used to test!!! </b>
	 * 
	 * @param initPhysicalAddress
	 *            the MAC address of the access point in the form of
	 *            "XX:XX:XX:XX:XX:XX" (X is an alpha-number)
	 * @param initSignalStrength
	 *            the signal strength measured in dBm
	 * @param initNetworkName
	 *            the name of the access point
	 */
	public WifiSignature(final String initPhysicalAddress,
			final int initSignalStrength, final String initNetworkName) {
		this.physicalAddress = initPhysicalAddress;
		this.signalStrength = initSignalStrength;
		this.networkName = initNetworkName;
	}

	/**
	 * Constructs the start state of the WifiSignature object by setting all the
	 * fields of the class with some information from the parameters.
	 * <p>
	 * <b> Note: this object method is used to test!!! </b>
	 * 
	 * @param initPhysicalAddress
	 *            the MAC address of the access point in the form of
	 *            "XX:XX:XX:XX:XX:XX" (X is an alpha-number)
	 * @param initSignalStrength
	 *            the signal strength measured in dB
	 */
	public WifiSignature(final String initPhysicalAddress,
			final int initSignalStrength) {
		this.physicalAddress = initPhysicalAddress;
		this.signalStrength = initSignalStrength;
		this.networkName = null;
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
	public int compareTo(final WifiSignature otherWifiSignature) {
		return this.getPhysicalAddress().compareTo(
				otherWifiSignature.getPhysicalAddress());
	}

	/**
	 * Sets the Basic Service Set Identifier (BSSID or the MAC address of the
	 * access point).
	 * 
	 * @param newPhysicalAddress
	 *            the BSSID string in the form of "XX:XX:XX:XX:XX:XX" with 'X'
	 *            is an alpha-number
	 */
	public void setPhysicalAddress(final String newPhysicalAddress) {
		this.physicalAddress = newPhysicalAddress;
	}

	/**
	 * Gets the Basic Service Set Identifier (BSSID or the MAC address of the
	 * access point).
	 * 
	 * @return the BSSID string in the form of "XX:XX:XX:XX:XX:XX" with 'X' is
	 *         an alpha-number
	 */
	public String getPhysicalAddress() {
		return this.physicalAddress;
	}

	/**
	 * Sets a new RSS for the WifiSignature object.</br> <b>Alert: this is just
	 * for test</b>
	 * <p>
	 * 
	 * @param newSignalStrength
	 *            a new RSS value
	 */
	public void setSignalStrength(final int newSignalStrength) {
		this.signalStrength = newSignalStrength;
	}

	/**
	 * Gets the Signal Strength (RSS).
	 * 
	 * @return the RSS integer in range from -100 to 0 (measured in dBm)
	 */
	public int getSignalStrength() {
		return this.signalStrength;
	}

	/**
	 * Returns this WifiSignature object in the form of string.
	 * 
	 * @return a string with 3 lines:
	 *         <ul>
	 *         <li>First line: the name</li>
	 *         <li>Second line: the MAC address</li>
	 *         <li>Third line: the signal strength</li>
	 *         </ul>
	 */
	@Override
	public String toString() {
		String strTemp = "SSID: " + this.networkName + "\nBSSID: "
				+ this.physicalAddress + "\nRSS: " + this.signalStrength + "\n";
		return strTemp;
	}
}