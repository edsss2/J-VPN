package com.edsonveiga.jvpn.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Edson Veiga
 * 
 * Essa classe é responsavel por gerenciar as ações do usuário ao interagir com o script,
 * ou seja, aqui irá chegar as repostas do usuário, e serão repassadas as mensagens do
 * script para o usuário.
 * 
 * 
 */
public class PromptHandler extends Thread {

	private final Socket dialogSocket;
	private BufferedReader readerUser;
	private PrintWriter writerUser;
	
	public PromptHandler(Socket dialogSocket) {
		this.dialogSocket = dialogSocket;
		start();
	}
	

	@Override
	public void run() {
		try {
			readerUser = new BufferedReader(new InputStreamReader(dialogSocket.getInputStream()));
			writerUser = new PrintWriter(dialogSocket.getOutputStream(), true);
			
			writerUser.println("Script starting...");
			
			String message;
			while(true) {
				message = readerUser.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public PrintWriter getWriterUser() {
		return writerUser;
	}
}
