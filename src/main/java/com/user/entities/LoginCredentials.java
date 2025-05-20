package com.user.entities;

public class LoginCredentials {
	
	private String userName;
	private String password;

	public LoginCredentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public LoginCredentials() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
