// import { configureStore } from '@reduxjs/toolkit';
// import roomReducer from './roomSlice'

// const store = configureStore({
//   reducer: {
//     rooms: roomReducer
//   }
// });

// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;

// export default store;


// store.ts
import { combineReducers, configureStore } from '@reduxjs/toolkit';
import roomsReducer, { RoomssState } from './fetchrooms';

export interface RootState {
  rooms: RoomssState;
  // other reducers...
}

const rootReducer = combineReducers({
  rooms: roomsReducer,
  // other reducers...
});

const store = configureStore({
  reducer: rootReducer,
});

export type AppDispatch = typeof store.dispatch;

export default store;
