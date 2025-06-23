package com.edsonveiga.jvpn.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.edsonveiga.jvpn.enums.Script;

@Service
public class ScriptsLinux {
	
	private void runScript() {
		ProcessBuilder pb = new ProcessBuilder(Script.OPENVPN.getPath());
		
		try {
			Process process = pb.start();
			
			String line;
			
			// Ler erros do script
            BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            while ((line = errorReader.readLine()) != null) {
                System.err.println("[Erro] " + line);
            }
            
         // Esperar o processo terminar
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
            
		} catch (IOException | InterruptedException e) {
            e.printStackTrace();
		}
	}
}
