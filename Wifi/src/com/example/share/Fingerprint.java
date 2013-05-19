package com.example.share;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 * 
 *         A Fingerprint object encapsulates the state information of all signal
 *         from all wifi access points(WifiSignature objects) at a point of
 *         time. Fingerprint class allows application to reorder the list of
 *         delete some unnecessary wifi signal from the list. Users also can
 *         assign a location label to a Fingerprint object This state
 *         information includes:
 *         <ul>
 *         <li>a list of WifiSignature objects</li>
 *         <li>a label of location</li>
 *         </ul>
 * 
 */

public class Fingerprint implements Serializable {
	final static long serialVersionUID = 1L;

	private WifiSignature[] wifiList; // the list of
										// WifiSignature objects
	private String label; // the label assigned by users

	/**
	 * Starts an empty object without any Fingerprint objects in the wifiList
	 * and the label is set to be "Unknown location"
	 */
	public Fingerprint() {
		this.wifiList = null;
		this.label = "Unknown location";
	}

	/**
	 * Constructs the object by using a scan result. This.wifiList is
	 * initialized by using data from a list of ScanResult objects and then is
	 * sorted and filtered. The location label of this object is initialized
	 * with "Unknown location" label.
	 * 
	 * @param listOfScanResults
	 *            list of all wifi signal received after the device makes a scan
	 */
	public Fingerprint(final List<ScanResult> listOfScanResults){
		int numOfScanResults = listOfScanResults.size();
		this.wifiList = new WifiSignature[numOfScanResults];
		for (int idWifiList = 0; idWifiList < numOfScanResults; idWifiList++) {
			this.wifiList[idWifiList] = new WifiSignature(
					listOfScanResults.get(idWifiList));
		}
		Arrays.sort(this.wifiList);
		this.label = "Unknown location";
	}

	/**
	 * Constructs this object with an initial list of WifiSignature objects. The
	 * label is initialized with "Unknown location" label.
	 * <p>
	 * <b>Note: this method is used to test!!!</b>
	 * 
	 * @param initWifiList
	 *            a list of WifiSignature objects
	 */
	public Fingerprint(final WifiSignature[] initWifiList) {
		this.wifiList = initWifiList.clone();
		this.label = "Unknown location";
	}

	/**
	 * Constructs this object with an initial list of WifiSignature objects and
	 * an initial location label.
	 * <p>
	 * <b>Note: this method is used to test!!!</b>
	 * 
	 * @param initWifiList
	 *            a list of WifiSignature objects
	 * @param initLocationLabel
	 *            a initial location label
	 */
	public Fingerprint(final WifiSignature[] initWifiList,
			final String initLocationLabel) {
		this.wifiList = initWifiList.clone();
		this.label = initLocationLabel;
	}

	/**
	 * Adds a new location label.
	 * <p>
	 * <b>Alert: if there exists an older label, it will be replaced!!!</b>
	 * 
	 * @param newLabel
	 *            a new location label
	 */
	public void setLabel(final String newLabel) {
		this.label = newLabel;
	}

	/**
	 * return the location label of this fingerprint
	 * 
	 * @return this locationLabel
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Sets a WifiSignature list for this.wifiList
	 * 
	 * @param newWifiList
	 *            new WifiSignature list
	 */
	public void setWifiList(final WifiSignature[] newWifiList) {
		this.wifiList = newWifiList.clone();
	}

	/**
	 * Gets the WifiSignature list
	 * 
	 * @return the list of WifiSignatures of this Fingerprint
	 */
	public WifiSignature[] getWifiList() {
		return this.wifiList.clone();
	}

	/**
	 * Differs this Fingerprint object with another Fingerprint object. Returns
	 * a float which shows the difference between two Fingerprint objects. The
	 * larger the float is, the more different two objects are.
	 * 
	 * @param otherFingerprint
	 *            other Fingerprint object which will be compared with the
	 *            current object
	 * @return a float show the relative difference between this Fingerprint
	 *         object the otherFingerprint object
	 */
	public float differFrom(final Fingerprint otherFingerprint) {
		WifiSignature[] thisWifiList = this.getWifiList();
		WifiSignature[] otherWifiList = otherFingerprint.getWifiList();
		int thisListSize = thisWifiList.length;
		int otherListSize = otherWifiList.length;

		int count = 0; // Number of matched WifiSignature
		int sumOfDifference = 0;
		// Sum of square of each difference of each matched WifiSignature's RSS
		int idThisList = 0;
		int idOtherList = 0;
		while (idThisList < thisListSize && idOtherList < otherListSize) {
			WifiSignature thisSignature = thisWifiList[idThisList];
			WifiSignature anotherSignature = otherWifiList[idOtherList];
			int compareResult = thisSignature.compareTo(anotherSignature);
			if (compareResult == 0) {
				// only compare the level of the same wifi address
				count++;
				int difference = thisSignature.getSignalStrength()
						- anotherSignature.getSignalStrength();
				sumOfDifference += difference * difference;
				idThisList++;
				idOtherList++;
			} else if (compareResult < 0) {
				idThisList++;
			} else {
				idOtherList++;
			}
		}

		float proportion = (float) count
				/ Math.min(thisListSize, otherListSize);
		float averageDifference = (float) sumOfDifference / count;
		float result;
		if (proportion < 0.5) {
			result = Constant.MAXIMUM_DIFFERENCE + 1;
		} else {
			result = averageDifference / proportion;
		}
		return result;
	}

	/**
	 * Returns this Fingerprint object in the form of string.
	 * 
	 * @return a string with many group of lines. Each group is a substring form
	 *         of a WifiSignature objects in the list
	 */
	@Override
	public String toString() {
		StringBuffer resultString = new StringBuffer();
		for (int i = 0; i < this.wifiList.length; i++) {
			resultString.append(this.wifiList[i].toString());
			resultString.append('\n');
		}
		return resultString.toString();
	}
}