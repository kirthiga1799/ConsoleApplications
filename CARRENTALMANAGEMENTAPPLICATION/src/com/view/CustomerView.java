package com.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.model.Car;
import com.model.CarRentalManager;
import com.model.Customer;


public class CustomerView {
	
	private CarRentalManager carRentalManager;
    private Scanner scanner = new Scanner(System.in);

    public CustomerView(CarRentalManager carRentalManager) {
        this.carRentalManager = carRentalManager;
        this.scanner = new Scanner(System.in);
    }

    public void handleCustomerMenu() {
        int choice;
        do {
        	System.out.println("Customer Menu:");
            System.out.println("1. View Available Cars");
            System.out.println("2. Make Reservation");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
            case 1:
            	viewAvailableCars();
                break;
            case 2:
                makeReservation();
                break;
//            case 3:
//                System.out.print("Enter Reservation ID to return the car: ");
//                String reservationId = scanner.next();
//                carRentalManager.returnCar(reservationId);
//                break;
            case 4:
                System.out.println("Returning to the main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    } while (choice != 4);
    }

    private void makeReservation() {
    	System.out.println("\nEnter Reservation Details:");
        System.out.print("Reservation ID: ");
        String reservationId = scanner.next();
        System.out.print("Car ID: ");
        String carId = scanner.next();
        
        Car selectedCar = carRentalManager.getCarById(carId);
        if (selectedCar != null && selectedCar.isAvailable()) {
        	System.out.println("Customer ID: ");
        	String customerId = scanner.next();
            System.out.print("Start Date (YYYY-MM-DD): ");
            String startDateString = scanner.next();
            LocalDate startDate = LocalDate.parse(startDateString);

            System.out.print("End Date (YYYY-MM-DD): ");
            String endDateString = scanner.next();
            LocalDate endDate = LocalDate.parse(endDateString);

            Customer customer = carRentalManager.getCustomerById("CUST001");

            carRentalManager.makeReservation(reservationId, customer, selectedCar, startDate, endDate);
            //System.out.println("Reservation made successfully!");
        } else {
            System.out.println("Invalid car ID or the selected car is not available.");
        }
    }

    private void viewAvailableCars() {
        List<Car> availableCars = carRentalManager.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("No available cars at the moment.");
        } else {
            System.out.println("Available Cars:");
            for (Car car : availableCars) {
                System.out.println("Car ID: " + car.getCarId() + ", Brand: " + car.getBrand());
            }
        }
    }

}


