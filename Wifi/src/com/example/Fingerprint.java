/**
 * 
 */
package com.example;

import java.util.List;

import android.net.wifi.ScanResult;

/**
 * @author anhtrung93
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
		
		this.reorganize();		
	}
	
	private void reorganize(){
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
