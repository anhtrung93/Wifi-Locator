/**
 * 
 */
package com.example;

import java.io.*;
import java.net.*;

/**
 * author anhtrung93
 * 
 *         Class Database overview: This class is a virtual database in the
 *         device as it is actually the link to the real database in the server
 * 
 */

// Clients interact with server via this class
public class DataBase {
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private Socket clientSocket;

	/**
	 * Constructor: DataBase()
	 * Input: a string and an integer (the string contains the server address
	 *         and the integer is the server port)
	 * Output: this object after initialization
	 */
	public DataBase(String serverAddress, int serverPort) throws Exception {
		clientSocket = new Socket(serverAddress, serverPort);
		objectOutputStream = new ObjectOutputStream(
				clientSocket.getOutputStream());
		objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
	}

	/**
	 * Method: request(Object)
	 * Input: the request object
	 * Output: the object replied from server
	 */
	private Object request(Object req) throws Exception {
		objectOutputStream.writeObject(req);
		return objectInputStream.readObject();
	}

	/**
	 * Method: find(Fingerprint)
	 * Input: this object + a Fingerprint(which needs to be found)
	 * Output: a Fingerprint which is closest to the input Fingerprint object
	 */
	public Fingerprint find(Fingerprint queryFingerprint) throws Exception {
		return (Fingerprint) request(new FindRequest(queryFingerprint));
	}

	/**
	 * Method add(Fingerprint)
	 * Input: this object
	 * Output: this object after adding the Fingerprint object to the database
	 */
	public void add(Fingerprint newFingerprint) throws Exception {
		request(new AddRequest(newFingerprint));
	}

	/**
	 * Method: remove(Fingerprint)
	 * Input: this object
	 * Output: this object after deleting the Fingerprint from the database
	 */
	public void remove(Fingerprint oldFingerprint) throws Exception {
		request(new RemoveRequest(oldFingerprint));
	}

	/**
	 * Method: closeSession()
	 * Description: close the Session by closing all streams and socket
	 */
	public void closeSession() throws Exception {
		request(Constant.FINISH);
		objectInputStream.close();
		objectOutputStream.close();
		clientSocket.close();
	}
}
