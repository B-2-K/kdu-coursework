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

interface TransactionsState {
  transactions: Transaction[];
}

const initialState: TransactionsState = {
  transactions: [],
};

const transactionsSlice = createSlice({
  name: 'transactions',
  initialState,
  reducers: {
    addTransaction(state, action: PayloadAction<Transaction>) {
      state.transactions.unshift(action.payload);
    },
  },
});

export const { addTransaction } = transactionsSlice.actions;

export default transactionsSlice.reducer;
