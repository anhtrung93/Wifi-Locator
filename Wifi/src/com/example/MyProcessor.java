package com.example;

import java.util.*;
import java.io.*;

public class MyProcessor implements Processor {
    private ArrayList <Fingerprint> fingerList;
    
    public MyProcessor() {
	fingerList = new ArrayList<Fingerprint>();
    }

    public MyProcessor(String fileName) throws Exception {
	FileInputStream fis = new FileInputStream(fileName);
	ObjectInputStream ois = new ObjectInputStream(fis);
	fingerList = (ArrayList <Fingerprint>) ois.readObject();
	fis.close();
	ois.close();
    }

    public void storeToFile(String fileName) throws Exception {
	FileOutputStream fos = new FileOutputStream(fileName);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(fingerList);
	fos.close();
	oos.close();
    }

    public Object process(Object obj) {
	Object ret = null;
	if (obj == Finish.FINISH)
	    ret = obj;
	else if (obj instanceof AddRequest) {
	    add(((AddRequest) obj).getFingerprint());
	} else if (obj instanceof RemoveRequest) {
	    remove(((RemoveRequest) obj).getFingerprint());
	} else if (obj instanceof FindRequest) {
	    ret = find(((FindRequest) obj).getFingerprint());
	}
	return ret;
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
