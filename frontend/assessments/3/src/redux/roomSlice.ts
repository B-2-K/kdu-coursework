import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { fetchRooms } from './fetchrooms';
import { Rooms } from './fetchrooms';

interface RoomssState {
  Roomss: Rooms[];
  loading: boolean;
  error: string | null;
}

const initialState: RoomssState = {
  Roomss: [],
  loading: false,
  error: null,
};

const RoomsSlice = createSlice({
  name: 'Roomss',
  initialState,
  reducers: {},
  extraReducers: builder => {
    builder.addCase(fetchRooms.pending, state => {
      state.loading = true;
      state.error = null;
    });
    builder.addCase(fetchRooms.fulfilled, (state, action: PayloadAction<Rooms[]>) => {
      state.loading = false;
      state.Roomss = action.payload;
    });
    builder.addCase(fetchRooms.rejected, (state, action) => {
      state.loading = false;
      state.error = action.error.message ?? 'An error occurred';
    });
  },
});

export default RoomsSlice.reducer;