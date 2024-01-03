package com.game.controller;

import com.game.model.NumberGuessingModel;
import com.game.view.NumberGuessingView;

public class NumberGuessingController {
	private NumberGuessingModel model;
	private NumberGuessingView view;

	public NumberGuessingController(NumberGuessingModel model, NumberGuessingView view) {
		this.model = model;
		this.view = view;
	}

	public void startGame(int min, int max, int attempts) {
		model.generateNewNumber(min, max, attempts);

		while (model.getAttemptsLeft() > 0 && !model.isTimeUp()) {
			view.displayAttemptsLeft(model.getAttemptsLeft());

			view.displayRange(min, max);

			int guess = view.takeUserInput();
			String result = model.checkGuess(guess);
			view.displayMessage(result);

			view.displayTimeRemaining(model.getTimeLimitMillis() - (System.currentTimeMillis() - model.getStartTime()));

			if (result.equals("Correct")) {
				view.displayMessage("Congratulations! \nYou guessed the correct number.\nYOU WIN THE GAME!!!");
				model.updateScore();
				view.displayMessage("Your Score: " + model.getScore());
				return;
			}

			if (model.isTimeUp()) {
				view.displayMessage("Sorry! You ran out of time.\nThe correct number was: " + model.getActualNumber());
				view.displayMessage("Your Score: " + model.getScore());
				return;
			}
		}

		view.displayMessage("Sorry! You ran out of attempts.\nThe correct number was: " + model.getActualNumber()
				+ ". YOU LOSE THE GAME.");
		view.displayMessage("Your Score: " + model.getScore());
	}
}
