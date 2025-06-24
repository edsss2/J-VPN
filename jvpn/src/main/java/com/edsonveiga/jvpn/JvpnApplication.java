package com.edsonveiga.jvpn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edsonveiga.jvpn.enums.Script;
import com.edsonveiga.jvpn.service.ScriptsLinux;

@SpringBootApplication
public class JvpnApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvpnApplication.class, args);
		
		ScriptsLinux.runScript(Script.SCRIPT_TEST);
	}

}
