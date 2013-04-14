package com.example;

import com.example.share.*;

/**
 * @author anhtrung93
 * 
 *         Database class is actually a virtual database as the real database is
 *         located on the Server. This class supports all methods that other
 *         part of the application need to communicate with the database.
 * 
 */
public class Database {
	/**
	 * Searches for a Fingerprint object on the real database on the server.
	 * 
	 * @param queryFingerprint
	 *            a Fingerprint object that the client want to find on the
	 *            Server
	 * @return the closest Fingerprint object with the one sent to the Server
	 * @throws Exception
	 *             exception may caused when the object is sending\receiving
	 *             to\from the Server
	 */
	static public Fingerprint find(Fingerprint queryFingerprint) throws Exception {
		RequestTask findTask = new RequestTask();
		findTask.execute(new FindRequest(queryFingerprint));
		return findTask.get();
	}

	/**
	 * Adds a Fingerprint object to the real database on the server.
	 * 
	 * @param newFingerprint
	 *            a Fingerprint object that the client want to add to the
	 *            database
	 * @throws Exception
	 *             exception may caused when the object is sending to the Server
	 */
	static public void add(Fingerprint newFingerprint) throws Exception {
		new RequestTask().execute(new AddRequest(newFingerprint));
	}

	/**
	 * Adds a Fingerprint object to the real database on the server.
	 * 
	 * @param newFingerprint
	 *            a Fingerprint object that the client want to add to the
	 *            database
	 * @throws Exception
	 *             exception may caused when the object is sending to the Server
	 */
	static public void remove(Fingerprint oldFingerprint) throws Exception {
		new RequestTask().execute(new RemoveRequest(oldFingerprint));
	}
}
