package com.example;

import com.example.share.AddRequest;
import com.example.share.FindRequest;
import com.example.share.Fingerprint;
import com.example.share.RemoveRequest;

/**
 * @author bvuong93
 * 
 *         Database class is actually a virtual database as the real database is
 *         located on the Server. This class supports all methods which other
 *         part of the application need to communicate with the database.
 * 
 *         Database instance is the only one (using the Singleton pattern) so
 *         that there is no more than one database for the application.
 * 
 */
public class Database {
	// the only instance of the Database class
	private static Database virtualDatabase = new Database();

	/**
	 * Creates the only instance and also prevents the other instance to be
	 * created
	 */
	private Database() {
		super();
	}

	/**
	 * Returns the only instance of Database class
	 * 
	 * @return the virtualDatabase
	 */
	public static Database getInstance() {
		return Database.virtualDatabase;
	}

	/**
	 * Searches for a Fingerprint object on the real database on the server.
	 * 
	 * @param fingerprintToFind
	 *            a Fingerprint object that the client want to find on the
	 *            Server
	 * @return the closest Fingerprint object with the one sent to the Server
	 * @throws Exception
	 *             exception may caused when the program receives data
	 *             from the Server
	 */
	public Fingerprint find(final Fingerprint fingerprintToFind)
			throws Exception {
		RequestTask findTask = new RequestTask();
		findTask.execute(new FindRequest(fingerprintToFind));
		return findTask.get();
	}

	/**
	 * Adds a Fingerprint object to the real database on the server.
	 * 
	 * @param fingerprintToAdd
	 *            a Fingerprint object that the client want to add to the
	 *            database
	 */
	public void add(final Fingerprint fingerprintToAdd) {
		new RequestTask().execute(new AddRequest(fingerprintToAdd));
	}

	/**
	 * Removes a Fingerprint object from the real database on the server.
	 * 
	 * @param newFingerprint
	 *            a Fingerprint object that the client want to add to the
	 *            database
	 */
	public void remove(final Fingerprint fingerprintToRemove) {
		new RequestTask().execute(new RemoveRequest(fingerprintToRemove));
	}
}