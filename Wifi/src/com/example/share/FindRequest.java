package com.example.share;

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
	 * @param fingerprintToFind
	 *            the Fingerprint object which will be searched from the
	 *            database on Server
	 * @throws Exception
	 *             when intializes a FindRequest with a null Fingerprint pointer
	 */
	public FindRequest(final Fingerprint fingerprintToFind) throws Exception {
		super(fingerprintToFind);
	}

	/**
	 * @return a String form of the FindRequest
	 */
	@Override
	public String toString() {
		if (fingerprint == null) {
			System.out.println("There is no fingerprint inside");
		}
		return "Request (FIND)\n" + getFingerprint().toString();
	}

}