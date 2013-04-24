package com.example.server;


import java.util.Scanner;
import com.example.share.*;


public class ServerController implements Runnable {
	MyProcessor processor;
	public ServerController(MyProcessor processor) {
		this.processor = processor;
	}

	public void run() {
		Scanner input = new Scanner(System.in);

		try {
			while (true) {
				String command = input.nextLine();

				if (command.equals("store")) {
					processor.storeToFile(Constant.SERVER_FILE);
					System.out.println("store ok");
				} else if (command.equals("load")) {
					processor.loadFromFile(Constant.SERVER_FILE);					
				} else if (command.equals("list")) {
					System.out.println(processor.getFingerprintList());
				} else if (command.equals("exit")) {
					System.exit(0);
				} else if (command.equals("")) {
					System.out.println("An empty command");
				} else {
					System.err.println("command not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
