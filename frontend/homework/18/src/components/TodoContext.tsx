import React, { createContext, useContext, useState } from 'react';

interface TodoContextType {
  todos: string[];
  addTodo: (todo: string) => void;
  deleteTodo: (index: number) => void;
  searchTerm: string;
  setSearchTerm: (term: string) => void;
}

const TodoContext = createContext<TodoContextType | undefined>(undefined);

export const useTodoContext = () => {
  const context = useContext(TodoContext);
  if (!context) {
    throw new Error('useTodoContext must be used within a TodoProvider');
  }
  return context;
};

export const TodoProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [todos, setTodos] = useState<string[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>('');

  const addTodo = (todo: string) => {
    setTodos([...todos, todo]);
  };

  const deleteTodo = (index: number) => {
    const newTodos = [...todos];
    newTodos.splice(index, 1);
    setTodos(newTodos);
  };

  return (
    <TodoContext.Provider value={{ todos, addTodo, deleteTodo, searchTerm, setSearchTerm }}>
      {children}
    </TodoContext.Provider>
  );
};