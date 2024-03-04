import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';

interface Stock {
  id: number;
  stock_name: string;
  stock_symbol: string;
  base_price: number;
}

interface UserStock {
  stock_name: string;
  quantity: number;
}

interface StocksState {
  stocks: Stock[];
  status: 'idle' | 'loading' | 'succeeded' | 'failed';
  error: string | null;
  userStocks: UserStock[];
}

const initialState: StocksState = {
  stocks: [],
  status: 'idle',
  error: null,
  userStocks: [],
};

// Define Redux thunk to fetch stocks data
export const fetchStocks = createAsyncThunk('stocks/fetchStocks', async () => {
  const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json');
  if (!response.ok) {
    throw new Error('Failed to fetch stocks data');
  }
  const data = await response.json();
  return data as Stock[];
});

const stocksSlice = createSlice({
  name: 'stocks',
  initialState,
  reducers: {
    buyStock(state, action: PayloadAction<{ stock_name: string, quantity: number }>) {
      const { stock_name, quantity } = action.payload;
      const userStockIndex = state.userStocks.findIndex(stock => stock.stock_name === stock_name);
      if (userStockIndex !== -1) {
        state.userStocks[userStockIndex].quantity += quantity;
      }
    },
    sellStock(state, action: PayloadAction<{ stock_name: string, quantity: number }>) {
      const { stock_name, quantity } = action.payload;
      const userStockIndex = state.userStocks.findIndex(stock => stock.stock_name === stock_name);
      if (userStockIndex !== -1 && state.userStocks[userStockIndex].quantity >= quantity) {
        state.userStocks[userStockIndex].quantity -= quantity;
      }
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchStocks.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchStocks.fulfilled, (state, action: PayloadAction<Stock[]>) => {
        state.status = 'succeeded';
        state.stocks = action.payload;
        state.userStocks = action.payload.map(stock => ({ stock_name: stock.stock_name, quantity: 0 }));
      })
      .addCase(fetchStocks.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message ?? 'Failed to fetch stocks data';
      });
  },
});

export const { buyStock, sellStock } = stocksSlice.actions;

export default stocksSlice.reducer;
