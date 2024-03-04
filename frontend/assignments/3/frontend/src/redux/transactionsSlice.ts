// transactionsSlice.ts

import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface Transaction {
  date: string;
  name: string;
  symbol: string;
  price: number;
  status: 'buy' | 'sell';
  qty: number;
}

interface UserTransaction {
  userName: string;
  date: string;
  name: string;
  qty: number;
  symbol: string;
  status: 'buy' | 'sell';
}

interface TransactionsState {
  transactions: Transaction[];
  userTransactions: UserTransaction[];
}

const names = ["Rishav", "Aakash", "Anupam", "Nitesh", "Sagun"];

const initialState: TransactionsState = {
  transactions: [],
  userTransactions: [],
};

const transactionsSlice = createSlice({
  name: 'transactions',
  initialState,
  reducers: {
    addTransaction(state, action: PayloadAction<Transaction>) {
      state.transactions.unshift(action.payload);
    },
    addUserTransaction(state, action: PayloadAction<Transaction>) {
      const randomIndex = Math.floor(Math.random() * names.length);
      const randomName = names[randomIndex];
      const userTransaction: UserTransaction = {
        userName: randomName,
        date: action.payload.date,
        name: action.payload.name,
        qty: action.payload.qty,
        symbol: action.payload.symbol,
        status: action.payload.status,
      };
      state.userTransactions.unshift(userTransaction);
    },
  },
});

export const { addTransaction, addUserTransaction } = transactionsSlice.actions;

export default transactionsSlice.reducer;
