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
	private final transient ObjectOutputStream objStreamToServer;
	private final transient ObjectInputStream objStreamFromServer;
	private final transient Socket clientSocket;

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
	public Connection(final String serverAddress, final int serverPort)
			throws Exception {
		this.clientSocket = new Socket(serverAddress, serverPort);
		this.objStreamToServer = new ObjectOutputStream(
				this.clientSocket.getOutputStream());
		this.objStreamFromServer = new ObjectInputStream(
				this.clientSocket.getInputStream());
	}

	/**
	 * Sends an object to the server and received an other object from Server.
	 * 
	 * @param objectToSend
	 *            an object that the client requests
	 * @return an object replied from the server
	 * @throws Exception
	 *             exception exception may caused when the client and server
	 *             communicate
	 */
	public Object request(final Object objectToSend) throws Exception {
		this.objStreamToServer.writeObject(objectToSend);
		return this.objStreamFromServer.readObject();
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
		this.objStreamFromServer.close();
		this.objStreamToServer.close();
		this.clientSocket.close();
	}
}