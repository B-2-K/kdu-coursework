import { useSelector } from 'react-redux';
import './StockHistory.scss'
import { RootState } from '../../../redux/store';

export default function StockHistory() {
  const allHistory = useSelector((state: RootState) => state.history.allHistory);
  return (
    <div className="stock-history-container">
      <p>History</p>
      <div className="stock-history-list">
        {allHistory.map((history, index) => (
          <div className='wrapper' key={index}>
            <div>
              <div>{history.qty}</div>
              <div>{history.date}</div>
            </div>
            <div className={history.status === 'sell' ? 'red' : 'green'}>
              {history.status.charAt(0).toUpperCase() + history.status.slice(1)}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
