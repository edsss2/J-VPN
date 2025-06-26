package com.edsonveiga.jvpn.service;

import java.util.Scanner;

import com.edsonveiga.jvpn.enums.Script;

public class Connection {

	public static void waitForEnter() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Press Enter to continue...");
		while(true) {
			waitForEnter(scn);
		}
		
	}
	
	private static void waitForEnter(Scanner scn) {
		scn.nextLine();
		launchMenuHandler(Script.SCRIPT_TEST);
	}
	
	private static void launchMenuHandler(Script script) {
		Process process;
		
		if(script.getPath().contains(".jar")) {
			process = MyServer.runJava(script);
		} else {
			process = MyServer.runScript(script);
		}
		
		PromptHandler ph = new PromptHandler(process);
		Scanner scn = new Scanner(System.in);
		String message;
		
		while(ph.isAlive()) {
			message = scn.nextLine();
			ph.getWriter().println(message);
		}
	}
}
