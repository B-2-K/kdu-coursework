// portfolioTransactionsSlice.ts
import { createSlice, createAsyncThunk, PayloadAction } from '@reduxjs/toolkit';

interface PortfolioTransaction {
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    timestamp: string;
    status: string;
}

interface PortfolioTransactionsState {
    transactions: PortfolioTransaction[];
    status: 'idle' | 'loading' | 'succeeded' | 'failed';
    error: string | null;
}

const initialState: PortfolioTransactionsState = {
    transactions: [],
    status: 'idle',
    error: null,
};

export const fetchPortfolioTransactions = createAsyncThunk(
    'portfolioTransactions/fetchPortfolioTransactions',
    async () => {
        const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json');
        if (!response.ok) {
            throw new Error('Failed to fetch portfolio transactions data');
        }
        const data = await response.json();
        return data as PortfolioTransaction[];
    }
);

const portfolioTransactionsSlice = createSlice({
    name: 'portfolioTransactions',
    initialState,
    reducers: {
        prependTransactionHistory(state, action: PayloadAction<PortfolioTransaction[]>) {
            state.transactions.unshift(...action.payload);
          },
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchPortfolioTransactions.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(fetchPortfolioTransactions.fulfilled, (state, action) => {
                state.status = 'succeeded';
                state.transactions = action.payload;
            })
            .addCase(fetchPortfolioTransactions.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.error.message ?? 'Failed to fetch portfolio transactions data';
            });
    },
});

export default portfolioTransactionsSlice.reducer;
