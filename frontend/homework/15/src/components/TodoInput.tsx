// TodoInput.tsx
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addTodo } from '../redux/todoSlice';

const TodoInput: React.FC = () => {
  const [inputValue, setInputValue] = useState<string>('');
  const dispatch = useDispatch();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleAddTodo = () => {
    if (inputValue.trim() !== '') {
      dispatch(addTodo(inputValue));
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
