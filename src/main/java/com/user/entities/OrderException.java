package com.user.entities;

public class OrderException {
	
	
	private String msg;
	private String suggestion;
	private String typeOfException;

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
	
	public OrderException(String msg, String suggestion,String typeOfException) {
		super();
		this.msg = msg;
		this.suggestion = suggestion;
		this.typeOfException = typeOfException;
	}
	public OrderException() {
		super();
		// TODO Auto-generated constructor stub
	}
}
