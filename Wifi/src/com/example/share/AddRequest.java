package com.example.share;

import com.example.*;
import java.io.Serializable;

/**
 * 
 * @author bvuong93
 * 
 *         AddRequest class is the subclass of the Request class, which is
 *         specialized to add a new Fingerprint object to the database on the
 *         Server. An AddRequest object keeps only a Fingerprint object field.
 */
public class AddRequest extends Request implements Serializable {

	/**
	 * Constructs an AddRequest object with a given Fingerprint object.
	 * 
	 * @param fingerprint
	 *            the Fingerprint object which will be added into the database
	 *            on Server
	 */
	public AddRequest(Fingerprint fingerprint) {
		super(fingerprint);
	}

    public String toString() {
	return "Request (ADD)\n" + getFingerprint().toString();
    }
}
