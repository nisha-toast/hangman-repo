import { useNavigate } from "react-router";
import { Link } from "react-router";
function LandingPage() {
    const navigate = useNavigate();

    return (



        <div className="hangman-game">
            <div className='landing-title'>
                <header>
                    <h1 className="fancy-word" >
                        <span>
                            Hangman Game </span></h1>

                </header>
            </div>
            <div className="landing-description ">
                <p> Instructions: </p>
                <p>Guess the word before the hangman is fully drawn</p>
            </div>
            <div className="start-game-container">
                <Link to="/game">
                    <button className="start-game-button">Start Game</button>
                </Link>
            </div>
        </div>
    );
}

export default LandingPage;