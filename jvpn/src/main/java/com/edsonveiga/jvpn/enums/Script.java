package com.edsonveiga.jvpn.enums;

public enum Script {

	OPENVPN("./openvpn-script.sh");
	
	private final String path;
	
	Script(String path) {
		this.path = path;
	}
	
	public String getPath() {
        return path;
    }
}
