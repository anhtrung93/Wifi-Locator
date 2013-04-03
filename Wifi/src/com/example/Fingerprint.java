package com.example;

import java.util.List;

import android.app.Activity;
import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 *
 *Class 'Fingerprint' Overview:
 *List of all WiFiSignature the device gets at a time
 * - which was arranged for future implementation
 *
 */
public class Fingerprint extends Activity{
	/********************Fields*****************************/
	private int size;
	private WiFiSignature [] WiFiList;
	String label;	//name of the room or location
	
	/********************Private Methods********************/
	/**
	 * Method: sort()
	 * Input:  'this'
	 * Output: 'this' with the array of WiFiSignature
	 * 	- sorted by their BSSID values
	 */
	public void sort(){
		for (int i = 0; i < this.size - 1; i++){
			String BSSID1 = this.WiFiList[i].getBSSID();
			for (int j = i+1; j < this.size; j++){
				String BSSID2 = this.WiFiList[j].getBSSID();
				if (BSSID1.compareTo(BSSID2) > 0){
					BSSID1 = BSSID2;
					WiFiSignature tempWiFiSignature = this.WiFiList[i];
					this.WiFiList[i] = this.WiFiList[j];
					this.WiFiList[j] = tempWiFiSignature;
				}
			}
		}
	}
	
	/**
	 * Method: filter()
	 * Input: 'this'
	 * Output: 'this' with the array of WiFiSignature was filtered
	 * - by deleting irrelevant of unimportant WiFiSignature object
	 */
	private void filter(){		
	}
	

	/********************Public Methods*********************/
	
	public Fingerprint(WiFiSignature[] wiFiList) {
		super();
		WiFiList = wiFiList;
	}

	public Fingerprint(WiFiSignature[] wiFiList, String label) {
		super();
		WiFiList = wiFiList;
		this.label = label;
	}
	
	
	/**
	 * Constructor: Fingerprint(List<ScanResult> WiFiList)
	 * Input: a list of ScanResult
	 * Output: 'this' with the array of WiFiSignature
	 * 	- constructed from the WiFiList
	 *  - the array was sorted by the BSSID value
	 *  - the array was filtered by calling this.filter()
	 */
	public Fingerprint(List<ScanResult> WiFiList){
		if (WiFiList == null){
			this.size = -1;
			return;
		}
		this.size = WiFiList.size();
		this.WiFiList = new WiFiSignature[this.size];
		for (int i = 0; i < this.size; i++){
			this.WiFiList[i] = new WiFiSignature(WiFiList.get(i));
		}
		
		this.sort();
		this.filter();
	}
	
	/**
	 * Method: getSize()
	 * Input: this
	 * Output: the number of the WiFiSignature
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Method: addLabel(String newLabel)
	 * Input: newLabel - String - the name of the location with
	 * - the respective location
	 * Output: this with this.label == newLabel
	 */
	public void addLabel(String newLabel){
		this.label = newLabel;
	}
	
	/**
	 * Method: differFrom(Fingerprint otherFingerprint)
	 * Input: 'this' object + otherFingerprint
	 * Output: an float which shows the relative 
	 *  - difference between 'this' and otherFingerprint.
	 *  - The larger the float is, the more different they are
	 */
	public float differFrom(Fingerprint otherFingerprint){
		return 0;
	}
	
	/**
	 * Method: toString()
	 * Input: this
	 * Output: a string with this.size line group
	 * - A line group shows the information about 
	 * - a WiFiSignature
	 */
	public String toString(){
		String tempString = "";
		for (int i = 0; i < this.size; i++){
			tempString += this.WiFiList[i].toString() + "\n";
		}
		return tempString;
	}
}
