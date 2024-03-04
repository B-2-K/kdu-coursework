import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import './Navbar.scss';
import { RootState } from '../../../redux/store';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import io from 'socket.io-client';
import { addTransaction, addUserTransaction } from '../../../redux/transactionsSlice';
import { addHistory, addTransactionHistory } from '../../../redux/historySlice';
import { buyStock, sellStock } from '../../../redux/stocksSlice';
import { toast } from 'react-toastify';

interface NavbarProps {
    stockSymbol: string | undefined;
    randomNumber: number;
}

const Navbar: React.FC<NavbarProps> = ({ stockSymbol, randomNumber }) => {
    const [price, setPrice] = useState<number>(20000);
    const [stockPrice, setStockprice] = useState<number>(5000);
    const [prev, setPrev] = useState<number>(510);
    const [flag, setFlag] = useState<boolean>(false);
    const [firstTime, setFirstTime] = useState<boolean>(true);
    const [percentageChange, setPercentageChange] = useState<number>(0);
    const { stocks, status, error } = useSelector((state: RootState) => state.stocks);
    const userStock = useSelector((state: RootState) => state.stocks.userStocks);
    const [selectedStockSymbol, setSelectedStockSymbol] = useState(stockSymbol);
    const navigate = useNavigate();
    const [qty, setQty] = useState(0);
    const [socket, setSocket] = useState<SocketIOClient.Socket | null>(null);
    const dispatch = useDispatch();

    useEffect(() => {

        const socket = io('http://localhost:3000');
        setSocket(socket);

        socket.on('buy', (data) => {
            console.log("buy succeeded");
            dispatch(addTransaction(data));
            dispatch(addUserTransaction(data));
        });

        socket.on('sell', (data) => {
            console.log("sell succeeded");
            dispatch(addTransaction(data));
            dispatch(addUserTransaction(data));
        });

        const data = randomNumber;
        setPercentageChange((data * 100) / stockPrice);

        setPrev(data);
        if (firstTime) {
            const selectedStock = stocks.find(stock => stock.stock_symbol === selectedStockSymbol);
            if (selectedStock) {
                setStockprice(selectedStock.base_price);
            }
            setFirstTime(false);
        }
        else {
            if (data >= prev) {
                const newPrice = stockPrice + data;
                setStockprice(newPrice);
                setFlag(true);
            }
            else {
                const newPrice = stockPrice - data;
                setStockprice(newPrice);
                setFlag(false);
            }
        }

        return () => {
            socket.disconnect();
        };
    }, [firstTime, randomNumber]);


    function handleQty(e: React.ChangeEvent<HTMLInputElement>) {
        setQty(parseInt(e.target.value));
    }

    const otherStocks = stocks.filter(stock => stock.stock_symbol !== selectedStockSymbol);

    const handleDropdownChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const newStockSymbol = event.target.value;
        setSelectedStockSymbol(newStockSymbol);
        setFirstTime(true);
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
                price: stockPrice,
                status: 'buy',
                qty: qty
            };

            const data = {
                status: 'Passed',
                stock_name: selectedStock?.stock_name,
                stock_symbol: selectedStockSymbol,
                timestamp: currentDate,
                transaction_price: stockPrice,
            }

            let currPrice = stockPrice;
            currPrice *= qty;

            if (price < currPrice) {
                {
                    toast.error('Insufficient Funds');
                }
                data.status = 'Failed'
            }

            setPrice(price - currPrice);
            await axios.post('http://localhost:3000/history', requestData);
            const buyStockUser = {
                stock_name: selectedStock?.stock_name ?? '',
                quantity: qty,
            }
            if (data.status === 'Passed') {
                {
                    toast.success('stock bought successfully');
                }
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
                price: stockPrice,
                status: 'sell',
                qty: qty
            };

            const data = {
                status: 'Failed',
                stock_name: selectedStock?.stock_name,
                stock_symbol: selectedStockSymbol,
                timestamp: currentDate,
                transaction_price: stockPrice,
            }

            await axios.post('http://localhost:3000/history', requestData);

            const currUserStock = userStock.find(stock => stock.stock_name === selectedStock?.stock_name);
            console.log(currUserStock);

            if (currUserStock && currUserStock.quantity >= qty) {
                {
                    toast.success('stock sold successfully');
                }
                data.status = 'Passed';
            }
            else {
                {
                    toast.error('not enough stocks in your portfolio');
                }
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
                <span>Price </span>{flag ? <span className="up">{stockPrice.toFixed(2)}<i className="fi fi-rr-arrow-small-up"></i></span> : <span className="down">{stockPrice.toFixed(2)}<i className="fi fi-rr-arrow-small-down"></i></span>} {percentageChange.toFixed(2)}%
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
                {error ? toast.error('error while fetching the stock details') : ''}
                {firstTime ? toast.success('stock details fetched successfully') : ''}
        </div>
    );
};

export default Navbar;
