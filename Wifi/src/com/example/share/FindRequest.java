package com.example.share;

import com.example.*;

/**
 * 
 * @author bvuong93
 * 
 *         FindRequest class is the subclass of the Request class, which is
 *         specialized to search a Fingerprint object on the database on the
 *         Server. An FindRequest object keeps only a Fingerprint object field.
 */
public class FindRequest extends Request {
	/**
	 * Constructs an FindRequest object with a given Fingerprint object.
	 * 
	 * @param fingerprint
	 *            the Fingerprint object which will be searched from the
	 *            database on Server
	 */
	public FindRequest(Fingerprint fingerprint) {
		super(fingerprint);
	}
}
