package com.game.view;

import java.util.Scanner;

public class NumberGuessingView {

	private Scanner scanner = new Scanner(System.in);

	public int takeUserInput() {
		System.out.print("Enter your guess: ");
		return scanner.nextInt();
	}

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public void displayAttemptsLeft(int attemptsLeft) {
		System.out.println("Attempts left: " + attemptsLeft);
	}

	public void displayTimeRemaining(long timeRemainingMillis) {
		System.out.println("Time remaining: " + (timeRemainingMillis / 1000) + " seconds");
	}

	public void displayRange(int minRange, int maxRange) {
		System.out.println("Valid Range: " + minRange + " to " + maxRange);
	}
}
