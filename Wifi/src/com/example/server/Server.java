package com.example.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.example.share.Constant;

/**
 * 
 * @author bvuong93
 * 
 *         Server object is located on the server. The Server object can process
 *         directly Java Serializable Object. It also supports multiple clients
 *         concurrently accessing the server. Server object keeps composites two
 *         objects:</br>
 *         <p>
 *         <ul>
 *         <li>portNumber: the port number which clients will use to connect to
 *         the server</li>
 *         <li>objProcessor: this processor will process all the objects from
 *         the clients</li>
 *         </ul>
 *         <b>Alert: Missing Exception handlers</b>
 */
public class Server {
	private final transient int portNumber;
	private final transient Processor objProcessor;

	/**
	 * Initializes a new Server object with two given object: a processor and
	 * the port number.
	 * 
	 * @param initObjProcessor
	 *            the object which will process all the requests and will answer
	 *            the client if it is necessary
	 * @param initPortNumber
	 *            the port integer number which all clients use to connect to
	 *            the server
	 */
	public Server(final Processor initObjProcessor, final int initPortNumber) {
		this.objProcessor = initObjProcessor;
		this.portNumber = initPortNumber;
	}

	/**
	 * Starts serving the clients' requests. Waits for incoming requests. When a
	 * request comes, a new ClientThread is created to process that request.
	 * 
	 * @throws Exception
	 *             exception when something goes wrong
	 */
	public void serve() throws Exception {
		ServerSocket serverSocket = new ServerSocket(this.portNumber);
		while (true) {
			Socket clientSocket = serverSocket.accept();
			new Thread(new ClientThread(clientSocket, this.objProcessor))
					.start();
		}
	}

	/**
	 * Starts the Server.java. Initializes ServerController, MyProcessor,
	 * Server. A ServerController is added to control the Server. Server strarts
	 * serving.
	 * 
	 * @param args
	 *            just ignore this
	 */

	public static void main(final String args[]) {
		MyProcessor demoProcessor = new MyProcessor();
		Server demoServer = new Server(demoProcessor, Constant.SERVER_PORT);
		ServerController controller = new ServerController(demoProcessor);
		try {
			System.out.println("Server is running");
			// Starts a new Thread to control the Server program.
			new Thread(controller).start();
			// the main Thread keep serving clients
			demoServer.serve();
		} catch (Exception serverException) {
			System.out.println("Error!!!");
			serverException.printStackTrace();
		}
	}
}