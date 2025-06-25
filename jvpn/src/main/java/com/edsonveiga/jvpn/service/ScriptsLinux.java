package com.edsonveiga.jvpn.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.edsonveiga.jvpn.enums.Script;

@Service
public class ScriptsLinux {

	public static void runScript(Script script) {
		ProcessBuilder pb = new ProcessBuilder();
		
		//Executa com os comandos devidos se for do tipo jar
		if(script.getPath().contains(".jar")) {
			pb.command("java", "-jar", script.getPath());
		} else {
			pb.command("/home/edson/" + script.getPath());
		}
		

		try {
			//Executa o PRocessBuilder e armazena o processo
			Process process = pb.start();

			// Ler saída do script
	        BufferedReader outputReader = new BufferedReader(
	                new InputStreamReader(process.getInputStream()));

	        // Escrever na entrada (stdin)
	        BufferedWriter inputWriter = new BufferedWriter(
	                new OutputStreamWriter(process.getOutputStream()));
	        
			// Ler erros do script
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			
	        while(process.waitFor() != 0) {
	        	launchMenuHandler(outputReader, inputWriter);
	        }
	        printError(errorReader);
	        
			// Esperar o processo terminar
	        
			int exitCode = process.waitFor();
			System.out.println("Exit Code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void launchMenuHandler(BufferedReader outputReader, BufferedWriter inputWriter) {

		Scanner scn = new Scanner(System.in);


		new Thread(() -> {
			String line;

			try {
				
				while ((line = outputReader.readLine()) != null) {
					System.out.println(line);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}).start();
		
		new Thread(() -> {
			String input;
			
			try {
				input = scn.next();
				inputWriter.write(input + "\n");
				inputWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
		
	}
	
	private static void printError(BufferedReader error) {
		String line;
		try {
			while ((line = error.readLine()) != null) {
				System.err.println("[Erro] " + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
