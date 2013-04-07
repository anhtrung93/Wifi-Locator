package com.example.share;

import com.example.*;

/**
 * 
 * @author bvuong93
 * 
 *         RemoveRequest class is the subclass of the Request class, which is
 *         specialized to remove an old Fingerprint object to the database on
 *         the Server. An RemoveRequest object keeps only a Fingerprint object
 *         field.
 */
public class RemoveRequest extends Request {

	/**
	 * Constructs an RemoveRequest object with a given Fingerprint object.
	 * 
	 * @param fingerprint
	 *            the Fingerprint object which will be removed from the database
	 *            on Server
	 */
	public RemoveRequest(Fingerprint fingerprint) {
		super(fingerprint);
	}
}
