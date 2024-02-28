import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Navbar from '../src/components/Navbar';
import { Provider } from 'react-redux';
import store from '../src/redux/store';

test('search term updates correctly', () => {
  const { getByPlaceholderText } = render(
    <Provider store={store}>
      <Navbar />
    </Provider>
  );

  const searchInput = getByPlaceholderText('Search Items..');

  fireEvent.change(searchInput, { target: { value: 'test' } });

  expect(searchInput.value).toBe('test');
});
