package com.edsonveiga.jvpn.service;

import java.io.IOException;
import org.springframework.stereotype.Service;

import com.edsonveiga.jvpn.enums.Script;

@Service
public class MyServer {
	
	public static Process runScript(Script script) {
	    ProcessBuilder pb = new ProcessBuilder(script.getPath());
	    
	    try {
	        return pb.start();
	    } catch (IOException e) {
	        System.err.println("The server failed to run the script.");
	        e.printStackTrace();
	        return null;  // <-- Retorna null se falhar
	    }
	}
	
	public static Process runJava(Script script) {
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", script.getPath());
		
		try {
			return pb.start();
		} catch (IOException e) {
			System.err.println("The server failed to run the script.");
			e.printStackTrace();
			return null;
		}
		
	}

}
