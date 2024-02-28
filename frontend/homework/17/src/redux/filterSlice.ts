import { createSlice } from '@reduxjs/toolkit';

interface InitialState {
    filter: string;
}

const initialState: InitialState = {
    filter: ''
}

const filterSlice = createSlice({
    name: 'filter',
    initialState,
    reducers: {
        setFilter: (state, action) => {
            state.filter = action.payload;
            console.log(action.payload);
        }
    }
});

export default filterSlice.reducer;
export const { setFilter } = filterSlice.actions;
