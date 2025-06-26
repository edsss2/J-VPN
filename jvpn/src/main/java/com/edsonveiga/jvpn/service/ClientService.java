package com.edsonveiga.jvpn.service;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public interface ClientService {

	File addNewClient(String nameClient);
}
