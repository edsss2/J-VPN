package com.edsonveiga.jvpn.enums;

public enum Script {

	OPENVPN("./openvpn-script.sh"),
	SCRIPT_TEST("Z:\\Desktop\\script.jar");
	
	private final String path;
	
	Script(String path) {
		this.path = path;
	}
	
	public String getPath() {
        return path;
    }
}
