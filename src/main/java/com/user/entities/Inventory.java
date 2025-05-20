package com.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class Inventory {

	private int drugId;
	private String drugName;
	private String description;
	private double quantity;
	private double price;
	private int supplierId;
	
	public Inventory() {
		
	}
	public Inventory(int drugId, String drugName, String description, double quantity, double price, int supplierId) {
		super();
		this.drugId = drugId;
		this.drugName = drugName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.supplierId = supplierId;
	}
	
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
}
