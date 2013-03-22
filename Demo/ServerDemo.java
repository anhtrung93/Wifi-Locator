// vuongbv
// Server part, which can process directly Java Serializable Object
// Support multiple clients concurrently accessing the server
// Missing Exception handlers

import java.io.*;
import java.net.*;

class Protocol {
    public Object process(Object obj) { // echo server
	if (obj == Finish.FINISH)
	    return obj;
	String s = new String((String) obj);
	s += " has the length of " + s.length();
	return s;
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