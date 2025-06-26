package com.edsonveiga.jvpn.service.impl;

import java.io.File;

import com.edsonveiga.jvpn.service.ClientService;
import com.edsonveiga.jvpn.service.admin.MyServer;

public class ClientServiceImpl implements ClientService {

	@Override
	public File addNewClient(String name) {
		if(MyServer.existingClient(name)) {
			System.out.println("Digite outro nome");
			return null;
		} else {
			return null;
		}
	}
}
