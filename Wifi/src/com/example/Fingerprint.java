package com.example;

import java.util.List;
import java.util.Arrays;

//import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
 *
 *Class 'Fingerprint' Overview:
 *List of all WiFiSignature the device gets at a time
 * which was arranged for future implementation
 *
 */

///  modified by bvuong1993
/// 3 slash mark where i modified

public class Fingerprint {
    /********************Fields*****************************/
    /********************Public Methods*********************/
    ///	private int size;
    /// to me 'size' attribute is unnesscary because of Array.len
    private WiFiSignature [] WiFiList;
    String name;
    /// each Fingerprint should be assigned a name for future use
    

    /// to me, we should let the  WiFiSignature implements Comparable
    /// then we use Array.sort to get the job done much easier

    /*    

    /**
     * Method: sort()
     * Input:  'this'
     * Output: 'this' with the array of WiFiSignature
     * 	- sorted by their BSSID values
     */
    /*
    private void sort(){
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
    */
    /********************Public Methods*********************/
    /**
     * Constructor: Fingerprint(List<ScanResult> WiFiList)
     * Input:  'this' +  a list of ScanResult
     * Output: 'this' with the array of WiFiSignature
     * 	- constructed from the WiFiList
     *  - the array was sorted by the BSSID value
     */


    
    public Fingerprint(List<WiFiSignature> WiFiList, String name){
	/*
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
	*/
	///
	this.WiFiList = (WiFiSignature []) WiFiList.toArray();
	Arrays.sort(this.WiFiList);
	this.name = name;
    }


    
    /**
     * Method: getSize()
     * Input: this
     * Output: the number of the WiFiSignature
     */
    
    public int getSize(){
	return this.WiFiList.length; ///
    }

    ///

    public WiFiSignature[] getWiFiList() {
	return this.WiFiList;
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
	for (int i = 0; i < this.getSize(); i++){ ///
	    tempString += this.WiFiList[i].toString() + "\n";
	}
	return tempString;
    }

    ///
    public float differFrom(Fingerprint another) {
	int i = 0, j = 0;
	WiFiSignature[] list1 = this.getWiFiList();
	WiFiSignature[] list2 = another.getWiFiList();
	int size1 = this.getSize();
	int size2 = another.getSize();
	int count = 0;
	int diff = 0;
	while (i < size1 && j < size2) {
	    WiFiSignature s1 = list1[i];
	    WiFiSignature s2 = list2[i];
	    int d = s1.compareTo(s2);
	    if (d == 0) {
		count++;
		int sd = s1.getRSS() - s2.getRSS();
		diff += sd*sd;
	    } else if (d < 0) {
		i++;
	    } else {
		j++;
	    }
	}
	return (float)diff / (count / (size1+size2));
    }
}
