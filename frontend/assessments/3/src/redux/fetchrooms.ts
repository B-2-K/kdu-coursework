import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { RootState } from './store';

export interface AddOns {
  name: string;
  cost: number;
  currency: string;
}

export interface Rooms {
  id: number;
  name: string;
  costPerNight: string;
  currency: string;
  addOns: AddOns[];
}

export interface RoomssState {
  roomTypes: Rooms[];
}

export const fetchRooms = createAsyncThunk<Rooms[], void, { state: RootState }>(
  'rooms/fetchrooms',
  async () => {
    const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json');
    const data = await response.json();
    return data.roomTypes;
  }
);

const roomsSlice = createSlice({
  name: 'rooms',
  initialState: { roomTypes: [] } as RoomssState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(fetchRooms.fulfilled, (state, action) => {
      state.roomTypes = action.payload;
    });
  },
});

export default roomsSlice.reducer;
