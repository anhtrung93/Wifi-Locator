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
		this.fingerprintList = new ArrayList<Fingerprint>();
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
		FileInputStream streamFromFile = new FileInputStream(fileName);
		ObjectInputStream objStreamFromFile = new ObjectInputStream(
				streamFromFile);
		Object myProcessorFromFile = objStreamFromFile.readObject();
		if (!(myProcessorFromFile instanceof MyProcessor)) {
			objStreamFromFile.close();
			streamFromFile.close();
			throw new Exception("Wrong file format: " + fileName);
		}
		this.fingerprintList = ((MyProcessor) myProcessorFromFile).fingerprintList;
		streamFromFile.close();
		objStreamFromFile.close();
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
		FileOutputStream streamToFile = new FileOutputStream(fileName);
		ObjectOutputStream objStreamToFile = new ObjectOutputStream(
				streamToFile);
		objStreamToFile.writeObject(this);
		streamToFile.close();
		objStreamToFile.close();
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
	 * @param fingerprintToFind
	 *            a Fingerprint to search for
	 * @return the closest Fingerprint to the query, using
	 *         Fingerprint.differFrom(Fingerprint) to compare
	 */
	public Fingerprint find(final Fingerprint fingerprintToFind) {
		Fingerprint result = fingerprintToFind;
		float smallestDifference = Constant.MAXIMUM_DIFFERENCE;
		for (Fingerprint aFingerprint : this.fingerprintList) {
			float difference = aFingerprint.differFrom(fingerprintToFind);
			if (difference < smallestDifference) {
				result = aFingerprint;
				smallestDifference = difference;
			}
		}
		System.out.println("Difference = " + smallestDifference);
		return result;
	}

	/**
	 * Adds a new Fingerprint to the fingerprintList
	 * 
	 * @param fingerprintToAdd
	 *            a new Fingerprint which will be added into the fingerprintList
	 */
	public void add(final Fingerprint fingerprintToAdd) {
		this.fingerprintList.add(fingerprintToAdd);
	}

	/**
	 * Deletes an old Fingerprint from the fingerprintList. <b>What will happen
	 * if fingerprint not in the fingerprintList</b>
	 * 
	 * @param fingerprintToRemove
	 *            an old Fingerprint which will be deleted from the
	 *            fingerprintList
	 */
	public void remove(final Fingerprint fingerprintToRemove) {
		this.fingerprintList.remove(fingerprintToRemove);
	}

	/**
	 * 
	 * @return the list of Fingerprints the processor are maintaining
	 */
	public ArrayList<Fingerprint> getFingerprintList() {
		return this.fingerprintList;
	}
}