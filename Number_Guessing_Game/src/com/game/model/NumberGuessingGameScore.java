package com.game.model;

public class NumberGuessingGameScore {

	private String gameName;
	private int score;

	public NumberGuessingGameScore(String gameName, int score) {
		this.gameName = gameName;
		this.score = score;
	}

	public String getGameName() {
		return gameName;
	}

	public int getScore() {
		return score;
	}

}
