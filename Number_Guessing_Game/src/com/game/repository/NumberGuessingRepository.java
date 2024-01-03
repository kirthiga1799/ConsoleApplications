package com.game.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.game.model.NumberGuessingGameScore;

public class NumberGuessingRepository {
	private List<NumberGuessingGameScore> scores = new ArrayList<>();
	private int highestScore = 0;

	public void addScore(String gameName, int score) {
		NumberGuessingGameScore gameScore = new NumberGuessingGameScore(gameName, score);
		scores.add(gameScore);
		updateHighestScore(score);
	}

	public List<NumberGuessingGameScore> getScores() {
		return Collections.unmodifiableList(scores);
	}

	public int getHighestScore() {
		return highestScore;
	}

	private void updateHighestScore(int newScore) {
		highestScore = Math.max(highestScore, newScore);
	}
}
