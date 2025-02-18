import HangmanDrawing from './HangmanDrawing';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';
import Keyboard from './Keyboard';
import GameOver from './GameOver';
import WordDisplay from './WordDisplay';

function HangmanGame() {

  const [progress, setProgress] = useState('');
  const [attemptsLeft, setAttemptsLeft] = useState(10);
  const [gameOver, setGameover] = useState(false);
  const [hangmanStage, setHangmanStage] = useState('');
  const [message, setMessage] = useState('');
  const [usedLetters, setUsedLetters] = useState(new Set());

  useEffect(() => {
    fetch(`/api/hangman/status`)
      .then(response => response.json())
      .then(data => {
        // console.log("useEffect backend status response:",data);
        setProgress(data.progress);
        setAttemptsLeft(data.attemptsLeft);
        setGameover(data.gameOver);
        setHangmanStage(data.hangmanFigureState);
        // setUsedLetters(new Set(data.guessedLetters));

        if (data.guessedLetters) {
          setUsedLetters(new Set(data.guessedLetters));
        }
      })
      .catch((error) => console.error("Error fetching status".error));
  }, []
  );

  const handleGuess = (letter) => {
    if (usedLetters.has(letter)) return;
    fetch(`/api/hangman/guess?guess=${letter}`, { method: 'POST' })
      .then(response => response.text())
      .then(message => {
        setMessage(message);
        fetch(`api/hangman/status`)
          .then(response => response.json())
          .then(data => {
            // console.log("handleGuess Backend response:", data)
            setProgress(data.progress);
            setAttemptsLeft(data.attemptsLeft);
            setGameover(data.gameOver);
            setHangmanStage(data.hangmanFigureState);
            setUsedLetters(new Set(data.guessedLetters));
            // console.log("Updated hangmanStage after guess:", data.hangmanFigureState);
          });
      });
    // setUsedLetters(new Set([...usedLetters, letter]));
  };

  const startNewGame = () => {
    fetch(`api/hangman/start`, { method: 'POST' })
      .then(() => {
        return fetch(`/api/hangman/status`);
      })
      .then(response => response.json())
      .then(data => {
        setProgress(data.progress);
        setAttemptsLeft(10);
        setGameover(false);
        setHangmanStage(data.hangmanFigureState);
        setMessage('New game started! Good luck!');
        setUsedLetters(new Set(data.guessedLetters));
      })
      .catch((error) => console.error("Error resetting game:", error));
  };


  const navigate = useNavigate();

  const goBackToLanding = () => {
    fetch('/api/hangman/start', { method: "POST" }).then(() => {
      navigate("/");
    })
      .catch((error) => console.error("Error restarting game:", error));
  };

  return (
    <>
      <div className='hangman-game'>
        <header>
          <h1 className="fancy-word" style={{ marginBottom: "-20px" }}>
            <span>
              Hangman Game </span></h1>
        </header>

        <div className="content">
          <WordDisplay progress={progress} attemptsLeft={attemptsLeft} />
          {/* Render HangmanDrawing Component here */}
          <div className='drawing-box'>
            <HangmanDrawing stage={hangmanStage} />
          </div>
          <p className='final-message'>{message}</p>
          <div className="keyboard">
            {!gameOver ? (
              <div className="keyboard-button">
                <Keyboard handleGuess={handleGuess} usedLetters={usedLetters}
                  gameOver={gameOver} />
              </div>) : (
              <GameOver startNewGame={startNewGame} goBackToLanding={goBackToLanding} />

            )}
          </div>
        </div>
      </div>
    </>
  );
};

export default HangmanGame;