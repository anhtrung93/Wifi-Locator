/**
 * 
 */
package com.example;

/**
 * @author anhtrung93
 *
 *
 */
 
 // Clients interract with server via this class
 
 public class DataBase {
	/********************Fields*****************************/
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket clientSock;



	/********************Private Methods********************/
	/********************Public Methods*********************/
	/**
	 * Constructor: DataBase()
	 * Input: server address
	 * Output: this after initialization
	 */
	public DataBase(String serverAddress, int serverPort) throws Exceptions{
		clientSock = new Socket(serverAddress, serverPort);
		oos = new ObjectOutputStream(clientSock.getOutputStream());
		ois = new ObjectInputStream(clientSock.getInputStream());		
	}
	
	/**
	 * Method: loadFromFile()
	 * Input: this
	 * Output: a file was creating to save 'fingerprintList'
	 * 
	 */
	public void loadFromFile(){
		// for very first release, everything will be pushed to a server
		// server will serve hard job, client(android) just request
		// no local database is implemented at the moment
		// just for future use
	}
	
	/**
	 * Method: storeToFile()
	 * Input: this
	 * Output: a file was creating to save 'fingerprintList'
	 * 
	 */
	public void storeToFile(){
		// the same as loadFromFile method
	}
	
	
	/**
	 * Method: request(Object obj)
	 * Input: request object
	 * Output: object replied from server
	 */
	 
	 
	private Object request(Object req) throws Exception {
		oos.writeObject(req);
		return ois.readObject();
	}
	
	/**
	 * Method: find(Fingerprint query)
	 * Input: 'this' object + query
	 * Output: a Fingerprint which is closest to
	 *  - the 'query' object
	 */
	public Fingerprint find(Fingerprint query){
		return (Fingerprint) request(new FindRequest(query))
	}
	
	/**
	 * Method add(Fingerprint newFingerprint)
	 * Input: 'this' object
	 * Output: 'this' object after adding 
	 *  - 'newFingerprint' to 'fingerprintList'
	 */
	public void add(Fingerprint newFingerprint){
		return (Fingerprint) request(new AddRequest(newFingerprint));
	}
	
	/**
	 * Method: remove(Fingerprint oldFingerprint)
	 * Input: 'this' object
	 * Output: 'this' object after deleting
	 *  - 'oldFingerprint' from 'fingerprintList'
	 */
	public void remove(Fingerprint oldFingerprint){
		return (Fingerprint) remove(new RemoveRequest(oldFingerprint));
	}
	
	/**
	 * Method: closeSession
	 * just to close the Session
	 * do it when there is no more work to do with the server
	 */
	public void closeSession() {
		request(Constant.FINISH);
		ois.close();
		oos.close();
		clientSock.close();
	}
}
