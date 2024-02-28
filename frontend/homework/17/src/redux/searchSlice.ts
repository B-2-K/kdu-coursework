import { createSlice } from '@reduxjs/toolkit';

interface InitialState {
    search: string;
}

const initialState: InitialState = {
    search: ''
}

const searchSlice = createSlice({
    name: 'search',
    initialState,
    reducers: {
        setSearch: (state, action) => {
            state.search = action.payload;
            console.log(action.payload);
        }
    }
});

export default searchSlice.reducer;
export const { setSearch } = searchSlice.actions;
