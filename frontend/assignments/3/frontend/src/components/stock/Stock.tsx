import React from 'react';
import { useParams } from 'react-router-dom';
import Header from '../dashboard/header/Header';
import Navbar from './navbar/Navbar';
import Graph from './graph/Graph';
import Transactions from './transactions/transactions';
import StockHistory from './history/StockHistory';
import './stock.scss';

const Stock: React.FC = () => {
    const { stockSymbol } = useParams();

    return (
        <>
            <Header />
            <div className="stock">
                <div className="navbar-and-graph">
                    <Navbar stockSymbol={stockSymbol}/>
                    <Graph />
                </div>
                <div className="history-and-transactions">
                    <StockHistory />
                    <Transactions stockSymbol={stockSymbol}/>
                </div>
            </div>
        </>
    );
};

export default Stock;
