import { configureStore } from '@reduxjs/toolkit';
import { combineReducers } from 'redux';
import todoReducer from './todoSlice';
import { persistReducer, persistStore } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // defaults to localStorage for web

const rootReducer = combineReducers({
  todo: todoReducer,
  // Add other reducers if any
});

const persistConfig = {
  key: 'root',
  storage
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer,
});

export const persistor = persistStore(store);

export type RootState = ReturnType<typeof store.getState>;
export default store;
