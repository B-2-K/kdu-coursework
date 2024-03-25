// todoSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface TodoState {
  todos: { text: string; completed: boolean }[];
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
      state.todos.push({ text: action.payload, completed: false });
    },
    deleteTodo: (state, action: PayloadAction<number>) => {
      state.todos.splice(action.payload, 1);
    },
    toggleTodo: (state, action: PayloadAction<number>) => {
      state.todos[action.payload].completed = !state.todos[action.payload].completed;
    },
    setSearchTerm: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
    clearCompletedTodos: (state) => {
      state.todos = state.todos.filter(todo => !todo.completed);
    }
  },
});

export const { addTodo, deleteTodo, toggleTodo, setSearchTerm, clearCompletedTodos } = todoSlice.actions;
export default todoSlice.reducer;

