import { createSlice } from '@reduxjs/toolkit';

interface InitialState {
    sort: string;
}

const initialState: InitialState = {
    sort: ''
}

const sortSlice = createSlice({
    name: 'sort',
    initialState,
    reducers: {
        setSort: (state, action) => {
            state.sort = action.payload;
            console.log(action.payload);
        }
    }
});

export default sortSlice.reducer;
export const { setSort } = sortSlice.actions;
