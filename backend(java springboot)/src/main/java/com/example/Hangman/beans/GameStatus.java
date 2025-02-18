package com.example.Hangman.beans;

import java.util.HashSet;

public class GameStatus {
	private String progress;
	private int attemptsLeft;

	private boolean gameOver;
	private String hangmanStateFigure;
	private String word;
	private HashSet<Character> guessedLetters;

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public void setAttemptsLeft(int attemptsLeft) {
		this.attemptsLeft = attemptsLeft;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setHangmanStateFigure(String hangmanStateFigure) {
		this.hangmanStateFigure = hangmanStateFigure;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setGuessedLetters(HashSet<Character> guessedLetters) {
		this.guessedLetters = guessedLetters;
	}

	public HashSet<Character> getGuessedLetters() {
		return guessedLetters;
	}

	public String getWord() {
		return word;
	}

	public String getProgress() {
		return progress;
	}

	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public String getHangmanFigureState() {
		return hangmanStateFigure;
	}

	
	public void resetGame() {
		guessedLetters.clear();
		progress = "";
		attemptsLeft = 10;
		gameOver = false;
		hangmanStateFigure = "nothing";
	}
}