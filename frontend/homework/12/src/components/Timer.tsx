import { useRef, useState } from "react";
import { timer } from "../styles/Timer.styles";

function Timer() {
  const [seconds, setSeconds] = useState<number>(0);
  const [isActive, setIsActive] = useState<boolean>(false);
  const intervalRef = useRef<number | null>(null);

  const startTimer = () => {
    intervalRef.current = window.setInterval(() => {
      setSeconds((prevSeconds) => prevSeconds + 1);
    }, 1000);
  };

  const stopTimer = () => {
    if (intervalRef.current !== null) {
      window.clearInterval(intervalRef.current);
    }
  };

  const resetTimer = () => {
    if (intervalRef.current !== null) {
      window.clearInterval(intervalRef.current);
    }
    setSeconds(0);
  };

  const toggleTimer = () => {
    if (isActive) {
      stopTimer();
    } else {
      startTimer();
    }
    setIsActive(!isActive);
  };

  return (
    <div style={timer}>
      <h1>Timer: {seconds}s</h1>
      <button onClick={toggleTimer}>{isActive ? 'Pause' : 'Start'}</button>
      <button onClick={resetTimer}>Reset</button>
    </div>
  );
}

export default Timer;
