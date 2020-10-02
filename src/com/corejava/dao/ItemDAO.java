package com.corejava.dao;

import java.util.List;

import com.corejava.model.Items;

public interface ItemDAO {

	public List<Items> getAllItems();
	
	public Items getItemByName(String name);
	
	public boolean addItem(Items item);
}
