import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Header from '../dashboard/header/Header';
import Navbar from './navbar/Navbar';
import Graph from './graph/Graph';
import Transactions from './transactions/transactions';
import StockHistory from './history/StockHistory';
import './stock.scss';

const Stock: React.FC = () => {
    const { stockSymbol } = useParams();
    const [randomNumber, setRandomNumber] = useState<number>(0);

    useEffect(() => {
        const interval = setInterval(() => {
            const randomNumber = Math.floor(Math.random() * (500 - 200 + 1) + 200);
            setRandomNumber(randomNumber);
        }, 5000);
        return () => clearInterval(interval);
    }, []);

    return (
        <>
            <Header />
            <div className="stock">
                <div className="navbar-and-graph">
                    <Navbar stockSymbol={stockSymbol} randomNumber={randomNumber} />
                    <Graph randomNumber={randomNumber} />
                </div>
                <div className="history-and-transactions">
                    <StockHistory />
                    <Transactions stockSymbol={stockSymbol} />
                </div>
            </div>
        </>
    );
};

export default Stock;
