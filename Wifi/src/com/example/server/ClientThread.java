package com.example.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.example.share.Constant;

class ClientThread implements Runnable {
	private Socket clientSocket;
	private Processor processor;

	public ClientThread(Socket clientSocket, Processor processor) {
		this.clientSocket = clientSocket;
		this.processor = processor;
	}

	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					clientSocket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(
					clientSocket.getInputStream());

			Object received, toSent;
			do {
				received = ois.readObject();
				toSent = processor.process(received);
				oos.writeObject(toSent);
				// oos.flush();
			} while (received != Constant.FINISH && toSent != Constant.FINISH);

			ois.close();
			oos.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}