import React from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../../../redux/store';
import './Transactions.scss'

interface TransactionProps {
  stockSymbol: string | undefined;
}

const Transactions: React.FC = ({ stockSymbol }) => {
  const transactions = useSelector((state: RootState) => state.transactions.transactions);
  console.log(transactions);

  console.log('transactions : ', stockSymbol);

  const names = ['Rishav', 'Aakash', 'Sagun', 'Amey', 'Anupam', 'Nitesh'];

  // Select five random names
  const randomNames = [];
  for (let i = 0; i < 5; i++) {
    const randomIndex = Math.floor(Math.random() * names.length);
    randomNames.push(names[randomIndex]);
  }

  // Select one of the random names
  const selectedNameIndex = Math.floor(Math.random() * randomNames.length);
  const selectedName = randomNames[selectedNameIndex];

  const filterTransactions = transactions.filter(transactions => transactions.symbol == stockSymbol);

  console.log('filtered transactions', filterTransactions);

  return (
    <div className="transactions">
      <p>Transactions</p>
      <div>
        {filterTransactions.map((transaction, index) => (
          <div className='wrapper' key={index}>
            <div>{selectedName} bought {transaction.qty} {transaction.name}</div>
            <div>
              {new Date(transaction.date).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: true })}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Transactions;
