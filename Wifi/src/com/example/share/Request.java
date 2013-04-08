package com.example.share;

import com.example.*;

/**
 * @author bvuong93
 * 
 *         Request class is an abstract base class for AddRequest, RemoveRequest
 *         and FindRequest classes. Subclasses are specialized for adding a new
 *         Fingerprint, deleting an obsolete Fingerprint and searching for a
 *         Fingerprint to/from the Database on Server, respectively. Request
 *         objects will be transferred between the client and the Server.
 */
public abstract class Request {
	protected Fingerprint fingerprint;

	/**
	 * Creates a new Request object with a given Fingerprint object.
	 * 
	 * @param fingerprint
	 *            a Fingerprint object to add, delete or search to/from/on
	 *            database
	 */
	public Request(Fingerprint fingerprint) {
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