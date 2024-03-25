import React from 'react';
import Navbar from './Navbar';
import TodoInput from './TodoInput';
import TodoList from './TodoList';
import '../scss/ToDos.scss';

const TodoApp: React.FC = () => {
  return (
    <div>
      <Navbar />
      <div className="list-container">
        <div className="text">Add Items</div>
        <TodoInput />
        <div className="text">Items</div>
        <TodoList />
      </div>
    </div>
  );
};

export default TodoApp;
