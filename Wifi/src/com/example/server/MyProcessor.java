package com.example.server;

import java.util.*;
import java.io.*;
import com.example.share.*;
import com.example.*;

/**
 * 
 * @author bvuong93
 * 
 *         Class MyProcessor is used to process different requests from the
 *         clients. MyProcessor encapsulates in it the true database of the
 *         program, which is called ArrayList<Fingerprint> fingerprintList.
 * 
 */
public class MyProcessor implements Processor {
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
	 *            the name of the file contains the fingerprintList.
	 * @throws Exception
	 *             there may be wrong fileName, or the data in the wifli
	 */
	public MyProcessor(String fileName) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				fileInputStream);
		fingerprintList = (ArrayList<Fingerprint>) objectInputStream
				.readObject();
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
	public void storeToFile(String fileName) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);
		objectOutputStream.writeObject(fingerprintList);
		fileOutputStream.close();
		objectOutputStream.close();
	}

	/**
	 * Processes incoming requests.
	 * 
	 * @param requestObject
	 */
	@Override
	public Object process(Object requestObject) {
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
			System.err.println("Uknown request regcognized");
		}
		return resultObject;
	}

	public Fingerprint find(Fingerprint query) {
		Fingerprint result = null;
		float smallestDifference = Constant.MAXIMUM_DIFFERENCE;
		for (Fingerprint fingerprint : fingerprintList) {
			float diff = fingerprint.differFrom(query);
			if (diff < smallestDifference) {
				result = fingerprint;
				smallestDifference = diff;
			}
		}
		return result;
	}

	public void add(Fingerprint fingerprint) {
		fingerprintList.add(fingerprint);
	}

	public void remove(Fingerprint fingerprint) {
		fingerprintList.remove(fingerprint);
	}
}
