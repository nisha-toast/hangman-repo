package com.example.Hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hangman.beans.GameStatus;
import com.example.Hangman.service.HangmanService;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/hangman")
public class HangmanController {

	@Autowired
	private HangmanService hangmanService;
	
	//Inject service
	public HangmanController(HangmanService hangmanService) {
		this.hangmanService = hangmanService;
	}

	//Game current state
	@GetMapping("/status")
	public ResponseEntity<GameStatus> getStatus(){
		GameStatus gameStatus = hangmanService.getGameState();
		return ResponseEntity.ok(gameStatus);
	}

	@PostMapping("/guess")
	public ResponseEntity<String> guessLetter(@RequestParam char guess){
		String response = hangmanService.makeGuess(guess);
		
		if (hangmanService.isGameOver()) {
			return ResponseEntity.ok(response);
		}
		
		GameStatus gameStatus = hangmanService.getGameState();
//		return ResponseEntity.ok(response + "Current status: " + gameStatus.getProgress());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/start")
	public ResponseEntity<String> startNewGame() {
		hangmanService.startNewGame();
		return ResponseEntity.ok("New game started!");
	}
}

