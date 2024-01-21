package com.model;

public class Car {

	private String carId;
	private String brand;
	private boolean available;

	public Car(String carId, String brand, boolean available) {
		this.carId = carId;
		this.brand = brand;
		this.available = available;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
