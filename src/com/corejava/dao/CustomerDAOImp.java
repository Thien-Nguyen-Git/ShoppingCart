package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.connection.ConnectionManager;
import com.shoppingapp.model.Customer;

public class CustomerDAOImp implements CustomerDAO {
	
	private Connection conn = ConnectionManager.getConnection();

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		
		try(Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from customers"); ){
			
			while(rs.next()) {
				String username = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone_number = rs.getString(4);
				String password = rs.getString(5);
				double balance = rs.getInt(6);
				
				// add to list
				Customer customer = new Customer(username, name, address, phone_number, password, balance);
				customers.add(customer);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		
		Customer customer = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from customers where username = ?")) {
			
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String cust_username = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone_number = rs.getString(4);
				String password = rs.getString(5);
				double balance = rs.getInt(6);
				
				customer = new Customer(cust_username, name, address, phone_number, password, balance);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return customer;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into customers values(?,?,?,?,?,?)");
			
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getAddress());
			pstmt.setString(4, customer.getPhone_number());
			pstmt.setString(5, customer.getPassword());
			pstmt.setDouble(6, customer.getBalance());
			
			int insert = pstmt.executeUpdate();
			
			if(insert > 0) {
				return true;
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Customer getCustomerByPassword(String password) {
		
		Customer customer = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from customers where password = ?")) {
			
			pstmt.setString(1, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String username = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone_number = rs.getString(4);
				String cust_password = rs.getString(5);
				double balance = rs.getInt(6);
				
				customer = new Customer(username, name, address, phone_number, cust_password, balance);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

	
}
