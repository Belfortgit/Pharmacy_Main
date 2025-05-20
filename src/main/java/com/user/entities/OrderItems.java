package com.user.entities;

public class OrderItems {

	private int id;
	private int orderId;
	private int drugId;
	private double quantity;
	private double price;
	private String drugName;
	
	public OrderItems() {
		
	}
	
	public OrderItems(int id, int orderId, int drugId, double quantity, double price,String drugName) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.drugId = drugId;
		this.quantity = quantity;
		this.price = price;
		this.drugName = drugName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
}
