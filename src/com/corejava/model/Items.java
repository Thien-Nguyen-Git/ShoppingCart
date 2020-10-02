package com.corejava.model;

public class Items {

	private String id;
	private String name;
	private double price;
	private String cust_username;
	
	public Items() {
		this("N/A", "N/A", 0, "N/A");
	}

	public Items(String id, String name, double price, String cust_username) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.cust_username = cust_username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCust_username() {
		return cust_username;
	}

	public void setCust_username(String cust_username) {
		this.cust_username = cust_username;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", cust_username=" + cust_username + "]";
	}

}
