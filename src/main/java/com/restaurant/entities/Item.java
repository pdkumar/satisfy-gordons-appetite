package com.restaurant.entities;

/**
 * 
 * @author Dilip Kumar
 *
 */
public class Item {
	
	private double satisfactionValue;
	private double time;
	
	public Item(double satisfactionValue, double time) {
		this.satisfactionValue = satisfactionValue;
		this.time = time;
	}
	
	public double getSatisfactionValue() {
		return satisfactionValue;
	}

	public void setSatisfactionValue(double satisfactionValue) {
		this.satisfactionValue = satisfactionValue;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
	public double getRatio() {
		return satisfactionValue / time;
	}
	
}
