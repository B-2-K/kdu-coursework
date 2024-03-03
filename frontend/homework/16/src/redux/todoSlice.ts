// todoSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface TodoState {
  todos: string[];
  searchTerm: string;
}

const initialState: TodoState = {
  todos: [],
  searchTerm: '',
};

const todoSlice = createSlice({
  name: 'todo',
  initialState,
  reducers: {
    addTodo: (state, action: PayloadAction<string>) => {
      state.todos.push(action.payload);
    },
    deleteTodo: (state, action: PayloadAction<number>) => {
      state.todos.splice(action.payload, 1);
    },
    setSearchTerm: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
  },
});

export const { addTodo, deleteTodo, setSearchTerm } = todoSlice.actions;
export default todoSlice.reducer;
