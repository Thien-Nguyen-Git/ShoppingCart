package com.shoppingapp.dao;

import java.util.List;

import com.shoppingapp.model.Items;

public interface ItemDAO {

	public List<Items> getAllItems();
	
	public Items getItemByName(String name);
	
	public boolean addItem(Items item);
}
