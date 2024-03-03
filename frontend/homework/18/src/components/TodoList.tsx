import React from 'react';
import { useTodoContext } from './TodoContext';

const TodoList: React.FC = () => {
  const { todos, deleteTodo, searchTerm } = useTodoContext();

  const filteredTodos = todos.filter(todo =>
    todo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <ul className="todo-list">
      {filteredTodos.map((todo, index) => (
        <li key={index}>
          {todo}
          <button onClick={() => deleteTodo(index)}>X</button>
        </li>
      ))}
    </ul>
  );
};

export default TodoList;