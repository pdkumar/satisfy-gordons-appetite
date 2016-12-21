package com.restaurant.util;

import java.util.List;

import com.restaurant.entities.Item;
import com.restaurant.entities.RestaurantMenu;
import com.restaurant.exceptions.RestaurantServiceException;

public class ServiceUtil {
	
	public static void validateCapacity(RestaurantMenu menu) throws RestaurantServiceException{
		int capacity=menu.getCapacity();
		List<Item> itemList=menu.getItems();
		boolean flag=false;
		for (Item item : itemList) {
			if(item.getTime()<capacity){
				flag=true;
			}
		}
		if(!flag){
			throw new RestaurantServiceException("Invalid maximum time ");
		}
	}
	
}
