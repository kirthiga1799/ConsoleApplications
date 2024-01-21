package com.controller;

import java.util.Scanner;

import com.model.CarRentalManager;
import com.repository.CarRentalrepository;
import com.view.CarRentalMainView;
import com.view.CustomerView;
import com.view.OwnerView;

public class CarRentalController {
	
	private CarRentalManager carRentalManager;
    private CarRentalMainView mainView;
    private OwnerView ownerView;
    private CustomerView customerView;

    public CarRentalController() {
        CarRentalrepository carRentalRepository = new CarRentalrepository();
        this.carRentalManager = new CarRentalManager();
        this.mainView = new CarRentalMainView();
        this.ownerView = new OwnerView(carRentalManager);
        this.customerView = new CustomerView(carRentalManager);
    }

    public void start() {
    	int choice;
        do {
            choice = mainView.displayMainMenu();
            switch (choice) {
                case 1:
                    ownerView.handleOwnerMenu();
                    break;
                case 2:
                    customerView.handleCustomerMenu();
                    break;
                case 3:
                    returnCar();
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);
    }
    
    
    private void returnCar() {
        System.out.print("Enter Reservation ID to return the car: ");
        String reservationId = mainView.getScanner().next();

        System.out.print("Enter the car ID: ");
        String carId = mainView.getScanner().next();

        System.out.print("Enter the customer ID: ");
        String customerId = mainView.getScanner().next();

        carRentalManager.returnCar(reservationId, carId, customerId);
        System.out.println();
    }
    
 
}
