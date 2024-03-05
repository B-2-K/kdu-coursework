import { combineReducers, configureStore } from '@reduxjs/toolkit';
import roomsReducer, { RoomssState } from './fetchrooms';

export interface RootState {
  rooms: RoomssState;
}

const rootReducer = combineReducers({
  rooms: roomsReducer,
});

const store = configureStore({
  reducer: rootReducer,
});

export type AppDispatch = typeof store.dispatch;

export default store;
