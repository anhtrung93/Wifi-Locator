package com.example;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.share.Constant;

/**
 * 
 * @author bvuong93
 * 
 *         Connection class is a class connecting the client Android device to
 *         the Server via Socket. Clients interact with server via this class.
 */

public class Connection {
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private Socket clientSocket;

	/**
	 * 
	 * Starts a new Connection object with the specified server address and the
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
	public Connection(String serverAddress, int serverPort) throws Exception {
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
	public Object request(Object requestObject) throws Exception {
		objectOutputStream.writeObject(requestObject);
		return objectInputStream.readObject();
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
}
