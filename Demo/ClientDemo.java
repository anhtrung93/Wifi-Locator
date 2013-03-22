// vuongbv

// guide to get the job done:
// just start the server, and run multiple clients at the same time
// for each client, the user enter lines of string
// the server will reply the oringinal string and its length
// enter "eof" to terminate the connection and exits

import java.io.*;
import java.net.*;

public class ClientDemo {
    public static void main(String args[]) throws Exception {

	Socket clientSock = new Socket("localhost", 4444);

	//oos.flush();
	ObjectOutputStream oos = new ObjectOutputStream(clientSock.getOutputStream());
	ObjectInputStream ois = new ObjectInputStream(clientSock.getInputStream());


	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));


	while (true) {
	    String s = stdin.readLine();
	    if (s.equals("eof"))
		break;
	    
	    oos.writeObject(s);
	    //	    oos.flush();
	    s = (String) ois.readObject();
	    
	    System.out.println(s);
	    //	    System.out.flush();
	}
	oos.writeObject(Finish.FINISH);
	//	oos.flush();

	stdin.close();
	ois.close();
	oos.close();
	clientSock.close();
    }
}
	    