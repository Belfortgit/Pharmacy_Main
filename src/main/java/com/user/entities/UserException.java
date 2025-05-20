package com.user.entities;

public class UserException {
	
	String msg;
	String suggestion;
	String typeOfException;
	
	public UserException() {
		
	}
	public UserException(String msg, String suggestion, String typeOfException) {
		super();
		this.msg = msg;
		this.suggestion = suggestion;
		this.typeOfException = typeOfException;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getTypeOfException() {
		return typeOfException;
	}
	public void setTypeOfException(String typeOfException) {
		this.typeOfException = typeOfException;
	}
	
}
