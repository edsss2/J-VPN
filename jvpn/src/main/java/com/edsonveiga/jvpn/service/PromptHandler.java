package com.edsonveiga.jvpn.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Edson Veiga
 * 
 * Essa classe é responsavel por gerenciar as ações do usuário ao
 * interagir com o script, ou seja, aqui irá chegar as repostas do
 * usuário, e serão repassadas as mensagens do script para o usuário.
 * 
 * 
 */
public class PromptHandler extends Thread {

	private BufferedReader readerScript;
	private PrintWriter writerScript;
	private Process process;

	public PromptHandler(Process process) {
		this.process = process;
		start();
	}

	@Override
	public void run() {
		// Esse é o leitor do que o script vai dizer
		readerScript = new BufferedReader(new InputStreamReader(process.getInputStream()));

		// E esse é onde vão as respostas ao script
		writerScript = new PrintWriter(process.getOutputStream(), true);
		
		String line;
		
		while(process.isAlive()) {
			try {
				line = readerScript.readLine();
				if(line == null) {
					line = "Press Enter to run the script again.";
				} 
				
				System.out.println(line);
				
			} catch (IOException e) {
				System.err.println("The connection may have been closed.");
				e.printStackTrace();
			}
			
			
		}
		
	}


	public PrintWriter getWriter() {
		return writerScript;
	}
}
