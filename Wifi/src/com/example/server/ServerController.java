/**
 * this file contains the controller of the server
 * it runs concurrently with clientThreads
 * it controls the server through manipulating its Process 
 */
package com.example.server;

import java.util.Scanner;

import com.example.share.Constant;

/**
 * 
 * @author bvuong93
 * 
 *         Class ServerController is based on the Thread Class. ServerController
 *         object is created to help control the Server.exe on the Server. This
 *         object allows the Server.exe to be stored/loaded database, to exit.
 *         It contains a MyProcessor object.
 * 
 */
public class ServerController implements Runnable {
	private final transient MyProcessor requestProcessor;

	/**
	 * Constructs a new ServerController with a given processor.
	 * 
	 * @param processor
	 *            the processor of the server
	 */
	public ServerController(final MyProcessor processor) {
		this.requestProcessor = processor;
	}

	/**
	 * Called to start a new Thread. Waiting for a new command:
	 * <p>
	 * <ul>
	 * <li>store: save database into file</li>
	 * <li>load: load database from file</li>
	 * <li>list: show the database</li>
	 * <li>exit: exit the program</li>
	 * </ul>
	 * If command is "store", it saves database into a file
	 */
	public void run() {
		Scanner input = new Scanner(System.in);

		try {
			System.out.println("Controller started");
			System.out.println("Just type list|store|load|exit");
			while (true) {
				String serverCommand = input.nextLine();

				if ("store".equals(serverCommand)) {
					requestProcessor.storeToFile(Constant.SERVER_FILE);
					System.out.println("store ok");
				} else if ("load".equals(serverCommand)) {
					requestProcessor.loadFromFile(Constant.SERVER_FILE);
					System.out.println("load ok");
				} else if ("list".equals(serverCommand)) {
					System.out.println(requestProcessor.getFingerprintList());
				} else if ("exit".equals(serverCommand)) {
					System.exit(0);
				} else if ("".equals(serverCommand)) {
					System.out.println("An empty command");
				} else {
					System.err.println("command not found");
				}
			}
		} catch (Exception serverControllerException) {
			serverControllerException.printStackTrace();
		}
	}
}