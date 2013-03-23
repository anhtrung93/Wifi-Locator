/**
 * 
 */
package com.example;

import java.util.List;

import android.net.wifi.ScanResult;

/**
 * @author trunganhdinh
 *
 */
public class Fingerprint {
	private int size;
	private WiFiSignature [] WiFiList;
	
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
		for (int i = 0; i < this.size - 1; i++){
			for (int j = i+1; j < this.size; j++){
				String BSSID1 = this.WiFiList[i].getBSSID();
				String BSSID2 = this.WiFiList[j].getBSSID();
				if (BSSID1.compareTo(BSSID2) > 0){
					WiFiSignature tempWiFiSignature = this.WiFiList[i];
					this.WiFiList[i] = this.WiFiList[j];
					this.WiFiList[j] = tempWiFiSignature;
				}
			}
		}		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public String toString(){
		String tempString = "";
		for (int i = 0; i < this.size; i++){
			tempString += this.WiFiList[i].toString() + "\n";
		}
		return tempString;
	}

}
