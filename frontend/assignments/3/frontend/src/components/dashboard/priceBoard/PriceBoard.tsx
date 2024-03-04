import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchStocks } from '../../../redux/stocksSlice';
import { AppDispatch, RootState } from '../../../redux/store';
import './PriceBoard.scss';
import { Link } from 'react-router-dom';
import { addToWatchlist, removeFromWatchlist } from '../../../redux/watchlistSlice';
import { ClipLoader } from 'react-spinners';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const STOCKS_PER_PAGE = 10;
const MAX_PAGE_BUTTONS = 4;

interface StockDetails {
  id: number;
  stock_name: string;
  stock_symbol: string;
  base_price: number;
}

export default function PriceBoard() {
  const dispatch = useDispatch<AppDispatch>();
  const [savedlist, setSavedlist] = useState(false);
  const [activeButton, setActiveButton] = useState<'explore' | 'watchlist'>('explore'); // Track the active button
  const { stocks, status, error } = useSelector((state: RootState) => state.stocks);
  const watchlist = useSelector((state: RootState) => state.watchlistStocks.stocks);

  const [currentPage, setCurrentPage] = useState(1);
  const [hovered, setHovered] = useState(false);

  const handleMouseEnter = () => {
    setHovered(true);
  };

  const handleMouseLeave = () => {
    setHovered(false);
  };

  useEffect(() => {
    dispatch(fetchStocks());
    {error ? toast.error('error while fetching the stocks') : toast.success('fetched the stocks successfully')}
  }, [dispatch]);

  const modifiedStocks = savedlist ? watchlist : stocks;

  const startIndex = (currentPage - 1) * STOCKS_PER_PAGE;
  const endIndex = currentPage * STOCKS_PER_PAGE;

  const paginatedStocks = modifiedStocks.slice().sort((a, b) => a.stock_name.localeCompare(b.stock_name)).slice(startIndex, endIndex);

  const totalPages = Math.ceil(stocks.length / STOCKS_PER_PAGE);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleAddToWatchlist = (stock: StockDetails) => {
    dispatch(addToWatchlist(stock));
  };

  const handleRemoveFromWatchlist = (stock: StockDetails) => {
    dispatch(removeFromWatchlist(stock));
  };

  const goToPreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const goToNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
    }
  };

  const renderPageButtons = () => {
    const pageButtons = [];
    const startPage = Math.max(1, currentPage - Math.floor(MAX_PAGE_BUTTONS / 2));
    const endPage = Math.min(startPage + MAX_PAGE_BUTTONS - 1, totalPages);

    for (let i = startPage; i <= endPage; i++) {
      pageButtons.push(
        <button
          key={i}
          onClick={() => handlePageChange(i)}
          disabled={currentPage === i}
        >
          {i}
        </button>
      );
    }

    return pageButtons;
  };

  function handleExplore() {
    setSavedlist(false);
    setActiveButton('explore'); // Set the active button to 'explore'
  }

  function handleWatchlist() {
    setSavedlist(true);
    setActiveButton('watchlist'); // Set the active button to 'watchlist'
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
    <div className="board">
      <div className="board-navbar">
        <button onClick={handleExplore} className={activeButton === 'explore' ? 'active' : ''}>Explore</button>
        <button onClick={handleWatchlist} className={activeButton === 'watchlist' ? 'active' : ''}>My Watch List</button>
      </div>
      <div className="price-board">
        <div className="price-board-heading">
          <div className="company">Company</div>
          <div className="base-price-and-watchlist">
            <div className="base-price">Base Price</div>
            <div className="watchlist">Watchlist</div>
          </div>
        </div>
        <hr />
        {paginatedStocks.map((stock) => (
          <div className='stock-board' key={stock.id}>
            <div className="price-board-heading">
              <Link to={`/stock/${stock.stock_symbol}`}>
                <div className="company">{stock.stock_name}</div>
              </Link>

              <div className="base-price-and-watchlist">
                <div className="base-price">{stock.base_price}</div>
                <div className="watchlist">
                  {watchlist.includes(stock) ? (
                    <button
                      id='remove-from-watchlist'
                      className={`watchlist-button ${hovered ? 'cross' : 'tick'}`}
                      onClick={() => handleRemoveFromWatchlist(stock)}
                      onMouseEnter={handleMouseEnter}
                      onMouseLeave={handleMouseLeave}
                    >
                      {hovered ? '❌' : '✔'}
                    </button>
                  ) : (
                    <button onClick={() => handleAddToWatchlist(stock)}>+</button>
                  )}
                </div>
              </div>
            </div>
            <hr />
          </div>
        ))}

        <div className="pagination">
          <button onClick={goToPreviousPage} disabled={currentPage === 1}>
            {'<'}
          </button>
          {renderPageButtons()}
          <button onClick={goToNextPage} disabled={currentPage === totalPages}>
            {'>'}
          </button>
        </div>
      </div>
    </div>
  );
}
