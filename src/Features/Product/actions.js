import { createAction } from '@reduxjs/toolkit';

// Actions
export const addProduct = createAction('ADD_PRODUCT');
export const fetchProducts = createAction('FETCH_PRODUCTS');
export const showProduct = createAction('SHOW_PRODUCT', function prepare(productId) {
  return {
    payload: {
      id: productId,
    },
  };
});
export const updateProfile = createAction('UPDATE_Profile');
export const deleteProfile = createAction('DELETE_Profile');
