// @author: bvuong1993

// Server part, which can process directly Java Serializable Object
// Support multiple clients concurrently accessing the server
// Missing Exception handlers

package com.example.server;

import java.net.*;
import com.example.share.*;

public class Server {
	private int port;
	private Processor processor;

	public Server(Processor processor, int port) {
		this.processor = processor;
		this.port = port;
	}

	public void serve() throws Exception {
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket clientSocket = serverSocket.accept();
			new Thread(new ClientThread(clientSocket, processor)).start();
		}
	}

	public static void main(String args[]) {
		Processor demoProcessor = new MyProcessor();
		Server demoServer = new Server(demoProcessor, Constant.SERVER_PORT);
		try {
			demoServer.serve();
			System.out.println("Server is running");
		} catch (Exception ignoreException) {
			// change in the future
		}
	}
}
