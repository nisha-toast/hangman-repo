function Keyboard({ handleGuess, usedLetters, gameOver }) {
    const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
    const rows = [
        'ABCDEFGHIJ'.split(''),
        'KLMNOPQRS'.split(''),
        'TUVWXYZ'.split(''),
    ];
    
    return (
        <div className="keyboard">
            {rows.map((row, rowIndex) => (
                <div key={rowIndex} className="keyboard-row">
                    {row.map((letter) => (
                        <button 
                            color="seagreen"
                            key={letter}
                            onClick={() => handleGuess(letter)}
                            disabled={usedLetters.has(letter) || gameOver}
                            className="keyboard-button">
                            {letter}
                        </button>
                    ))}
                </div>
            ))}
        </div>
    );
}

export default Keyboard;