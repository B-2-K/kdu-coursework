import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPortfolioTransactions } from '../../redux/portfolioTransactionsSlice';
import { AppDispatch, RootState } from '../../redux/store';
import Header from '../dashboard/header/Header';
import { setSearchQuery } from '../../redux/searchSlice';
import './Portfolio.scss';
import axios from 'axios';
import { ClipLoader } from 'react-spinners';
import { toast } from 'react-toastify';

interface Stock {
  id: string;
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: string;
}

export default function Portfolio() {
  const dispatch = useDispatch<AppDispatch>();
  const { transactions, status, error } = useSelector((state: RootState) => state.portfolioTransactions);
  const { stocks } = useSelector((state: RootState) => state.stocks);
  const searchQuery = useSelector((state: RootState) => state.search.query);
  const allTransactionHistory = useSelector((state: RootState) => state.history.allTransactionHistory);

  const [transactionHistory, setTransactionHistory] = useState<Stock[]>([]);
  const [selectedFilters, setSelectedFilters] = useState<string[]>([]);
  const [selectedStocks, setSelectedStocks] = useState<string[]>([]);
  const [startDate, setStartDate] = useState<string>('');
  const [endDate, setEndDate] = useState<string>('');

  useEffect(() => {
    dispatch(fetchPortfolioTransactions());
    fetchTransactionHistory();
  }, [dispatch]);

  const fetchTransactionHistory = async () => {
    try {
      const response = await axios.get('http://localhost:3000/history');
      const transactionHistoryData = response.data;
      console.log('Transaction History:', transactionHistoryData);
      setTransactionHistory(transactionHistoryData);
    } catch (error) {
      console.error('Error fetching transaction history:', error);
    }
  };

  console.log('new history', allTransactionHistory);

  const mergedTransactions = [...transactions, ...allTransactionHistory];

  let filteredStocks = mergedTransactions.filter(stock =>
    stock.stock_name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    stock.stock_symbol.toLowerCase().includes(searchQuery.toLowerCase())
  );

  if (selectedFilters.length > 0) {
    filteredStocks = filteredStocks.filter(stock => selectedFilters.includes(stock.status));
  }

  if (selectedStocks.length > 0) {
    filteredStocks = filteredStocks.filter(stock => selectedStocks.includes(stock.stock_symbol));
  }

  if (startDate) {
    filteredStocks = filteredStocks.filter(stock => {
      const stockDate = new Date(stock.timestamp).toISOString().split('T')[0];
      return stockDate >= startDate;
    });
  }

  if (endDate) {
    filteredStocks = filteredStocks.filter(stock => {
      const stockDate = new Date(stock.timestamp).toISOString().split('T')[0];
      return stockDate <= endDate;
    });
  }




  const sortedTransactions = filteredStocks.slice().sort((a, b) => {
    return new Date(b.timestamp).getTime() - new Date(a.timestamp).getTime();
  });

  const groupedstocksTransactions = groupstocksTransactionsByDate(sortedTransactions);

  function groupstocksTransactionsByDate(stocksTransactions: Stock[]): { [date: string]: Stock[] } {
    const grouped: { [date: string]: Stock[] } = {};
    stocksTransactions.forEach((stock) => {
      const date = new Date(stock.timestamp).toLocaleDateString();
      if (!grouped[date]) {
        grouped[date] = [];
      }
      grouped[date].push(stock);
    });
    return grouped;
  }

  const handleFilterChange = (filter: string) => {
    if (selectedFilters.includes(filter)) {
      setSelectedFilters(selectedFilters.filter(f => f !== filter));
    } else {
      setSelectedFilters([...selectedFilters, filter]);
    }
  };

  const handleStockChange = (stock: string) => {
    if (selectedStocks.includes(stock)) {
      setSelectedStocks(selectedStocks.filter(s => s !== stock));
    } else {
      setSelectedStocks([...selectedStocks, stock]);
    }
  };

  const handleStartDateChange = (date: string) => {
    setStartDate(date);
  };

  const handleEndDateChange = (date: string) => {
    setEndDate(date);
  };

  const handleClearAll = () => {
    setSelectedFilters([]);
    setSelectedStocks([]);
    setStartDate('');
    setEndDate('');
    dispatch(setSearchQuery(''));
  }

  if (status === 'loading') {
    return <div><div style={{ textAlign: "center", marginTop: "5rem" }}>
    <ClipLoader
      color="black"
      size={100}
      aria-label="Loading Spinner"
      data-testid="loader"
      className="loader"
    />
  </div></div>;
  }

  return (
    <div className="portfolio-wrapper">
      <Header />
      <div className="portfolio">
        <div className="filter">
          <div className="filter-header">
            <div className="filter-title">
              Filters
            </div>
            <div className="clear-filter" onClick={handleClearAll}>
              Clear All
            </div>
          </div>
          <hr />
          <input
            type="text"
            id='search-box'
            placeholder='Search for a stock'
            value={searchQuery}
            onChange={(e) => dispatch(setSearchQuery(e.target.value))}
          />
          <hr />
          <div className="search-by-date">
            <input
              type="date"
              name=""
              id=""
              placeholder='Start Date'
              value={startDate}
              onChange={(e) => handleStartDateChange(e.target.value)}
            />
            <input
              type="date"
              name=""
              id=""
              placeholder='End Date'
              value={endDate}
              onChange={(e) => handleEndDateChange(e.target.value)}
            />
          </div>
          <hr />
          <div className="passed-failed">
            <div>
              <input
                type="checkbox"
                name="Passed"
                id="passed"
                checked={selectedFilters.includes('Passed')}
                onChange={() => handleFilterChange('Passed')}
              />
              <label htmlFor="passed">Passed</label>
            </div>
            <div>
              <input
                type="checkbox"
                name="Failed"
                id="failed"
                checked={selectedFilters.includes('Failed')}
                onChange={() => handleFilterChange('Failed')}
              />
              <label htmlFor="failed">Failed</label>
            </div>
          </div>
          <hr />
          <div className="all-stocksTransactions">
            {stocks.map(stock => (
              <div key={stock.id}>
                <input
                  type="checkbox"
                  id={stock.id}
                  name={stock.stock_symbol}
                  value={stock.stock_symbol}
                  checked={selectedStocks.includes(stock.stock_symbol)}
                  onChange={() => handleStockChange(stock.stock_symbol)}
                />
                <label htmlFor={stock.id}>{stock.stock_name}</label>
              </div>
            ))}
          </div>
        </div>
        <div className="history">
          {Object.entries(groupedstocksTransactions).map(([date, stocksTransactions]) => (
            <div className='group-by-date' key={date}>
              <p>{date}</p>
              <hr className='date-line' />
              {stocksTransactions.map((stock) => (
                <div className="portfolio-elements" key={stock.id}>
                  <div>
                    <div>{stock.stock_name}</div>
                    <div>{stock.stock_symbol}</div>
                    <div>{stock.transaction_price}</div>
                    <div>{`${stock.status}` === 'Passed' ? <span className='green-dot'></span> : <span className='red-dot'></span>}</div>
                  </div>
                  <hr />
                </div>
              ))}
              <br />
            </div>
          ))}
        </div>
      </div>
        {error ? toast.error('error while fetching the portfolio') : toast.success('fetched the portfolio successfully')}
    </div>
  );
}
