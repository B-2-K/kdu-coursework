// In your watchlistSlice.ts file
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface StockDetails {
    id: number;
    stock_name: string;
    stock_symbol: string;
    base_price: number;
    // Add other details as needed
}

interface WatchlistState {
    stocks: StockDetails[]; // Array to hold details of stocks in the watchlist
}

const initialState: WatchlistState = {
    stocks: [], // Initialize watchlist with an empty array
};

const watchlistSlice = createSlice({
  name: 'watchlist',
  initialState,
  reducers: {
    addToWatchlist(state, action: PayloadAction<StockDetails>) {
      state.stocks.push(action.payload); // Add the entire stock details to the watchlist
    },
    removeFromWatchlist(state, action: PayloadAction<StockDetails>) {
      state.stocks = state.stocks.filter(stock => stock.stock_name !== action.payload.stock_name);
    },
  },
});

export const { addToWatchlist, removeFromWatchlist } = watchlistSlice.actions;

export default watchlistSlice.reducer;
