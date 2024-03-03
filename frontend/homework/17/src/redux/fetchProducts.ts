import { createAsyncThunk } from '@reduxjs/toolkit';
import { RootState } from './store';

export interface Product {
  id: number;
  title: string;
  price: number;
  category: string;
  description: string;
  image: string;
  rating: {
    rate: number;
    count: number;
  }
}

export const fetchProducts = createAsyncThunk<Product[], void, { state: RootState }>(
  'products/fetchProducts',
  async () => {
    const response = await fetch('https://fakestoreapi.com/products');
    const data = await response.json();
    return data;
  }
);
