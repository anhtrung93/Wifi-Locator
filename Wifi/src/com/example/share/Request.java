package com.example.share;

import java.io.Serializable;

/**
 * @author bvuong93
 * 
 *         Request class is an abstract base class for AddRequest, RemoveRequest
 *         and FindRequest classes. Subclasses are specialized for adding a new
 *         Fingerprint, deleting an obsolete Fingerprint and searching for a
 *         Fingerprint to/from the Database on Server, respectively. Request
 *         objects will be transferred between the client and the Server.
 */
public abstract class Request implements Serializable {
	protected Fingerprint fingerprint;
	public final static long serialVersionUID = 1L;

	/**
	 * Creates a new Request object with a given Fingerprint object.
	 * 
	 * @param fingerprint
	 *            a Fingerprint object to add, delete or search to/from/on
	 *            database
	 * @throws Exception
	 *             when intializes a Request with a null Fingerprint pointer
	 */
	public Request(Fingerprint fingerprint) throws Exception {
		if (fingerprint == null) {
			throw new Exception(
					"Initialize a request with a null Fingerprint pointer");
		}
		this.fingerprint = fingerprint;
	}

	/**
	 * Gets the Fingerprint object in the Request object.
	 * 
	 * @return fingerprint a Fingerprint object to add, delete or search
	 *         to/from/on database
	 */
	public Fingerprint getFingerprint() {
		return this.fingerprint;
	}
}
