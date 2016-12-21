package com.restaurant.entities;

import java.util.List;

public class RestaurantMenu {
	
	private int capacity;
	private List<Item> items;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
