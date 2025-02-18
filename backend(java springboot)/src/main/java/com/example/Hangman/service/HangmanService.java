package com.example.Hangman.service;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashSet;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.Hangman.beans.GameStatus;



@Service
public class HangmanService {

	private String word;
	private StringBuilder progress;
	private HashSet<Character> guessedLetters;
	private int attemptsLeft;
	private GameStatus gameStatus = new GameStatus();
	
	public String getProgress() {
		return progress.toString();
	}

	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	public HangmanService() {
		startNewGame();
	}
	
	//method for starting new game
	public void startNewGame() {
		
		//array of words:
		String[] words = { "Pasta", "Bread", "Soup", "Spaghetti", "Rice", "Fish", "Curry" };
		Random random = new Random();
		this.word = words[random.nextInt(words.length)].toUpperCase();
		this.progress = new StringBuilder("*".repeat(word.length()));
		this.guessedLetters = new HashSet<>();
		this.attemptsLeft = 10;
		
	}
	
	//indicate game over true or false
	public boolean isGameOver() {
		return attemptsLeft == 0 || progress.toString().equals(word);
	}
	
	public String makeGuess(char guess) {
		if (guessedLetters.contains(guess)) {
			return "Letter has already been guessed!";
		}
		
		guessedLetters.add(guess);
		
		//indexOf() method returns -1 if the value is not found.
		if (word.indexOf(guess) != -1) {
			checkGuess(word, guess);
			if (progress.toString().equals(word)) {
	        	return "The word has been guessed!";
	        }
	        return "Correct guess!";
		}
		else {
			attemptsLeft--;
			if (attemptsLeft == 0) {
				return "Game over. The word was " + word;
			}
			else {
				return "Incorrect guess";
			}
		}
	}
	
	public void checkGuess(String str, char guessed)
    {
        CharacterIterator it
            = new StringCharacterIterator(str);
 
        // Iterate and print current character
        while (it.current() != CharacterIterator.DONE) {
            if (it.current() == guessed) {
            	progress.setCharAt(it.getIndex(), guessed);
            }
            // Moving onto next element in the object
            // using next() method
            it.next();
        }
        
    }
	
	public String getHangmanFigureState() {
		String[] stages = {
				"nothing",       // 0 incorrect guesses    

				"ground",      // 1 incorrect guess    

				"stand",    // 2 incorrect guesses    

				"rope",    // 3 incorrect guesses    

				"hair",  // 4 incorrect guesses    

				"head",// 5 incorrect guesses    

				"body",// 6 incorrect guesses    

				"left arm",// 7 incorrect guesses    

				"right arm",// 8 incorrect guesses    

				"left leg",// 9 incorrect guesses    

				"right leg" // 10 incorrect guesses;
		};
		int stageIndex = Math.min(10 - attemptsLeft, stages.length-1);
//		System.out.println("the stage is: " + stages[stageIndex]);
		return stages[stageIndex];
	}

	public GameStatus getGameState() {
		GameStatus gameStatus = new GameStatus();
		gameStatus.setProgress(progress.toString());
		gameStatus.setAttemptsLeft(attemptsLeft);
		gameStatus.setGameOver(isGameOver());
		gameStatus.setGuessedLetters(guessedLetters);
		gameStatus.setWord(word);
		gameStatus.setHangmanStateFigure(getHangmanFigureState());
		
		return gameStatus;
	}
}