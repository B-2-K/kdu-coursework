import { configureStore } from '@reduxjs/toolkit';
import productReducer from './productSlice';
import searchReducer from './searchSlice';
import filterReducer from './filterSlice';
import sortReducer from './sortSlice';
import snackbarReducer from './snackbarSlice';

const store = configureStore({
  reducer: {
    products: productReducer,
    search: searchReducer,
    filter: filterReducer,
    sort: sortReducer,
    snackbar: snackbarReducer
  }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export default store;
