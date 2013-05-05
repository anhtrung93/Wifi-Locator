package com.example.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.example.share.AddRequest;
import com.example.share.Constant;
import com.example.share.FindRequest;
import com.example.share.Fingerprint;
import com.example.share.RemoveRequest;

/**
 * 
 * @author bvuong93
 * 
 *         Class MyProcessor is used to process different requests from the
 *         clients. MyProcessor encapsulates in it the true database of the
 *         program, which is called ArrayList<Fingerprint> fingerprintList.
 * 
 */
public class MyProcessor implements Processor, Serializable {
	final static long serialVersionUID = 1L;
	private ArrayList<Fingerprint> fingerprintList;

	/**
	 * Constructs a new empty list of Fingerprint.
	 */
	public MyProcessor() {
		fingerprintList = new ArrayList<Fingerprint>();
	}

	/**
	 * Reads the database from a fileName team
	 * 
	 * @param fileName
	 *            the name of the file containning the MyProcessor.
	 * @throws Exception
	 *             there may be wrong fileName, or the data in the wifi
	 */
	public MyProcessor(final String fileName) throws Exception {
		loadFromFile(fileName);
	}

	/**
	 * The real part of loading the database from a "fileName" file
	 * 
	 * @param fileName
	 *            the name of the file containning the MyProcessor.
	 * @throws Exception
	 *             there may be wrong fileName, or the data in the wifi
	 */
	public void loadFromFile(final String fileName) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				fileInputStream);
		Object myProcessorFromFile = objectInputStream.readObject();
		if (!(myProcessorFromFile instanceof MyProcessor)) {
			objectInputStream.close();
			fileInputStream.close();
			throw new Exception("Wrong file format: " + fileName);
		}
		fingerprintList = ((MyProcessor) myProcessorFromFile).fingerprintList;
		fileInputStream.close();
		objectInputStream.close();
	}

	/**
	 * Writes the current database of MyProcessor into a filename team.
	 * 
	 * @param fileName
	 *            the name of the file which the Fingerprint list will be
	 *            written in.
	 * @throws Exception
	 */

	public void storeToFile(final String fileName) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);
		objectOutputStream.writeObject(this);
		fileOutputStream.close();
		objectOutputStream.close();
	}

	/**
	 * Processes incoming requests:
	 * <ul>
	 * <li>AddRequest: add a new Fingerprint into fingerprintList</li>
	 * <li>RemoveRequest: delete an old Fingerprint from fingerprintList</li>
	 * <li>FindRequest: search for a Fingerprint on fingerprintList</li>
	 * <li>...</li>
	 * </ul>
	 * 
	 * @param requestObject
	 */
	@Override
	public Object process(final Object requestObject) {
		Object resultObject = null;
		if (Constant.isFINISH(requestObject)) {
			resultObject = requestObject;
		} else if (requestObject instanceof AddRequest) {
			add(((AddRequest) requestObject).getFingerprint());
		} else if (requestObject instanceof RemoveRequest) {
			remove(((RemoveRequest) requestObject).getFingerprint());
		} else if (requestObject instanceof FindRequest) {
			resultObject = find(((FindRequest) requestObject).getFingerprint());
		} else {
			System.err.println("Uknown request recognized");
		}

		return resultObject;
	}

	/**
	 * Searches for a Fingerprint on the fingerprintList
	 * 
	 * @param queryFingerprint
	 *            a Fingerprint to search for
	 * @return the closest Fingerprint to the query, using
	 *         Fingerprint.differFrom(Fingerprint) to compare
	 */
	public Fingerprint find(final Fingerprint queryFingerprint) {
		Fingerprint result = queryFingerprint;
		float smallestDifference = Constant.MAXIMUM_DIFFERENCE;
		for (Fingerprint fingerprint : fingerprintList) {
			float difference = fingerprint.differFrom(queryFingerprint);
			if (difference < smallestDifference) {
				result = fingerprint;
				smallestDifference = difference;
			}
		}
		System.out.println("Difference = " + smallestDifference);
		return result;
	}

	/**
	 * Adds a new Fingerprint to the fingerprintList
	 * 
	 * @param newFingerprint
	 *            a new Fingerprint which will be added into the fingerprintList
	 */
	public void add(final Fingerprint newFingerprint) {
		fingerprintList.add(newFingerprint);
	}

	/**
	 * Deletes an old Fingerprint from the fingerprintList. <b>What will happen
	 * if fingerprint not in the fingerprintList</b>
	 * 
	 * @param oldFingerprint
	 *            an old Fingerprint which will be deleted from the
	 *            fingerprintList
	 */
	public void remove(final Fingerprint oldFingerprint) {
		fingerprintList.remove(oldFingerprint);
	}

	/**
	 * 
	 * @return the list of Fingerprints the processor are maintaining
	 */
	public ArrayList<Fingerprint> getFingerprintList() {
		return fingerprintList;
	}
}