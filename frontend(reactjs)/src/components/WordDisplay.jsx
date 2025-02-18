function WordDisplay({ progress, attemptsLeft }) {
    return (
        <div className='word-container'>
            <div className='word-row'>
                {/* <p className="label" > Guess the word:</p> */}
                <p className="fancy-word"> Word: {progress}</p>
            </div>
            <p className='attempts'>LIVES LEFT: {attemptsLeft}</p>
        </div>
    )
}

export default WordDisplay;