import { configureStore } from '@reduxjs/toolkit';
import stocksReducer from './stocksSlice';
import portfolioTransactionsReducer from './portfolioTransactionsSlice';
import searchReducer from './searchSlice';
import watchlistReducer from './watchlistSlice';
import transactionsReducer from './transactionsSlice';
import historyReducer from './historySlice';

const store = configureStore({
  reducer: {
    stocks: stocksReducer,
    watchlistStocks: watchlistReducer,
    portfolioTransactions: portfolioTransactionsReducer,
    transactions: transactionsReducer,
    history: historyReducer,
    search: searchReducer
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;

export default store;