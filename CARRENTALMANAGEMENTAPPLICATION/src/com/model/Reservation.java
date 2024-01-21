package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

	private String reservationId;
	private Customer customer;
	private Car car;
	private LocalDate startDate;
	private LocalDate endDate;
	 private double totalAmount;
	 
	 private static List<Reservation> allReservations = new ArrayList<>();


	 public Reservation(String reservationId, Customer customer, Car car, LocalDate startDate, LocalDate endDate, double totalAmount) {
	        this.reservationId = reservationId;
	        this.customer = customer;
	        this.car = car;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.totalAmount = totalAmount;
	        
	    }
	 
	 
	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public static List<Reservation> getAllReservations() {
		return getAllReservations();
	}
	
	 public static void addReservation(Reservation reservation) {
	        allReservations.add(reservation);
	    }


}
