package com.edsonveiga.jvpn.service.impl;

import java.io.File;

import com.edsonveiga.jvpn.enums.Script;
import com.edsonveiga.jvpn.service.ClientService;
import com.edsonveiga.jvpn.service.admin.ListenerProcess;
import com.edsonveiga.jvpn.service.admin.MyServer;
import com.edsonveiga.jvpn.service.admin.RespondScript;

public class ClientServiceImpl implements ClientService {

	@Override
	public File addNewClient(String name) {
		Process process;
		if(MyServer.existingClient(name)) {
			System.out.println("Digite outro nome");
			return null;
		} else {
			process = MyServer.runScript(Script.OPENVPN);
			
			ListenerProcess listener = new ListenerProcess(process);
			listener.start();
			try {
				listener.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RespondScript respond = new RespondScript(process, "1");
			respond.start();
			
			ListenerProcess listener2 = new ListenerProcess(process);
			listener2.start();
			try {
				listener.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RespondScript respond2 = new RespondScript(process, name);
			respond2.start();
		}
		
		return MyServer.findFileOvpn(name);
	}
	
}
