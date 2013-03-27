// vuongbv

package com.example;

import java.io.*;
import java.net.*;

public class ClientDemo {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSock;
    
    public ClientDemo() throws Exception{
	clientSock = new Socket("localhost", 4444);
	oos = new ObjectOutputStream(clientSock.getOutputStream());
	ois = new ObjectInputStream(clientSock.getInputStream());
    }

    public Object request(Object req) throws Exception {
	oos.writeObject(req);
	return ois.readObject();
    }

    public void finish() throws Exception {
	request(Finish.FINISH);
	ois.close();
	oos.close();
	clientSock.close();
    }
}
