package com.restaurant.service;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurant.exceptions.RestaurantServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceImplTest {

	@Autowired
	RestaurantServiceImpl restaurantService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testMaximumSatisfaction_with_3Dishes() throws Exception {
		double satisfaction = restaurantService.findMaximumSatisfaction("test2.txt");
		assertEquals(63,satisfaction,0);
	}

	@Test
	public void testMaximumSatisfaction_with_5Dishes() throws Exception {
		double satisfaction = restaurantService.findMaximumSatisfaction("test1.txt");
		assertEquals(155,satisfaction,0);
	}
	
	@Test
	public void testWithEmptyfile() throws Exception {
		thrown.expect(RestaurantServiceException.class);
		thrown.expectMessage("Input file is empty");

		restaurantService.findMaximumSatisfaction("testwithemptyfile.txt");
	}

	@Test
	public void testWithInvalidContent() throws Exception {
		thrown.expect(RestaurantServiceException.class);
		thrown.expectMessage("Invalid time/satisfactionValue");

		restaurantService.findMaximumSatisfaction("test3.txt");
	}

	@Test
	public void testWithInvalidMaxTime() throws Exception {
		thrown.expect(RestaurantServiceException.class);
		thrown.expectMessage("Invalid maximum time");
		
		restaurantService.findMaximumSatisfaction("test4.txt");
	}
	
	@Test
	public void testLeaseMaximumTime() throws Exception {
		thrown.expect(RestaurantServiceException.class);
		thrown.expectMessage("Invalid maximum time ");
		
		restaurantService.findMaximumSatisfaction("test6.txt");
	}

}
