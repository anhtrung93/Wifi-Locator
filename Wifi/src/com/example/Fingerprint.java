package com.example;

import java.util.List;
import java.util.Arrays;

import android.app.Activity;
import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 * 
 *         Class Fingerprint overview: Objects of this class keep a list of all
 *         wifiSignature which a device scan gets and a label of its location
 * 
 */
public class Fingerprint extends Activity {
	private WifiSignature[] wifiList; //the list of WifiSignature objects
	String locationLabel; //the label assigned by users

	/**
	 * Constructor: Fingerprint(List<ScanResult>)
	 * Input: a list of ScanResult objects
	 * Output: this object with
	 * - this.wifiList are initialized 
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
	 * - this.wifiList are initialized 
	 *  + by using data from the array WifiSignature[]
	 * Note: this method is used to test!!!
	*/
	public Fingerprint(WifiSignature[] initWifiList) {
		super();
		this.wifiList = initWifiList;
	}

	/**
	 * Constructor: Fingerprint(WifiSignature[], String)
	 * Input: an array of WifiSignature objects + String
	 * - the String is the label assigned to this object
	 * Output: this object with
	 * - this.wifiList are initialized 
	 *  + by using data from the array WifiSignature[]
	 * - this.locationLabel is initialized with the String
	 * Note: this method is used to test!!!
	*/
	public Fingerprint(WifiSignature[] initWifiList,
			String initLocationLabel) {
		super();
		this.wifiList = initWifiList;
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
	//This method needs to be changed in the future release
	public float differFrom(Fingerprint anotherFingerprint) {
		WifiSignature[] thisWifiList = this.getWifiList();
		WifiSignature[] anotherWifiList = anotherFingerprint.getwifiList();
		int thisSize = this.getSize();
		int anotherSize = otherFingerprint.getSize();
		
		int count = 0; // Number of matched WifiSignature
		int difference = 0;
		// Sum of square of each difference of each matched WifiSignature's RSS

		for (int i=0, j=0; i < thisSize && j < anotherSize; ) {
			WifiSignature thisSignature = thisWifiList[i];
			WifiSignature anotherSignature = anotherWifiList[i];
			int compareResult  = thisSignature.compareTo(anotherSignature);
			if (compareResult == 0) {
				count++;
				intsquareOfDifference = thisSignature.getReceivedSignalStrength()
						- anotherSignature.getReceivedSignalStrength();
				difference += squareOfDifference * squareOfDifference;
			} else if (compareResult < 0) {
				i++;
			} else {
				j++;
			}
		}

		return ((float)difference / count)
		    / ((float) count / (thisSize + anotherSize));
	}

	/**
	 * Method: filter()
	 * Input: this object
	 * Output: this object with 
	 * - unimportant WifiSignature objects are deleted
	 */
	private void filter() {
	}

	/**
	 * Method: getSize()
	 * Input: this object
	 * Output: an integer
	 * - the number of the WifiSignature objects in the list
	 */
	public int getSize() {
		return this.wifiList.length;
	}

	/**
	 * Method: getWifiList()
	 * Input: this object
	 * Output: the wifi list
	 */
	public WifiSignature[] getWifiList() {
		return this.wifiList;
	}

	/**
	 * Method: sort()
	 * Input: this object
	 * Output: this object with
	 * - the array of WifiSignature objects
	 * - sorted by their BSSID values
	 */
	private void sort() {
		Arrays.sort(this.wifiList);
	}

	/**
	 * Method: toString()
	 * Input: this object
	 * Output: a string including 
	 * - groups of lines 
	 * - each groups shows the information about 
	 * - a WifiSignature object
	 */
	@Override
	public String toString() {
		String tempString = "";
		for (int i = 0; i < this.wifiList.length; i++) {
			tempString += this.wifiList[i].toString() + "\n";
		}
		return tempString;
	}
}
