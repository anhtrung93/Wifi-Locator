package com.example;

import java.io.*;
import java.net.*;
import java.util.Random;
import com.example.share.*;

/**
 * @author bvuong93
 * 
 *         Database class is actually a virtual database as the real database is
 *         located on the Server. This class supports all methods that other
 *         part of the application need to communicate with the database.
 *         Clients interact with server via this class
 * 
 */
public class Database {
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private Socket clientSocket;

	/**
	 * 
	 * Starts a new Database object with the specified server address and the
	 * server port to connect.
	 * 
	 * @param serverAddress
	 *            a string contains the address of the server
	 * @param serverPort
	 *            an integer which is the port number
	 * @throws Exception
	 *             exception which may exists when creates new
	 *             objectInput/Output Stream and clienSocket
	 */
	public Database(String serverAddress, int serverPort) throws Exception {
		clientSocket = new Socket(serverAddress, serverPort);
		objectOutputStream = new ObjectOutputStream(
				clientSocket.getOutputStream());
		objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
	}

	/**
	 * Sends an object to the server and received an other object from Server.
	 * 
	 * @param requestObject
	 *            an object that the client requests
	 * @return an object replied from the server
	 * @throws Exception
	 *             exception exception may caused when the client and server
	 *             communicate
	 */
	private Object request(Object requestObject) throws Exception {
		objectOutputStream.writeObject(requestObject);
		return objectInputStream.readObject();
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
	public Fingerprint find(Fingerprint queryFingerprint) throws Exception {
		return (Fingerprint) request(new FindRequest(queryFingerprint));
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
	public void add(Fingerprint newFingerprint) throws Exception {
		request(new AddRequest(newFingerprint));
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
	public void remove(Fingerprint oldFingerprint) throws Exception {
		request(new RemoveRequest(oldFingerprint));
	}

	/**
	 * Closes all the objectInput\OuputStream and clientSocket. Also sends a
	 * request to terminate connection.
	 * 
	 * @throws Exception
	 *             exception when something goes wrong
	 */
	public void closeSession() throws Exception {
		request(Constant.FINISH);
		objectInputStream.close();
		objectOutputStream.close();
		clientSocket.close();
	}

	/** 
	 * This is just for demo linking on localhost.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		Database database = new Database(Constant.SERVER_ADDRESS, Constant.SERVER_PORT);
		Random rd = new Random();
		Fingerprint[] fingerList = new Fingerprint[10];
		for (int i = 0; i < 10; i++) {

			WifiSignature[] signatureList = new WifiSignature[5];
			for (int j = 0; j < 5; j++) {
				String name = "wifi" + (j+1);
				int rss = Math.abs(rd.nextInt()) % 100;
				signatureList[j] = new WifiSignature(name, rss, name);
			}
			String name = "place" + (i+1);
			fingerList[i] = new Fingerprint(signatureList, name);
		}


		for (int i = 0; i < 10; i++) {
			System.out.println(fingerList[i]);
			database.add(fingerList[i]);
			//database.request("hello");
		}

		System.out.println("RETTTTTTT");

		//database.closeSession();

		for (int i = 0; i < 10; i++) {
			Fingerprint fp = database.find(fingerList[i]);
			System.out.println(fp);
		}

		database.closeSession();
	}
}
