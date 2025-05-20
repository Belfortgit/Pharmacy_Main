package com.user.entities;

import java.time.LocalDate;

public class Orders {
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	private int orderId;
	private String drugName;
	private int userId;
	private String userName;
	private LocalDate orderDate;
	private String status;
	private double totalAmount;
	
	
	public Orders() {
		
	}

	public Orders(int orderId,String drugName, int userId,String userName, LocalDate orderDate, String status,double totalAmount) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.status = status;
		this.drugName = drugName;
		this.userName = userName;
		this.totalAmount = totalAmount;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
