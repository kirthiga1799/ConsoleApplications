package com.game.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.game.controller.NumberGuessingController;
import com.game.model.NumberGuessingGameScore;
import com.game.model.NumberGuessingModel;
import com.game.repository.NumberGuessingRepository;

public class NumberGuessingGameApp {

	private static NumberGuessingRepository repository = new NumberGuessingRepository();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			playGame(scanner);

			System.out.print("Do you want to play again? (yes/no): ");
			String response = scanner.next().toLowerCase();
			if (!response.equals("yes")) {
				displayScores();
				displayHighestScore();
				break;
			}
		}

		scanner.close();
		System.out.println("Thanks for playing!!");
	}

	private static void playGame(Scanner scanner) {
		NumberGuessingModel model = new NumberGuessingModel();
		NumberGuessingView view = new NumberGuessingView();
		NumberGuessingController controller = new NumberGuessingController(model, view);

		System.out.println("Select a game level:");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		System.out.println("Enter your choice :");
		int selectedLevel = scanner.nextInt();

		int minRange, maxRange, attempts;
		long timeLimitMillis;

		switch (selectedLevel) {
		case 1:
			System.out.println("-----EASY LEVEL-----");
			minRange = 1;
			maxRange = 100;
			attempts = 15;
			timeLimitMillis = 60000;
			break;
		case 2:
			System.out.println("-----MEDIUM LEVEL-----");
			minRange = 1;
			maxRange = 250;
			attempts = 12;
			timeLimitMillis = 45000;
			break;
		case 3:
			System.out.println("-----HARD LEVEL-----");
			minRange = 1;
			maxRange = 350;
			attempts = 10;
			timeLimitMillis = 30000;
			break;
		default:
			System.out.println("Invalid level selected. Defaulting to Medium level.");
			minRange = 1;
			maxRange = 100;
			attempts = 12;
			timeLimitMillis = 45000;
			break;
		}

		model.setGameParameters(minRange, maxRange, attempts, timeLimitMillis);

		System.out.println("Total time for the game: " + (timeLimitMillis / 1000) + " seconds");

		controller.startGame(minRange, maxRange, attempts);

		int currentScore = model.getScore();
		String gameName = "Game " + String.format("%02d", repository.getScores().size() + 1); 
		repository.addScore(gameName, currentScore);

		if (currentScore > repository.getHighestScore()) {
			System.out.println("New High Score: " + currentScore);
		}
	}

	private static void displayScores() {

		List<NumberGuessingGameScore> sortedScores = new ArrayList<>(repository.getScores());
		sortedScores.sort(Comparator.comparingInt(NumberGuessingGameScore::getScore).reversed());

		System.out.println("Game Scores (Descending Order):");
		for (NumberGuessingGameScore gameScore : sortedScores) {
			System.out.println(gameScore.getGameName() + " Score: " + gameScore.getScore());
		}
	}

	private static void displayHighestScore() {
		System.out.println("Current Highest Score: " + repository.getHighestScore());
	}
}
