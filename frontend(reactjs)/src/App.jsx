
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Routes, Route } from 'react-router';
import LandingPage from './components/LandingPage';
import HangmanGame from "./components/HangmanGame";
import Layout from './components/Layout';


function App() {

  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<LandingPage />}></Route>
        <Route path="/game" element={<HangmanGame />}> </Route>
      </Route>
    </Routes>
  );
};


export default App
