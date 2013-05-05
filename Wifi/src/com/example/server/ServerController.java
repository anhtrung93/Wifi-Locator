/**
 * this file contains the controller of the server
 * it runs concurrently with clientThreads
 * it controls the server through manipulating its Process 
 */
package com.example.server;

import com.example.share.Constant;

import java.util.Scanner;



/**
 * 
 * @author bvuong93
 * 
 *         Class ServerController is based on the Thread Class. ServerController
 *         object is crearted to help control the Server.exe on the Server. This
 *         object allows the Server.exe to be stored/loaded database, to exit.
 *         It contains a MyProcessor object.
 * 
 */
public class ServerController implements Runnable {
	private final transient MyProcessor processor;

	/**
	 * Contructs a new ServerController with a given processor.
	 * 
	 * @param processor
	 *            the processor of the server
	 */
	public ServerController(final MyProcessor processor) {
		this.processor = processor;
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
				String command = input.nextLine();

				if ("store".equals(command)) {
					processor.storeToFile(Constant.SERVER_FILE);
					System.out.println("store ok");
				} else if ("load".equals(command)) {
					processor.loadFromFile(Constant.SERVER_FILE);
					System.out.println("load ok");
				} else if ("list".equals(command)) {
					System.out.println(processor.getFingerprintList());
				} else if ("exit".equals(command)) {
					System.exit(0);
				} else if ("".equals(command)) {
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