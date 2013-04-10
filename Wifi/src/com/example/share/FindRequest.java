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
	final static long serialVersionUID = 1L;

	/**
	 * Constructs an FindRequest object with a given Fingerprint object.
	 * 
	 * @param fingerprint
	 *            the Fingerprint object which will be searched from the
	 *            database on Server
	 * @throws Exception
	 *             when intializes a FindRequest with a null Fingerprint pointer
	 */
	public FindRequest(Fingerprint fingerprint) throws Exception {
		super(fingerprint);
	}

	/**
	 * @return a String form of the FindRequest
	 */
	@Override
	public String toString() {
		return "Request (FIND)\n" + getFingerprint().toString();
	}

}
