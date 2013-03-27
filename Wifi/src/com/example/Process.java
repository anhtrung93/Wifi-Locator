package com.example;

import java.util.ArrayList;
import java.io.*;

public class Process {
    private ArrayList <Fingerprint> fingerList;


    public Process() {
	fingerList = new ArrayList<Fingerprint>();
    }

    public void loadFromFile() {
	FileInputStream fis = new FileInputStream("fpdb");
	ObjectInputStream ois = new ObjectInputStream(fis);
	fingerList = ois.readObject();
	fis.close();
	ois.close();
    }

    public void storeToFile() {
	FileOutputStream fos = new FileOutputStream("fpdb");
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(fingerList);
	fos.close();
	oos.close();
    }
	
    public Fingerprint find(Fingerprint query) {
	Fingerprint result = null;
	float minDiff = Constant.MAX_DIFF;
	for (Fingerprint fp: fingerList) {
	    float diff = fp.differFrom(query);
	    if (diff < minDiff) {
		result = fp;
		minDiff = diff;
	    }
	}
	return result;
    }

    public void add(Fingerprint fp) {
	fingerList.add(fp);
    }

    public void remove(Fingerprint fp) {
	fingerList.remove(fp);
    }
}