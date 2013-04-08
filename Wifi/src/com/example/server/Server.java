package com.example.server;

import java.net.*;
import com.example.share.*;

/**
 * 
 * @author bvuong93
 * 
 *         Server object is located on the server. The Server object can process
 *         directly Java Serializable Object. It also supports mulitple clients
 *         concurrently accessing the server. Server object keeps composites two
 *         objects:</br>
 *         <p>
 *         <ul>
 *         <li>port: the port number which clients will use to connect to the
 *         server</li>
 *         <li>processor: the object will process all the requests from the
 *         clients</li>
 *         </ul>
 *         <b>Alert: Missing Exception handlers</b>
 */
public class Server {
	private int port;
	private Processor processor;

	/**
	 * Initializes a new Server object with two given object: a processor and
	 * the port number.
	 * 
	 * @param processor
	 *            the object which will process all the requests and will answer
	 *            the client if it is necessary
	 * @param port
	 *            the port integer number which all clients use to connect to
	 *            the server
	 */
	public Server(Processor processor, int port) {
		this.processor = processor;
		this.port = port;
	}

	/**
	 * Starts serving the clients' requests
	 * 
	 * @throws Exception
	 *             exception when something goes wrong
	 */
	public void serve() throws Exception {
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket clientSocket = serverSocket.accept();
			new Thread(new ClientThread(clientSocket, processor)).start();
		}
	}

	/**
	 * Starts the Server.java
	 * 
	 * @param args
	 *            just ignore this
	 */
	public static void main(String args[]) {
		Processor demoProcessor = new MyProcessor();
		Server demoServer = new Server(demoProcessor, Constant.SERVER_PORT);
		try {
			demoServer.serve();
			System.out.println("Server is running");
		} catch (Exception exception) {
			// change in the future
		}
	}
}
