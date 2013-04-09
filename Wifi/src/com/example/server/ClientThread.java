package com.example.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.share.Constant;

/**
 * @author bvuong93
 * 
 *         ClientThread class is used to control the requests from multiple
 *         clients. ClientThread is the subclass of the Runnable class so that
 *         it can process requests from multiple clients concurrently. Each
 *         object has:
 *         <p>
 *         <ul>
 *         <li>socket of the client</li>
 *         <li>a processor object to process the requests from this client
 *         Socket</li>
 *         </ul>
 */
class ClientThread implements Runnable {
	private Socket clientSocket;
	private Processor processor;

	/**
	 * Starts a new thread of Client with the given clientSocket and a
	 * processor.
	 * 
	 * @param clientSocket
	 *            the socket of one of the clients
	 * @param processor
	 *            the processor to handle the requests from the clients
	 */
	public ClientThread(Socket clientSocket, Processor processor) {
		this.clientSocket = clientSocket;
		this.processor = processor;
	}

	/**
	 * Each client thread runs. Connects the server with clients through
	 * objectInput\OuputStream objects, keeps receiving request objects and
	 * answering when it is necessary until the connections are terminated.
	 * 
	 */
	@Override
	public void run() {
		try {
			ObjectOutputStream ouputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(
					clientSocket.getInputStream());

			Object received, toSent;
			do {
				received = inputStream.readObject();
				System.out.println("Received " + received);
				toSent = processor.process(received);
				ouputStream.writeObject(toSent);
				System.out.println(">>>>>>>Return " + toSent);
				System.out.println("------------------END REQUEST--------------------\n");
			} while (! Constant.isFINISH(received)
					&& ! Constant.isFINISH(toSent));

			inputStream.close();
			ouputStream.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}