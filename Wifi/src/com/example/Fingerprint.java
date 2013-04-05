package com.example;

import java.util.List;
import java.util.Arrays;

import android.app.Activity;
import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 * 
 *         Class Fingerprint overview:
 *         - Keep a list of all wifiSignature which a device scan gets
 *         - and a label of its location
 * 
 */
public class Fingerprint extends Activity {
	private int size;	//the number of WifiSignature objects
	private WifiSignature[] wifiList; //the list of WifiSignature objects
	String locationLabel; //the label assigned by users

	/**
	 * Constructor: Fingerprint(List<ScanResult>)
	 * Input: a list of ScanResult objects
	 * Output: this object with
	 * - this.size and this.wifiList are initialized 
	 *  + by using data from List<ScanResult>
	 * - this.wifiList is sorted and filtered (watch sort() and filter())
	 * - this.locationLabel is initialized with "Unknown location"
	 */
	public Fingerprint(List<ScanResult> listOfScanResults) {
		int size = listOfScanResults.size();
		
		this.wifiList = new WifiSignature[size];
		for (int idWifiList = 0; idWifiList < size; idWifiList++) {
			this.wifiList[idWifiList] = new WifiSignature(
					listOfScanResults.get(idWifiList));
		}
		this.sort();
		this.filter();
		
		this.locationLabel = "Unknown location";
	}

	/**
	 * Constructor: Fingerprint(WifiSignature[])
	 * Input: an array of WifiSignature objects
	 * Output: this object with
	 * - this.size and this.wifiList are initialized 
	 *  + by using data from the array WifiSignature[]
	 * Note: this method is used to test!!!
	*/
	public Fingerprint(WifiSignature[] initWifiList) {
		super();
		this.wifiList = initWifiList;
		this.size = initWifiList.length;
	}

	/**
	 * Constructor: Fingerprint(WifiSignature[], String)
	 * Input: an array of WifiSignature objects + String
	 * - the String is the label assigned to this object
	 * Output: this object with
	 * - this.size and this.wifiList are initialized 
	 *  + by using data from the array WifiSignature[]
	 * - this.locationLabel is initialized with the String
	 * Note: this method is used to test!!!
	*/
	public Fingerprint(WifiSignature[] initWifiList, String initLocationLabel) {
		super();
		this.wifiList = initWifiList;
		this.size = initWifiList.length;
		this.locationLabel = initLocationLabel;
	}

	/**
	 * Method: addLabel(String)
	 * Input: a String 
	 * - the new label of the location
	 * Output: this (with this.label == newLabel)
	 * Alert: if there is an old label, it will be replaced!!!
	 */
	public void addLabel(String newLabel) {
		this.locationLabel = newLabel;
	}

	/**
	 * Method: differFrom(Fingerprint)
	 * Input: this object + another Fingerprint object
	 * Output: a float
	 * - shows the difference between two Fingerprint objects
	 * - the larger the float is, the more different two objects are
	 */
	
	// Pause refactoring right here!!!!
	public float differFrom(Fingerprint otherFingerprint) {
		WifiSignature[] list1 = this.getwifiList();
		WifiSignature[] list2 = otherFingerprint.getwifiList();
		int size1 = this.getSize();
		int size2 = otherFingerprint.getSize();
		int count = 0;
		int diff = 0;

		int i = 0, j = 0;
		while (i < size1 && j < size2) {
			WifiSignature s1 = list1[i];
			WifiSignature s2 = list2[i];
			int d = s1.compareTo(s2);
			if (d == 0) {
				count++;
				int sd = s1.getReceivedSignalStrength()
						- s2.getReceivedSignalStrength();
				diff += sd * sd;
			} else if (d < 0) {
				i++;
			} else {
				j++;
			}
		}

		return diff / ((float) count / (size1 + size2));
	}

	/**
	 * Method: filter() Input: this object Output: this object with the array of
	 * wifiSignature was filtered - by deleting irrelevant of unimportant
	 * wifiSignature object
	 */
	private void filter() {
	}

	/**
	 * Method: getSize() Input: this Output: the number of the wifiSignature
	 */
	public int getSize() {
		return this.wifiList.length;
	}

	/**
	 * Method: getwifiList(); Output: the Wifi list
	 */
	public WifiSignature[] getwifiList() {
		return this.wifiList;
	}

	/**
	 * Method: sort() Input: this object Output: this object with the array of
	 * wifiSignature - sorted by their BSSID values
	 */
	private void sort() {
		Arrays.sort(this.wifiList);
	}

	/**
	 * Method: toString()
	 * Input: this
	 * Output: a string with a group of lines -
	 * Lines show the information about - a wifiSignature
	 */
	@Override
	public String toString() {
		String tempString = "";
		for (int i = 0; i < this.size; i++) {
			tempString += this.wifiList[i].toString() + "\n";
		}
		return tempString;
	}
}
