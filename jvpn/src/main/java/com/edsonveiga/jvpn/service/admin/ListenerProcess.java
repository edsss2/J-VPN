package com.edsonveiga.jvpn.service.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListenerProcess extends Thread {

	private String line;
	private Process process;
	private BufferedReader reader;
	
	public ListenerProcess(Process process) {
		this.process = process;
	}
	
	
	@Override
	public void run() {
		reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		try {
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.err.println("The server failed to run the script.");
			e.printStackTrace();
		}
	}
	
}
