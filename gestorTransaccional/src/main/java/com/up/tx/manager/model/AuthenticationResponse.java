package com.up.tx.manager.model;

public class AuthenticationResponse {
    
	
	private final String token;
	private final String test;
	
    public AuthenticationResponse(String token, String test) {
        this.token = token;
        this.test = test;
    }

    public String getToken() {
        return token;
    }

	public String getTest() {
		return test;
	}
    
}
