package com.model;

public class Customer {
	private String customerId;
	private String name;
	private String contactNumber;

	public Customer(String customerId, String name, String contactNumber) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.contactNumber = contactNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
