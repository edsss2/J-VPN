package com.edsonveiga.jvpn.service.admin;

import java.io.PrintWriter;

public class RespondScript extends Thread {

	private Process process;
	private PrintWriter writer;
	private String response;
	
	public RespondScript(Process process, String response) {
		this.process = process;
		this.response = response;
	}
	
	@Override
	public void run() {
		writer = new PrintWriter(process.getOutputStream(), true);
		writer.println(response);
	}
}
