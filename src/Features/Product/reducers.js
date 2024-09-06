import { createReducer } from '@reduxjs/toolkit';
import {
  fetchProducts 
} from './actions';

const productsReducer = createReducer([], (builder) => {
  builder
    .addCase(fetchProducts, (state, action) => {
      return action.payload;
    });
});
export default productsReducer;
