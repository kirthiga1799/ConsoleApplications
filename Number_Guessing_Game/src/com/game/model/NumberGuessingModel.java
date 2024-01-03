package com.game.model;

import java.util.Random;

public class NumberGuessingModel {
	private int actualNumber;
	private int attemptsLeft;
	private int minRange;
	private int maxRange;
	private long startTime;
	private long timeLimitMillis = 20000;
	private int score;

	public void generateNewNumber(int min, int max, int attempts) {
		Random random = new Random();
		actualNumber = random.nextInt(max - min + 1) + min;
		attemptsLeft = attempts;
		minRange = min;
		maxRange = max;
		startTime = System.currentTimeMillis();
		score = 0;
	}

	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	public String checkGuess(int guess) {
		attemptsLeft--;

		if (guess < minRange || guess > maxRange) {
			return "Out of Range";
		}

		if (guess == actualNumber) {
			return "Correct";
		} else if (guess < actualNumber) {
			return "Too Low";
		} else {
			return "Too High";
		}
	}

	public boolean isTimeUp() {
		return System.currentTimeMillis() - startTime > timeLimitMillis;
	}

	public int getActualNumber() {
		return actualNumber;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getTimeLimitMillis() {
		return timeLimitMillis;
	}

	public void setTimeLimitMillis(long timeLimitMillis) {
		this.timeLimitMillis = timeLimitMillis;
	}

	public void setGameParameters(int minRange, int maxRange, int attempts, long timeLimitMillis) {
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.attemptsLeft = attempts;
		this.timeLimitMillis = timeLimitMillis;
		this.startTime = System.currentTimeMillis();
	}

	public int getScore() {
		return score;
	}

	public void updateScore() {
		score += (attemptsLeft * 100) + (int) ((timeLimitMillis - (System.currentTimeMillis() - startTime)) / 1000);
	}
}
