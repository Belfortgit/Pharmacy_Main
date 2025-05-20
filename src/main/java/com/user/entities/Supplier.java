package com.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Supplier {

	@Id
	private int supplierId;
	private String supplierName;
	private Long contact;
	private String address;
	
	public Supplier() {
		
	}
	
	public Supplier(int supplierId, String supplierName, Long contact, String address) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.contact = contact;
		this.address = address;
	}
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
