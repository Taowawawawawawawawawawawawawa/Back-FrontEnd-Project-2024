import { createAction } from '@reduxjs/toolkit';
import axios from 'axios'; // Axios to make HTTP requests

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
export const updateProfile = createAction('UPDATE_PROFILE');
export const deleteProfileSuccess = createAction('DELETE_PROFILE_SUCCESS');

// Async action to delete a profile
export const deleteProfile = (userId) => async (dispatch) => {
  try {
    const token = localStorage.getItem('authToken'); // Get the token from localStorage
    const response = await axios.delete(`http://localhost:8200/customers/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`, // Send the token for authorization
      },
    });

    // Check if the response status is 200 (OK)
    if (response.status === 200) {
      dispatch(deleteProfileSuccess()); // Dispatch success action
      localStorage.removeItem('authToken'); // Remove token after account deletion
      alert('Your account has been deleted successfully');
    } else {
      alert('Failed to delete your account. Please try again.');
    }
  } catch (error) {
    console.error('Failed to delete profile:', error);
    alert('Failed to delete your account. Please try again.');
  }
};
