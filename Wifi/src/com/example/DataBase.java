/**
 * 
 */
package com.example;

/**
 * @author anhtrung93
 *
 *
 */
public class DataBase {
	/********************Fields*****************************/
	private Fingerprint[] fingerprintList;

	/********************Private Methods********************/
	/********************Public Methods*********************/
	/**
	 * Constructor: DataBase()
	 * Input:
	 * Output: this after initialization
	 */
	public DataBase(){
		
	}
	
	/**
	 * Method: loadFromFile()
	 * Input: this
	 * Output: a file was creating to save 'fingerprintList'
	 * 
	 */
	public void loadFromFile(){
	}
	
	/**
	 * Method: storeToFile()
	 * Input: this
	 * Output: a file was creating to save 'fingerprintList'
	 * 
	 */
	public void storeToFile(){
	}
	
	/**
	 * Method: find(Fingerprint query)
	 * Input: 'this' object + query
	 * Output: a Fingerprint which is closest to
	 *  - the 'query' object
	 */
	public Fingerprint find(Fingerprint query){
		return query;
	}
	
	/**
	 * Method add(Fingerprint newFingerprint)
	 * Input: 'this' object
	 * Output: 'this' object after adding 
	 *  - 'newFingerprint' to 'fingerprintList'
	 */
	public void add(Fingerprint newFingerprint){
	}
	
	/**
	 * Method: remove(Fingerprint oldFingerprint)
	 * Input: 'this' object
	 * Output: 'this' object after deleting
	 *  - 'oldFingerprint' from 'fingerprintList'
	 */
	public void remove(Fingerprint oldFingerprint){
	}
}
