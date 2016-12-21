package com.restaurant.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.restaurant.entities.Item;
import com.restaurant.entities.RestaurantMenu;
import com.restaurant.exceptions.RestaurantServiceException;
import com.restaurant.util.ServiceUtil;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private SatisfactionResolver satisfactionSolver;
	
	@Override
	public double findMaximumSatisfaction(String datafilePath) throws Exception {
		satisfactionSolver.setRestaurantMenu(prepareRestaurantMenu(datafilePath));
		return satisfactionSolver.solve();
	}

	private RestaurantMenu prepareRestaurantMenu(String datafilePath) throws RestaurantServiceException {
		RestaurantMenu restaurantMenu = new RestaurantMenu();
		BufferedReader br = null; 
		List<Item> items = new LinkedList<Item>();
		try {
			Resource resource = new ClassPathResource(datafilePath);
			br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			String line = br.readLine();
			if(line == null){
				throw new RestaurantServiceException("Input file is empty");
			}
			String[] lineContent = line.split(" ");
			restaurantMenu.setCapacity(Integer.valueOf(lineContent[0].trim()));
			// maximum time capacity should be greater than 0.
			if(restaurantMenu.getCapacity() <= 0){
				throw new RestaurantServiceException("Invalid maximum time");
			}
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					double satisfactionValue = 0;
					double time = 0;
					try {
						lineContent = line.split(" ");
						satisfactionValue = Double.valueOf(lineContent[0].trim());
						time = Double.valueOf(Double.valueOf(lineContent[1].trim()));

						if(satisfactionValue < 0 || time < 0){
							throw new RestaurantServiceException("Invalid time/satisfactionValue");
						}
					} catch (NumberFormatException exc) {
						throw new RestaurantServiceException("Invalid time/satisfactionValue", exc);
					}
					items.add(new Item(satisfactionValue, time));
				}
			}
			restaurantMenu.setItems(items);
		} catch (IOException exc) {
			throw new RestaurantServiceException("File not found in the specified location", exc);
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		ServiceUtil.validateCapacity(restaurantMenu);
		return restaurantMenu;
	}
	
}
