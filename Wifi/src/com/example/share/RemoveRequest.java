package com.example.share;

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
	final static long serialVersionUID = 1L;

	/**
	 * Constructs an RemoveRequest object with a given Fingerprint object.
	 * 
	 * @param toDeleteFingerprint
	 *            the Fingerprint object which will be removed from the database
	 *            on Server
	 * @throws Exception
	 *             when intializes a RemoveRequest with a null Fingerprint
	 *             pointer
	 */
	public RemoveRequest(final Fingerprint toDeleteFingerprint) throws Exception {
		super(toDeleteFingerprint);
	}

	/**
	 * @return a String form of the RemoveRequest
	 */
	@Override
	public String toString() {
		return "Request (REMOVE)\n" + getFingerprint().toString();
	}

}