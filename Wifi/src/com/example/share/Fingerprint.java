package com.example.share;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;
import android.net.wifi.ScanResult;

/**
 * @author anhtrung93 + bvuong93
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

	private WifiSignature[] wifiList; // the list of WifiSignature objects
	private String locationLabel; // the label assigned by users

	/**
	 * Starts an empty object without any Fingerprint objects in the wifiList
	 * and the label is set to be "Unknown location"
	 */
	public Fingerprint() {
		wifiList = null;
		locationLabel = "Unknown location";
	}

	/**
	 * Constructs the object by using a scan result. This.wifiList is
	 * initialized by using data from a list of ScanResult objects and then is
	 * sorted and filtered. The location label of this object is initialized
	 * with "Unknown location" label.
	 * 
	 * @param listOfScanResults
	 *            list of all wifi signal received after the device makes a scan
	 * @throws Exception
	 *             when listOfScanResults does not exist
	 */
	public Fingerprint(List<ScanResult> listOfScanResults) throws Exception {
		if (listOfScanResults == null) {
			throw new Exception(
					"There is no listOfScanResults to init Fingerprint");
		}
		int size = listOfScanResults.size();
		this.wifiList = new WifiSignature[size];
		for (int idWifiList = 0; idWifiList < size; idWifiList++) {
			this.wifiList[idWifiList] = new WifiSignature(
					listOfScanResults.get(idWifiList));
		}

		//this.filter();
		this.sort();		

		this.locationLabel = "Unknown location";
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
	public Fingerprint(WifiSignature[] initWifiList) {
		this.wifiList = initWifiList;
		this.locationLabel = "Unknown location";
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
	public Fingerprint(WifiSignature[] initWifiList, String initLocationLabel) {
		this.wifiList = initWifiList;
		this.locationLabel = initLocationLabel;
	}

	/**
	 * Adds a new location label.
	 * <p>
	 * <b>Alert: if there exists an older label, it will be replaced!!!</b>
	 * 
	 * @param newLabel
	 *            a new location label
	 */
	public void addLabel(String newLabel) {
		this.locationLabel = newLabel;
	}

	/**
	 * return the location label of this fingerprint
	 * 
	 * @return this locationLabel
	 */
	public String getLabel() {
		return this.locationLabel;
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
	// This method needs to be changed in the future release
	public float differFrom(Fingerprint otherFingerprint) {
		WifiSignature[] thisWifiList = this.getWifiList();
		WifiSignature[] anotherWifiList = otherFingerprint.getWifiList();
		int thisSize = this.getSize();
		int anotherSize = otherFingerprint.getSize();

		int count = 0; // Number of matched WifiSignature
		int sumOfDifference = 0;
		// Sum of square of each difference of each matched WifiSignature's RSS

		// Note: Rename i,j !!!!!!!!!11
		for (int i = 0, j = 0; i < thisSize && j < anotherSize;) {
			WifiSignature thisSignature = thisWifiList[i];
			WifiSignature anotherSignature = anotherWifiList[j];
			int compareResult = thisSignature.compareTo(anotherSignature);
			if (compareResult == 0) {
				count++;
				int difference = thisSignature
						.getReceivedSignalStrength()
						- anotherSignature.getReceivedSignalStrength();
				int squareOfDifference = difference * difference;
				sumOfDifference += squareOfDifference;
				i++;
				j++;
			} else if (compareResult < 0) {
				i++;
			} else {
				j++;
			}
		}
		
		float proportion = (float) count / Math.min(thisSize, anotherSize);
		float averageDifference = (float) sumOfDifference / count;
		
		if (proportion < 0.5)
			return Constant.MAXIMUM_DIFFERENCE + 1;
		else
			return averageDifference / (proportion);
	}

	
	public float __differFrom(Fingerprint otherFingerprint) {
		WifiSignature[] thisWifiList = this.getWifiList();
		WifiSignature[] anotherWifiList = otherFingerprint.getWifiList();
		int thisSize = this.getSize();
		int anotherSize = otherFingerprint.getSize();

		// int count = 0; // Number of matched WifiSignature		
		
		
		ArrayList<WifiSignature> thisList = new ArrayList<WifiSignature> ();
		ArrayList<WifiSignature> anotherList = new ArrayList<WifiSignature> ();
		
		
		// Sum of square of each difference of each matched WifiSignature's RSS

		// Note: Rename i,j !!!!!!!!!11
		
		
		for (int i = 0, j = 0; i < thisSize && j < anotherSize; ) {
			WifiSignature thisSignature = thisWifiList[i];
			WifiSignature anotherSignature = anotherWifiList[j];
			int compareResult = thisSignature.compareTo(anotherSignature);
						
			if (compareResult == 0) {
				thisList.add(thisSignature);
				anotherList.add(thisSignature);
				i++; j++;
			} else if (compareResult < 0) {
				i++;
			} else {
				j++;
			}
		}
		
		WifiSignature[] thisArray = thisList.toArray(new WifiSignature[thisList.size()]);
		WifiSignature[] anotherArray = anotherList.toArray(new WifiSignature[anotherList.size()]);
		
		float difference = Fingerprint.getEuclideDistance(
				Fingerprint.getStackingDifference(thisArray),
				Fingerprint.getStackingDifference(anotherArray));
				
		// float proportion = (float) count / Math.min(thisSize, anotherSize);
		return difference;		
	}
	
	/**
	 * Deletes unnecessary WifiSignature objects from the list of WifiSignature
	 * objects. Objects which will be deleted are ...
	 */
	private void filter() {		
		int count = 0;
		for (int i = 0; i < getSize(); i++)
			if (wifiList[i].getReceivedSignalStrength() > -80)
				count++;
		
		WifiSignature[] filteredWifiList = new WifiSignature[count];
		
		for (int i = 0, j = 0; (i < getSize()) && (j < count); i++)
			if (wifiList[i].getReceivedSignalStrength() > -80) {
				filteredWifiList[j] = wifiList[i];
				j++;
			}
		
		wifiList = filteredWifiList;
		
	}

	/**
	 * Gets the current number of WifiSignature objects in the list.
	 * 
	 * @return the number of WifiSignature objects
	 */
	public int getSize() {
		return this.wifiList.length;
	}

	/**
	 * Gets the list of all wifi signal objects in the form of an array of
	 * WifiSignature objects.
	 * 
	 * @return array of WifiSignature objects extracted from this current object
	 */
	public WifiSignature[] getWifiList() {
		return this.wifiList;
	}

	/**
	 * Sorts the list of WifiSignature objects by using the compareTo method of
	 * the WifiSignature class
	 */
	public void sort() {
		Arrays.sort(this.wifiList);
	}

	/**
	 * Returns this Fingerprint object in the form of string.
	 * 
	 * @return a string with many group of lines. Each group is a substring form
	 *         of a WifiSignature objects in the list
	 */
	@Override
	public String toString() {
		String tempString = "Place: " + this.locationLabel + "\n";
		for (int i = 0; i < this.wifiList.length; i++) {
			tempString += this.wifiList[i].toString() + "\n";
		}
		return tempString;
	}
	
	
	static int[] getStackingDifference(WifiSignature[] wifiList) {
		int[] result = new int[wifiList.length];
		for (int i = 0; i < wifiList.length; i++) {
			result[i] = 0;
			for (int j = 0; j < wifiList.length; j++) {
				int difference = wifiList[i].getReceivedSignalStrength()
					- wifiList[j].getReceivedSignalStrength();
				if (difference > 0)
					result[i] += difference;
			}
		}
		return result;
	}
	
	static float getEuclideDistance(int[] firstVector, int[] secondVector) {
		int size = firstVector.length;
		
		//if (size != secondVector.length)
			//throw new Exception("different size err in get getEuclideDistance");
		int sum = 0;
		for (int i = 0; i < size; i++) {
			int difference = firstVector[i] - secondVector[i];
			sum += difference * difference;
		}
		
		return (float) Math.sqrt(sum);
	}
}
