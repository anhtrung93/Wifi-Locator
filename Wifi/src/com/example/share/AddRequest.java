package com.example.share;

/**
 * 
 * @author bvuong93
 * 
 *         AddRequest class is the subclass of the Request class, which is
 *         specialized to add a new Fingerprint object to the database on the
 *         Server. An AddRequest object keeps only a Fingerprint object field.
 */
public class AddRequest extends Request {
	final static long serialVersionUID = 1L;

	/**
	 * Constructs an AddRequest object with a given Fingerprint object.
	 * 
	 * @param fingerprintToAdd
	 *            the Fingerprint object which will be added into the database
	 *            on Server
	 */
	public AddRequest(final Fingerprint fingerprintToAdd){
		super(fingerprintToAdd);
	}

	/**
	 * @return a String form of the AddRequest
	 */
	@Override
	public String toString() {
		return "Request (ADD)\n" + getFingerprint().toString();
	}
}