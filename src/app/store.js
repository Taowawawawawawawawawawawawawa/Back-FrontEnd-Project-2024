import { configureStore } from '@reduxjs/toolkit';
import productReducers from '../Features/Product/reducers';

export default configureStore({
  reducer: {
    products: productReducers
  }
});
