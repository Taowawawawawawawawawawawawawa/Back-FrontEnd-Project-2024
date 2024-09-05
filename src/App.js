import React, { useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import axios from 'axios';

import Navbar from './Features/Navbar';
import Container from './Features/Container';
import Home from './Features/Home';
import GlobalStyle from './Features/GlobalStyle';
import { fetchProducts } from './Features/Product/actions';
import ShowProduct from './Features/Product/show-product';
import Login from './Features/Login';
import Register from './Features/Register';
import Profile from './Features/Profile';
import EditProfile from './Features/Product/update'; 

function App() {
  const products = useSelector((state) => state.products);
  const dispatch = useDispatch();

  useEffect(() => {
    async function getProducts() {
      try {
        const response = await axios.get('https://apimocha.com/662110195/products');  //https://apimocha.com/662110195/products
        console.log('API Response:', response.data);
        if (Array.isArray(response.data)) {
          dispatch(fetchProducts(response.data));
        } else {
          console.error('Expected an array but received:', response.data);
        }
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    }
  
    getProducts();
  }, [dispatch]);
  

  return (
    <>
      <GlobalStyle />
      <Navbar />
      <Container>
        {products.length > 0 ? (
        <Routes>
          <Route path="/Product/update" element={<EditProfile />} /> 
          <Route path="/profile" element={<Profile />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/show-product/:id" element={<ShowProduct />} />
          <Route path="/" element={<Home products={products} />} />
        </Routes>
      
        ) : (
          <div>Loading products...</div>
        )}
      </Container>
    </>
  );
}

export default App;
