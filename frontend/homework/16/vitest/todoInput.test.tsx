import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import TodoInput from '../src/components/TodoInput';
import { Provider } from 'react-redux';
import store from '../src/redux/store';

test('adds a todo when submit button is clicked', () => {
  const { getByText, getByPlaceholderText } = render(
    <Provider store={store}>
      <TodoInput />
    </Provider>
  );

  const input = getByPlaceholderText('');
  const submitButton = getByText('Submit');

  fireEvent.change(input, { target: { value: 'Test todo' } });
  fireEvent.click(submitButton);

  // You can add assertions here to check if the todo is added to the Redux store
});
