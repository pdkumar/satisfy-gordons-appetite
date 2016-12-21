package com.restaurant.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.service.RestaurantService;

/**
 * 
 * @author Dilip Kumar
 *
 */
@RestController
public class RestaurantController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	RestaurantService restaurantService;
	
	@RequestMapping("/")
	public ResponseEntity<String> findMaxValue(){
		ResponseEntity<String> responseEntity=null;
		try {
			responseEntity=new ResponseEntity<String>("Maximum Satisfaction value for given time  --->  "+String.valueOf(restaurantService.findMaximumSatisfaction("data.txt")), HttpStatus.OK);
		} catch (Exception exc) {
			logger.error(exc.getMessage(), exc);
			responseEntity=new ResponseEntity<String>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return responseEntity;
	}
	
}
