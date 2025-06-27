package com.edsonveiga.jvpn;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edsonveiga.jvpn.service.ClientService;
import com.edsonveiga.jvpn.service.admin.Connection;
import com.edsonveiga.jvpn.service.impl.ClientServiceImpl;

@SpringBootApplication
public class JvpnApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(JvpnApplication.class, args);
		ClientService cs = new ClientServiceImpl();
		File file = cs.addNewClient("edson");
		System.out.println(file.getAbsolutePath());
		
		Connection.waitForEnter();
		

	}

}
