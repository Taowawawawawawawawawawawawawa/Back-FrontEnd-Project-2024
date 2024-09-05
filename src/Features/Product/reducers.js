import { createReducer } from '@reduxjs/toolkit';
import {
  fetchProducts // ตรวจสอบให้แน่ใจว่าใช้ชื่อที่ถูกต้อง
} from './actions';

const productsReducer = createReducer([], (builder) => {
  builder
    .addCase(fetchProducts, (state, action) => {
      return action.payload;
    });
});
export default productsReducer;
