function GameOver({ startNewGame, goBackToLanding }) {
    return (
        <div className='button-container'>
            <button className="start-game-button" onClick={startNewGame}>Start New Game</button>
            <button className="back-button" onClick={goBackToLanding}>Back to Landing Page</button>
            {/* <button className="back-button" onClick={() => {startNewGame; goBackToLanding;}}>
            Back to Landing Page</button> */}
        </div>
    )
}

export default GameOver;