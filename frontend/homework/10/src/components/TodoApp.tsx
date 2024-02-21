import React, { useState } from 'react';
import Navbar from './Navbar';
import TodoInput from './TodoInput';
import TodoList from './TodoList';
import '../scss/ToDos.scss';

const TodoApp: React.FC = () => {
  const [todos, setTodos] = useState<string[]>([]);
  const [inputValue, setInputValue] = useState<string>('');
  const [searchTerm, setSearchTerm] = useState<string>('');

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleAddTodo = () => {
    if (inputValue.trim() !== '') {
      setTodos([...todos, inputValue]);
      setInputValue('');
    }
  };

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  const handleDeleteTodo = (index: number) => {
    const newTodos = [...todos];
    newTodos.splice(index, 1);
    setTodos(newTodos);
  };

  const filteredTodos = todos.filter(todo =>
    todo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <Navbar searchTerm={searchTerm} onSearchChange={handleSearchChange} />
      <div className="list-container">
        <div className="text">Add Items</div>
        <TodoInput
          inputValue={inputValue}
          onInputChange={handleInputChange}
          onAddTodo={handleAddTodo}
        />
        <div className="text">Items</div>
        <TodoList todos={filteredTodos} onDeleteTodo={handleDeleteTodo} />
      </div>
    </div>
  );
};

export default TodoApp;
