// TodoList.tsx
import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../redux/store';
import { clearCompletedTodos, deleteTodo, toggleTodo } from '../redux/todoSlice';

const TodoList: React.FC = () => {
  const todos = useSelector((state: RootState) => state.todo.todos);
  const searchTerm = useSelector((state: RootState) => state.todo.searchTerm);
  const dispatch = useDispatch();

  const filteredTodos = todos.filter(todo =>
    todo.text.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <ul className="todo-list">
      {filteredTodos.map((todo, index) => (
        <li key={index}>
          <div>
            <input id='checkbox' 
              type="checkbox"
              checked={todo.completed}
              onChange={() => dispatch(toggleTodo(index))}
            />
            <span style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}>
              {todo.text}
            </span>
          </div>
          <button onClick={() => dispatch(deleteTodo(index))}>X</button>
        </li>
      ))}
      
    </ul>
  );
};

export default TodoList;
