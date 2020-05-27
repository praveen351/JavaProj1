package com.OAS.model;

public class UserAuthenticate {
	private String Email;
	private String Password;

	public UserAuthenticate() {

	}

	public UserAuthenticate(String email, String password) {
		Email = email;
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
