import React from 'react';

interface TodoItemProps {
  todo: string;
  onDelete: () => void;
}

const TodoItem: React.FC<TodoItemProps> = ({ todo, onDelete }) => {
  return (
    <li>
      {todo}
      <button onClick={onDelete}>X</button>
    </li>
  );
};

export default TodoItem;
