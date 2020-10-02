package com.corejava.model;

public class Customer {

	private String username;
	private String name;
	private String password;
	private String address;
	private String phone_number;
	private double balance;
	
	public Customer() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", 0);
	}

	public Customer(String username, String name, String address, String phone_number, String password, double balance) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone_number = phone_number;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", name=" + name + ", password=" + password + ", address=" + address
				+ ", phone_number=" + phone_number + ", balance=" + balance + "]";
	}

}
