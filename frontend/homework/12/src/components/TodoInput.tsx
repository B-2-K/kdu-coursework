import React, { useState } from 'react';
import { useTodoContext } from './TodoContext';

const TodoInput: React.FC = () => {
  const [inputValue, setInputValue] = useState<string>('');
  const { addTodo } = useTodoContext();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleAddTodo = () => {
    if (inputValue.trim() !== '') {
      addTodo(inputValue);
      setInputValue('');
    }
  };

  return (
    <div className="todo-input">
      <input
        type="text"
        placeholder=""
        value={inputValue}
        onChange={handleInputChange}
      />
      <button onClick={handleAddTodo}>Submit</button>
    </div>
  );
};

export default TodoInput;
