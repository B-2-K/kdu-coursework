import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import './Navbar.scss';
import { RootState } from '../../../redux/store';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import io from 'socket.io-client';
import { addTransaction } from '../../../redux/transactionsSlice';
import { addHistory, addTransactionHistory } from '../../../redux/historySlice';
import { buyStock, sellStock } from '../../../redux/stocksSlice';

interface NavbarProps {
    stockSymbol: string | undefined;
}

const Navbar: React.FC<NavbarProps> = ({ stockSymbol }) => {
    const [price, setPrice] = useState<number>(20000);
    const [stockPrice, setStockprice] = useState<number>(5000);
    const [prev, setPrev] = useState<number>(510);
    const [flag, setFlag] = useState<boolean>(false);
    const [percentageChange, setPercentageChange] = useState<number>(0);
    const { stocks, status, error } = useSelector((state: RootState) => state.stocks);

    const userStock = useSelector((state: RootState) => state.stocks.userStocks);
    const [selectedStockSymbol, setSelectedStockSymbol] = useState(stockSymbol);
    const navigate = useNavigate();
    const [qty, setQty] = useState(0);
    const [socket, setSocket] = useState<SocketIOClient.Socket | null>(null);
    const dispatch = useDispatch();

    useEffect(() => {
        const selectedStock = stocks.find(stock => stock.stock_symbol === selectedStockSymbol);
        if (selectedStock) {
            setStockprice(selectedStock.base_price);
        }
        const socket = io('http://localhost:3000');
        setSocket(socket);

        socket.on('buy', (data) => {
            console.log("buy succeeded");
            dispatch(addTransaction(data))
        });

        socket.on('sell', (data) => {
            console.log("sell succeeded");
            dispatch(addTransaction(data))
        });

        socket.on('newRandomNumber', (data) => {
            // console.log(data);
            setPercentageChange((data * 100) / stockPrice);
            if (data >= prev) {
                setStockprice(stockPrice + data);
                setFlag(true);
            }
            else {
                setStockprice(stockPrice + data);
                setFlag(false);
            }
            setPrev(data);
        });

        return () => {
            socket.disconnect();
        };
    }, []);

    function handleQty(e: React.ChangeEvent<HTMLInputElement>) {
        setQty(parseInt(e.target.value));
    }

    const otherStocks = stocks.filter(stock => stock.stock_symbol !== selectedStockSymbol);

    const handleDropdownChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const newStockSymbol = event.target.value;
        setSelectedStockSymbol(newStockSymbol);
        navigate(`/stock/${newStockSymbol}`);
    };

    const handleBuy = async () => {
        try {
            const selectedStock = stocks.find(stock => stock.stock_symbol === selectedStockSymbol);
            const currentDate = new Date().toISOString();

            const requestData = {
                date: currentDate,
                name: selectedStock?.stock_name,
                symbol: selectedStockSymbol,
                price: selectedStock?.base_price,
                status: 'buy',
                qty: qty
            };

            const data = {
                status: 'Passed',
                stock_name: selectedStock?.stock_name,
                stock_symbol: selectedStockSymbol,
                timestamp: currentDate,
                transaction_price: selectedStock?.base_price,
            }

            let currPrice = selectedStock?.base_price ?? 0;
            currPrice *= qty;

            if (price < currPrice) {
                data.status = 'Failed'
            }

            setPrice(price - currPrice);
            await axios.post('http://localhost:3000/history', requestData);
            const buyStockUser = {
                stock_name: selectedStock?.stock_name ?? '',
                quantity: qty,
            }
            if (data.status === 'Passed') {
                dispatch(addHistory(requestData));
                dispatch(buyStock(buyStockUser));
            }
            dispatch(addTransactionHistory(data));
            if (socket && data.status === 'Passed') {
                socket.emit('buy', requestData);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const handleSell = async () => {
        try {
            const selectedStock = stocks.find(stock => stock.stock_symbol === selectedStockSymbol);
            const currentDate = new Date().toISOString();
            const requestData = {
                date: currentDate,
                name: selectedStock?.stock_name,
                symbol: selectedStockSymbol,
                price: selectedStock?.base_price,
                status: 'sell',
                qty: qty
            };

            const data = {
                status: 'Failed',
                stock_name: selectedStock?.stock_name,
                stock_symbol: selectedStockSymbol,
                timestamp: currentDate,
                transaction_price: selectedStock?.base_price,
            }

            await axios.post('http://localhost:3000/history', requestData);

            const currUserStock = userStock.find(stock => stock.stock_name === selectedStock?.stock_name);

            if (currUserStock && currUserStock.quantity >= qty) {
                data.status = 'Passed';
            }

            if (data.status === 'Passed') {
                dispatch(addHistory(requestData));
                dispatch(sellStock({ stock_name: selectedStock?.stock_name ?? '', quantity: qty }));
            }
            dispatch(addTransactionHistory(data));
            if (socket && data.status === 'Passed') {
                socket.emit('sell', requestData);
            }
            console.log(requestData);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="navbar">
            <div className="stock-selector">
                <div className='stock-symbol'>{selectedStockSymbol}</div>
                <select value={selectedStockSymbol} onChange={handleDropdownChange}>
                    <option value={selectedStockSymbol}>
                        {selectedStockSymbol ? (
                            <div>

                                <div>{stocks.find(stock => stock.stock_symbol === selectedStockSymbol)?.stock_name}</div>
                            </div>
                        ) : (
                            'Select a Stock'
                        )}
                    </option>
                    {otherStocks.map(stock => (
                        <option key={stock.id} value={stock.stock_symbol}>
                            <div className='stock-symbol'>{stock.stock_symbol}</div>
                            <div>

                                <div>{stock.stock_name}</div>
                            </div>
                        </option>
                    ))}
                </select>
            </div>
            <div>
                <span>Price </span>{flag ? <span className="up">{stockPrice}<i className="fi fi-rr-arrow-small-up"></i></span> : <span className="down">{stockPrice}<i className="fi fi-rr-arrow-small-down"></i></span>} {percentageChange.toFixed(2)}%
            </div>
            <div className="stock-qty">
                <input type="number" placeholder='Enter QTY' onChange={handleQty} />
            </div>
            <div className="buy" onClick={handleBuy}>
                Buy
            </div>
            <div className="sell" onClick={handleSell}>
                Sell
            </div>
        </div>
    );
};

export default Navbar;
