package com.user.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Payment {
	
	private String paymentId; //Combination of p+userid+orderid
	private int userId;
	private String userName;
	private int orderId;
	private String drugName;
	private LocalDate orderDate;
	private String status;
	private double totalAmount;
	private double amountPaid;
	private double amountPending;
	private String paymentStatus;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(String paymentId, int userId, String userName, int orderId, String drugName, LocalDate orderDate,
			String status, double totalAmount, double amountPaid, double amountPending, String paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.userId = userId;
		this.userName = userName;
		this.orderId = orderId;
		this.drugName = drugName;
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
		this.amountPaid = amountPaid;
		this.amountPending = amountPending;
		this.paymentStatus = paymentStatus;
	}
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public double getAmountPending() {
		return amountPending;
	}
	public void setAmountPending(double amountPending) {
		this.amountPending = amountPending;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}
