package com.example.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.share.Constant;

/**
 * @author bvuong93
 * 
 *         ClientThread class is used to control the requests from multiple
 *         clients. A new ClientThread object is created each time a new request
 *         comes. Each object has:
 *         <ul>
 *         <li>clientSocket: socket of the client</li>
 *         <li>requestProcessor: a processor object to process the requests from
 *         this clientSocket</li>
 *         </ul>
 */
class ClientThread implements Runnable {
	private final transient Socket clientSocket;
	private final transient Processor requestProcessor;

	/**
	 * Starts a new thread of Client with the given clientSocket and a
	 * processor.
	 * 
	 * @param clientSocket
	 *            the socket of one of the clients
	 * @param requestProcessor
	 *            the processor to handle the requests from the clients
	 */
	public ClientThread(final Socket clientSocket,
			final Processor requestProcessor) {
		this.clientSocket = clientSocket;
		this.requestProcessor = requestProcessor;
	}

	/**
	 * Each client-thread runs: Connects the server with clients through
	 * objectInputStream\objectOuputStream objects, keeps receiving request
	 * objects and answering when it is necessary until the connections are
	 * terminated.
	 * 
	 */
	@Override
	public void run() {
		try {
			ObjectOutputStream objStreamToClient = new ObjectOutputStream(
					this.clientSocket.getOutputStream());
			ObjectInputStream objStreamFromClient = new ObjectInputStream(
					this.clientSocket.getInputStream());

			Object receivedObject;
			Object toSendObject;
			do {
				receivedObject = objStreamFromClient.readObject();
				System.out.println("Received " + receivedObject);
				toSendObject = this.requestProcessor.process(receivedObject);
				objStreamToClient.writeObject(toSendObject);
				System.out.println(">>>>>>>Return " + toSendObject);
				System.out
						.println("------------------END REQUEST--------------------\n");
			} while (!Constant.isFINISH(receivedObject)
					&& !Constant.isFINISH(toSendObject));

			objStreamFromClient.close();
			objStreamToClient.close();
			this.clientSocket.close();
		} catch (Exception threadException) {
			threadException.printStackTrace();
		}
	}
}