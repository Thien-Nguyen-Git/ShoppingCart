package com.shoppingapp.dao;

import java.util.List;

import com.shoppingapp.model.Customer;

public interface CustomerDAO {

	public List<Customer> getAllCustomers();
	
	public Customer getCustomerByUsername(String username);
	
	public Customer getCustomerByPassword(String password);
	
	public boolean addCustomer(Customer customer);
}
