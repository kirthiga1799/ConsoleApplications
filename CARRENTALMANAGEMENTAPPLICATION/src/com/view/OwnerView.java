package com.view;

import java.util.List;
import java.util.Scanner;

import com.model.Car;
import com.model.CarRentalManager;
import com.model.Customer;
import com.model.Reservation;

public class OwnerView {
	
		private CarRentalManager carRentalManager;
	    private Scanner scanner = new Scanner(System.in);

	    public OwnerView(CarRentalManager carRentalManager) {
	        this.carRentalManager = carRentalManager;
	        this.scanner = new Scanner(System.in);
	    }

  
            public void handleOwnerMenu() {
                int choice;
                do {
                    System.out.println("Owner Menu:");
                    System.out.println("1. Add Car");
                    System.out.println("2. Add Customer");
                    System.out.println("3. View Reservations");
                    System.out.println("4. Available Cars");
                    System.out.println("5. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            addCar();
                            break;
                        case 2:
                            addCustomer();
                            break;
                        case 3:
                            viewReservations();
                            break;
                        case 4:
                            viewAvailableCars();  // Call the method to display available cars
                            break;
                        case 5:
                            System.out.println("Returning to the main menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } while (choice != 5);
            }
            private void addCar() {
                System.out.println("\nEnter Car Details:");
                System.out.print("Car ID: ");
                String carId = scanner.next();

                // Consume the newline character left in the buffer
                scanner.nextLine();

                System.out.print("Brand: ");
                String brand = scanner.nextLine();

                // Read availability as a string and parse to boolean
                System.out.print("Is Available (true/false): ");
                String availabilityInput = scanner.next().toLowerCase();
                boolean isAvailable = availabilityInput.equals("true");

                Car car = new Car(carId, brand, isAvailable);
                carRentalManager.addCar(car);

                System.out.println("Car added successfully!");
            }	

            private void addCustomer() {
                System.out.println("\nEnter Customer Details:");
                System.out.print("Customer ID: ");
                String customerId = scanner.next();

                // Consume the newline character left in the buffer
                scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Contact Number: ");
                String contactNumber = scanner.next();

                Customer customer = new Customer(customerId, name, contactNumber);
                carRentalManager.addCustomer(customer);

                System.out.println("Customer added successfully!");
            }
            private void viewReservations() {
                List<Reservation> reservations = carRentalManager.getAllReservations();

                if (reservations.isEmpty()) {
                    System.out.println("No reservations yet.");
                } else {
                    System.out.println("Reservations:");
                    for (Reservation reservation : reservations) {
                        System.out.println("Reservation ID: " + reservation.getReservationId() +
                                           ", Customer: " + reservation.getCustomer().getName() +
                                           ", Car: " + reservation.getCar().getBrand() +
                                           ", Start Date: " + reservation.getStartDate() +
                                           ", End Date: " + reservation.getEndDate());
                    }
                }
            }
            
            public void viewAvailableCars() {
                List<Car> availableCars = carRentalManager.getAvailableCars();
                if (availableCars.isEmpty()) {
                    System.out.println("No available cars at the moment.");
                } else {
                    System.out.println("Available Cars:");
                    for (Car car : availableCars) {
                        System.out.println("Car ID: " + car.getCarId() + ", Brand: " + car.getBrand() + ", Available: " + car.isAvailable());
                    }
                }
            }
}