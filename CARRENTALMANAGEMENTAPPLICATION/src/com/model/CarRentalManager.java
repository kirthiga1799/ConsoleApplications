package com.model;

import java.time.LocalDate;
import com.repository.CarRentalrepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalManager {
	private CarRentalrepository repository;
	private List<Car> cars;
	private List<Customer> customers;
	private List<Reservation> reservations;

	public CarRentalManager() {
		this.cars = new ArrayList<>();
		this.customers = new ArrayList<>();
		this.reservations = new ArrayList<>();
		this.repository = new CarRentalrepository();
	}

	public void addCar(Car car) {
		repository.addCar(car);
	}

	public void addCustomer(Customer customer) {
		repository.addCustomer(customer);
	}

	public void makeReservation(Reservation reservation) {
		repository.makeReservation(reservation);
	}

	public void returnCar(String reservationId) {
		repository.returnCar(reservationId);
	}
//	    public void addCar(Car car) {
//	        cars.add(car);
//	        System.out.println("INSERT INTO cars (carId, brand, available) VALUES ('" + car.getCarId() + "', '" + car.getBrand() + "', " + car.isAvailable() + ");");
//	    }
//
//	    public void addCustomer(Customer customer) {
//	        customers.add(customer);
//	    }

	public List<Car> getAvailableCars() {
		List<Car> availableCars = new ArrayList<>();
		for (Car car : cars) {
			if (car.isAvailable()) {
				availableCars.add(car);
			}
		}
		return availableCars;
	}

	public void viewAvailableCars() {
		List<Car> availableCars = getAvailableCars();
		if (availableCars.isEmpty()) {
			System.out.println("No available cars at the moment.");
		} else {
			System.out.println("Available Cars:");
			for (Car car : availableCars) {
				System.out.println("Car ID: " + car.getCarId() + ", Brand: " + car.getBrand() + ", Available: "
						+ car.isAvailable());
			}
		}
	}

	public List<Reservation> getReservationsByCustomer(Customer customer) {
		List<Reservation> customerReservations = new ArrayList<>();
		for (Reservation reservation : reservations) {
			if (reservation.getCustomer().equals(customer)) {
				customerReservations.add(reservation);
			}
		}
		return customerReservations;
	}

	private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate) {

		List<Reservation> carReservations = getReservationsForCar(car);

		for (Reservation reservation : carReservations) {
			if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())) {

				return false;
			}
		}
		return true;
	}

	private List<Reservation> getReservationsForCar(Car car) {
		List<Reservation> carReservations = new java.util.ArrayList<>();

		for (Reservation reservation : reservations) {
			if (reservation.getCar().equals(car)) {
				carReservations.add(reservation);
			}
		}

		return carReservations;
	}

	public void returnCar(String reservationId, String carId, String customerId) {
		Reservation reservation = getReservationById(reservationId);

		if (reservation != null) {
			Car car = getCarById(carId);
			Customer customer = getCustomerById(customerId);

			if (car != null && customer != null) {
				car.setAvailable(true);
				reservations.remove(reservation);

				double totalAmount = reservation.getTotalAmount();
				System.out.println("Car returned successfully by customer " + customer.getName() + "! Payment Amount: $"
						+ totalAmount);
			} else {
				System.out.println("Car, customer, or reservation not found. Unable to return the car.");
			}
		} else {
			System.out.println("Reservation not found. Unable to return the car.");
		}
	}

	public Reservation getReservationById(String reservationId) {
		for (Reservation reservation : reservations) {
			if (reservation.getReservationId().equals(reservationId)) {
				return reservation;
			}
		}
		return null;
	}

	public Car getCarById(String carId) {
		for (Car car : cars) {
			if (car.getCarId().equals(carId)) {
				return car;
			}
		}
		return null;
	}

	public Customer getCustomerById(String customerId) {
		for (Customer customer : customers) {
			if (customer.getCustomerId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	public void makeReservation(String reservationId, Customer customer, Car car, LocalDate startDate,
			LocalDate endDate) {
		if (isCarAvailable(car, startDate, endDate)) {
			double totalAmount = calculateTotalAmount(startDate, endDate); // Add a method to calculate total amount
			Reservation reservation = new Reservation(reservationId, customer, car, startDate, endDate, totalAmount);
			reservations.add(reservation);
			car.setAvailable(false);
			System.out.println("Reservation made successfully! Total Amount To This Ride Is : $" + totalAmount);
		} else {
			System.out.println("The selected car is not available for the specified period.");
		}
	}

	private double calculateTotalAmount(LocalDate startDate, LocalDate endDate) {
		long days = startDate.until(endDate).getDays();
		return 1000 * days;
	}

	public void displayAvailableCars() {
		List<Car> availableCars = getAvailableCars();
		if (!availableCars.isEmpty()) {
			System.out.println("No available cars at the moment.");
		} else {
			System.out.println("Available Cars:");
			for (Car car : availableCars) {
				System.out.println("Car ID: " + car.getCarId() + ", Brand: " + car.getBrand() + ", Available: "
						+ car.isAvailable());
			}
		}
	}

//	    public void viewAvailableCars() {
//	        List<Car> availableCars = getAvailableCars();
//	        if (availableCars.isEmpty()) {
//	            System.out.println("No available cars at the moment.");
//	        } else {
//	            System.out.println("Available Cars:");
//	            for (Car car : availableCars) {
//	                System.out.println("Car ID: " + car.getCarId() + ", Brand: " + car.getBrand() + ", Available: " + car.isAvailable());
//	            }
//	        }
//	    }

	public static List<Reservation> getAllReservations() {
		return Reservation.getAllReservations();
	}
}
