import React from 'react';
import { render } from '@testing-library/react';
import TodoList from '../src/components/TodoList';
import { Provider } from 'react-redux';
import store from '../src/redux/store';

test('renders todo list correctly', () => {
  // Optionally, you can dispatch actions to add todos to the store before rendering the component

  const { getByText } = render(
    <Provider store={store}>
      <TodoList />
    </Provider>
  );

  // Add assertions here to check if todos are rendered correctly
});
