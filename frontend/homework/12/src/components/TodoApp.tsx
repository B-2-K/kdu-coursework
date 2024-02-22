import React from 'react';
import Navbar from './Navbar';
import TodoInput from './TodoInput';
import TodoList from './TodoList';
import { TodoProvider } from './TodoContext';
import '../scss/ToDos.scss';

const TodoApp: React.FC = () => {
  return (
    <TodoProvider>
      <div>
        <Navbar />
        <div className="list-container">
          <div className="text">Add Items</div>
          <TodoInput />
          <TodoList />
        <div className="text">Items</div>
        </div>
      </div>
    </TodoProvider>
  );
};

export default TodoApp;
