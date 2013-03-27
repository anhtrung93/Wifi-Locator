// vuongbv
// Server part, which can process directly Java Serializable Object
// Support multiple clients concurrently accessing the server
// Missing Exception handlers

package com.example;
import java.io.*;
import java.net.*;

class Protocol {
    private Process p;
    public Protocol() {
	Process p = new Process();
    }
    
    public Object process(Object obj) {
	Object ret = null;
	if (obj == Finish.FINISH)
	    ret = obj;
	else if (obj instanceof AddRequest) {
	    p.add(((AddRequest) obj).getFingerprint());
	} else if (obj instanceof RemoveRequest) {
	    p.remove(((RemoveRequest) obj).getFingerprint());
	} else if (obj instanceof FindRequest) {
	    ret = p.find(((FindRequest) obj).getFingerprint());
	}
	
	return ret;
    }
}

class ClientThread implements Runnable {

    private Socket clientSock;
    public ClientThread(Socket clientSocket) {
	clientSock = clientSocket;
    }

    public void run() {
	try {
	    ObjectOutputStream oos = new ObjectOutputStream(clientSock.getOutputStream());
	    ObjectInputStream ois = new ObjectInputStream(clientSock.getInputStream());

	    Protocol p = new Protocol();
	    
	    Object received, toSent;
	    do {
		received = ois.readObject();
		toSent = p.process(received);
		oos.writeObject(toSent);
		//		oos.flush();
	    } while (received != Finish.FINISH && toSent != Finish.FINISH);
	
	    ois.close();
	    oos.close();
	    clientSock.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
			
public class ServerDemo {
    public static void main(String args[]) throws Exception {
	ServerSocket ss = new ServerSocket(4444);

	while (true) {
	    Socket clientSock = ss.accept();
	    new Thread(new ClientThread(clientSock)).start();
	}
    }
}