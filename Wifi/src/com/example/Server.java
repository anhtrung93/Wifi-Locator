// @author: bvuong1993

// Server part, which can process directly Java Serializable Object
// Support multiple clients concurrently accessing the server
// Missing Exception handlers

package com.example;

import java.io.*;
import java.net.*;

class ClientThread implements Runnable {

    private Socket clientSocket;
    private Processor protocol;
    public ClientThread(Socket clientSocket, Processor protocol) {
	this.clientSocket = clientSocket;
	this.protocol = protocol;
    }

    public void run() {
	try {
	    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
	    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

	    Object received, toSent;
	    do {
		received = ois.readObject();
		toSent = protocol.process(received);
		oos.writeObject(toSent);
		//		oos.flush();
	    } while (received != Constant.FINISH && toSent != Constant.FINISH);
	
	    ois.close();
	    oos.close();
	    clientSocket.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

public class Server {
    private int port;
    private Processor protocol;
    
    public Server(Processor protocol, int port) {
	this.protocol = protocol;
	this.port = port;
    }

    public void serve() throws Exception {
	ServerSocket serverSocket = new ServerSocket(port);
	while (true) {
	    Socket clientSocket = serverSocket.accept();
	    new Thread(new ClientThread(clientSocket, protocol)).start();
	}
    }
}
