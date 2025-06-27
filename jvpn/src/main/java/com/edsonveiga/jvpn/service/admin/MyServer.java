package com.edsonveiga.jvpn.service.admin;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.edsonveiga.jvpn.enums.Script;

public class MyServer {
	
	private final static String USER_HOME = System.getProperty("user.home");
	
	public static Process runScript(Script script) {
	    ProcessBuilder pb = new ProcessBuilder("/home/edson/" + script.getPath());
	    
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
	
	public static File findFileOvpn (String name) {
		String nameToFind = normalizeOvpnFileName(name);
		
		File file = new File(USER_HOME + "/" + nameToFind);
		if(file.exists() && file.isFile()) {
			return file;
		} else {
			throw new IllegalArgumentException("File not found");
		}
	}
	
	public static boolean existingClient(String name) {
		String nameToFind = normalizeOvpnFileName(name);
        File file = new File(System.getProperty("user.home") + "/" + nameToFind);

        return file.exists() && file.isFile();
	}
	
	private static String normalizeOvpnFileName(String inputName) {
		
	    if (inputName == null || inputName.isBlank()) {
	        throw new IllegalArgumentException("File name cannot be empty");
	    }

	    if (inputName.toLowerCase().endsWith(".ovpn")) {
	        return inputName;
	    }

	    return inputName + ".ovpn";
	}

}
