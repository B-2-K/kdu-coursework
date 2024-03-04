import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import './Graph.scss';

const ENDPOINT = "http://localhost:3000";

const socket = socketIOClient(ENDPOINT);

interface GraphProps {
  randomNumber: number;
}

export default function Graph({ randomNumber }) {
  const [randomNumbers, setRandomNumbers] = useState<number[]>([]);
  const [flag, setFlag] = useState<number>(0);
  const barColors = ['red', 'blue'];

  useEffect(() => {
    setRandomNumbers(prevNumbers => {
      const newNumbers = [...prevNumbers, randomNumber];
      if (newNumbers.length > 50) {
        return newNumbers.slice(newNumbers.length - 50);
      } else {
        return newNumbers;
      }
    });

  }, [randomNumber]);

  useEffect(() => {
    const updatedBarsColors = randomNumbers.map((randomNumber, index) => {
      let color = 'blue';
      setFlag(0);
      if (index > 0) {
        if (randomNumber < randomNumbers[index - 1]) {
          color = 'red';
          setFlag(1);
        }
      }
      return color;
    });
  }, [randomNumbers]);

  return (
    <div className="graph-container">
      <div className="grid-container">
        {/* Horizontal lines */}
        {[...Array(3)].map((_, index) => (
          <div className="horizontal-line" style={{ top: `${(index + 1) * 33.33}%` }} key={index}></div>
        ))}
        {/* Vertical lines */}
        {[...Array(5)].map((_, index) => (
          <div className="vertical-line" style={{ left: `${(index + 1) * 20}%` }} key={index}></div>
        ))}
      </div>

      {randomNumbers.map((randomNumber, index) => {
        const barClassName = `bar-${index}`;
        const barStyle: React.CSSProperties = {
          height: `${randomNumber}px`,
          width: '20px',
          backgroundColor: index > 0 && randomNumber >= randomNumbers[index - 1] ? '#B2F2BB' : '#FFC9C9'
        };
        return (
          <div
            className={barClassName}
            style={barStyle}
            key={index}
          ></div>
        );
      })}
    </div>
  );
}
