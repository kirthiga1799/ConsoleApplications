package com.view;

import java.util.Scanner;

import com.controller.CarRentalController;

public class CarRentalMainView {
	 private Scanner scanner;

	    public CarRentalMainView() {
	        this.scanner = new Scanner(System.in);
	    }

	    public int displayMainMenu() {
	    	System.out.println("----------CAR RENTAL MANAGEMENT SYSTEM----------");
	    	System.out.println("1. Owner Menu");
	        System.out.println("2. Customer Menu");
	        System.out.println("3. Return Car");
	        System.out.println("4. Exit");
	        System.out.print("\nEnter your choice: ");
	        return scanner.nextInt();
	    }
	    public Scanner getScanner() {
	        return scanner;
	    }
	
	    
	    public static void main(String[] args) {
	        CarRentalController carRentalController = new CarRentalController();
	        carRentalController.start();
	    }
	

}
