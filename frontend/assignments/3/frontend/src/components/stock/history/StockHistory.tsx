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
              <div>{history.qty} stocks</div>
              <div>
                {(() => {
                  const dateObj = new Date(history.date);
                  const weekday = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'][dateObj.getUTCDay()];
                  const date = dateObj.getUTCDate();
                  const month = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][dateObj.getUTCMonth()];
                  const year = dateObj.getUTCFullYear();
                  const time = dateObj.toTimeString().split(' ')[0];
                  return `${weekday} ${date} ${month} ${year} ${time} GMT`;
                })()}
              </div>

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
