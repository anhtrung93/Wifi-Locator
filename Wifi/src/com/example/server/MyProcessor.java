package com.example.server;

import java.util.*;
import java.io.*;
import com.example.share.*;
import com.example.*;

public class MyProcessor implements Processor {
	private ArrayList <Fingerprint> fingerList;

	public MyProcessor() {
		fingerList = new ArrayList<Fingerprint>();
	}

	public MyProcessor(String fileName) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		fingerList = (ArrayList <Fingerprint>) objectInputStream.readObject();
		fileInputStream.close();
		objectInputStream.close();
	}

	public void storeToFile(String fileName) throws Exception {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(fingerList);
		fileOutputStream.close();
		objectOutputStream.close();
	}

	public Object process(Object object) {
		Object result = null;
		if (Constant.isFINISH(object)) {
			result = object;		    
		} else if (object instanceof AddRequest) {
			add(((AddRequest) object).getFingerprint());
		} else if (object instanceof RemoveRequest) {
			remove(((RemoveRequest) object).getFingerprint());
		} else if (object instanceof FindRequest) {
			result = find(((FindRequest) object).getFingerprint());
		} else {
			System.err.println("Uknown request regcognized");
		}
		return result;
	}

	public Fingerprint find(Fingerprint query) {
		Fingerprint result = null;
		float smallestDifference = Constant.MAXIMUM_DIFFERENCE;
		for (Fingerprint fingerprint: fingerList) {
			float diff = fingerprint.differFrom(query);
			if (diff < smallestDifference) {
				result = fingerprint;
				smallestDifference = diff;
			}
		}
		return result;
	}

	public void add(Fingerprint fingerprint) {
		fingerList.add(fingerprint);
	}

	public void remove(Fingerprint fingerprint) {
		fingerList.remove(fingerprint);
	}
}
