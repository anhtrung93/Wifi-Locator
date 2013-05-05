package com.example;

import com.example.share.AddRequest;
import com.example.share.FindRequest;
import com.example.share.Fingerprint;
import com.example.share.RemoveRequest;

/**
 * @author anhtrung93
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
	 * @param queryFingerprint
	 *            a Fingerprint object that the client want to find on the
	 *            Server
	 * @return the closest Fingerprint object with the one sent to the Server
	 * @throws Exception
	 *             exception may caused when the object is sending\receiving
	 *             to\from the Server
	 */
	public Fingerprint find(final Fingerprint queryFingerprint)
			throws Exception {
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
	public void add(final Fingerprint newFingerprint) throws Exception {
		new RequestTask().execute(new AddRequest(newFingerprint));
	}

	/**
	 * Removes a Fingerprint object from the real database on the server.
	 * 
	 * @param newFingerprint
	 *            a Fingerprint object that the client want to add to the
	 *            database
	 * @throws Exception
	 *             exception may caused when the object is sending to the Server
	 */
	public void remove(final Fingerprint oldFingerprint) throws Exception {
		new RequestTask().execute(new RemoveRequest(oldFingerprint));
	}
}