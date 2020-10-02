package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.connection.ConnectionManager;
import com.shoppingapp.model.Items;

public class ItemDAOImp implements ItemDAO {

	private Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<Items> getAllItems() {
		List<Items> items = new ArrayList<Items>();
		
		try(Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from items"); ){
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				double price = rs.getInt(3);
				String cust_username = rs.getString(4);
				
				// add to list
				Items item = new Items(id, name, price, cust_username);
				items.add(item);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}

	@Override
	public Items getItemByName(String name) {
		Items item = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from items where name = ?")) {
			
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				String item_name = rs.getString(2);
				double price = rs.getInt(3);
				String cust_username = rs.getString(4);
				
				item = new Items(id, item_name, price, cust_username);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return item;
	}

	@Override
	public boolean addItem(Items item) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into items values(?,?,?,?)");
			
			pstmt.setString(1, item.getId());
			pstmt.setString(2, item.getName());
			pstmt.setDouble(3, item.getPrice());
			pstmt.setString(4, item.getCust_username());
			
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

}
