import React from 'react';
import TodoItem from './TodoItem';

interface TodoListProps {
  todos: string[];
  onDeleteTodo: (index: number) => void;
}

const TodoList: React.FC<TodoListProps> = ({ todos, onDeleteTodo }) => {
  return (
    <ul className="todo-list">
      {todos.map((todo, index) => (
        <TodoItem key={index} todo={todo} onDelete={() => onDeleteTodo(index)} />
      ))}
    </ul>
  );
};

export default TodoList;
