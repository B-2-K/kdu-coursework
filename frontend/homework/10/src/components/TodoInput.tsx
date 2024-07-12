import React from 'react';

interface TodoInputProps {
  inputValue: string;
  onInputChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onAddTodo: () => void;
}

const TodoInput: React.FC<TodoInputProps> = ({
  inputValue,
  onInputChange,
  onAddTodo,
}) => {
  return (
    <div className="todo-input">
      <input
        type="text"
        placeholder=""
        value={inputValue}
        onChange={onInputChange}
      />
      <button onClick={onAddTodo}>Submit</button>
    </div>
  );
};

export default TodoInput;
