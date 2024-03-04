import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface History {
  date: string;
  name: string;
  symbol: string;
  price: number;
  status: 'buy' | 'sell';
  qty: number;
}

interface TransactionHistory {
  status: string,
  stock_name: string,
  stock_symbol:string,
  timestamp: string,
  transaction_price: number,
}

interface HistoryState {
  allHistory: History[];
  allTransactionHistory: TransactionHistory[];
}

const initialState: HistoryState = {
  allHistory: [],
  allTransactionHistory: [],
};

const historySlice = createSlice({
  name: 'history',
  initialState,
  reducers: {
    addHistory(state, action: PayloadAction<History>) {
      state.allHistory.unshift(action.payload);
    },
    addTransactionHistory(state, action: PayloadAction<TransactionHistory>) {
      state.allTransactionHistory.unshift(action.payload);
    },
  },
});

export const { addHistory, addTransactionHistory } = historySlice.actions;

export default historySlice.reducer;
