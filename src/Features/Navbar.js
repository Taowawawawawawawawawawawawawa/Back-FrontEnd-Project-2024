import React, { useState } from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

function Navbar({ className }) {
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [searchTerm, setSearchTerm] = useState('');

  const handleCategoryChange = (event) => {
    setSelectedCategory(event.target.value);
  };

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  return (
    <header className={className}>
      <Link to="/" className="brand">
        DO MOVIE MAI YUPP
      </Link>
      <div className="nav-items">
        <Link to="/" className="Menu">
          HOME
        </Link>
      

        <div className="category-selector">
          <select
            value={selectedCategory}
            onChange={handleCategoryChange}
          >
            <option value="All">All Movie</option>
            <option value="Action">Action</option>
            <option value="Animation">Animation</option>
            <option value="Comedy">Comedy</option>
            <option value="Drama">Drama</option>
            <option value="Horror">Horror</option>
            <option value="Mystery">Mystery</option>
            <option value="Romance">Romance</option>
            <option value="Sci-Fi">Sci-Fi </option>
          </select>
        </div>
        
        <div className="search-container">
          <input
            type="text"
            placeholder="Search..."
            value={searchTerm}
            onChange={handleSearchChange}
            className="search-input"
          />
        </div>
       
        <Link to="/Login" className="Login-product">Login</Link>
        <Link to="/Profile" className="Login-product">Profile</Link>
      </div>
    </header>
  );
}
  
Navbar.propTypes = {
  className: PropTypes.string.isRequired
};

export default styled(Navbar)`
  height: 80px;
  width: 100%;
  display: flex;
  align-items: center;
  background-color: #FFC0CB;
  border-bottom: 1px solid #dee2e6;
  box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
  padding: 0 2rem;
  position: fixed;
  z-index: 100;
  

 

  .brand {
    font-family: "Copperplate", "Copperplate", fantasy;
    font-weight: bold;
    font-size: 2.0rem;
    text-decoration: none;
    color: inherit;
  }
  

  .nav-items {
    display: flex;
    align-items: center;
    margin-left: auto; 
  }

  .search-container {
    margin-right: 1rem;
  }

  .search-input {
    padding: 0.5rem;
    border: 1px solid #dee2e6;
    border-radius: 4px;
    font-size: 1rem;
    width: 200px;
  }

  .category-selector {
    margin-right: 1rem;
  }

  .category-selector select {
    padding: 0.5rem;
    border: 1px solid #dee2e6;
    border-radius: 4px;
    font-size: 1rem;
  }

  .Login-product {
    text-decoration: none;
    color: #007bff;
    font-weight: bold;
  }

  .Login-product:hover {
    text-decoration: underline;
  }

  a {
    text-decoration: none;
    color: inherit;
    margin-right: 1rem;
  }

  a:hover {
    text-decoration: underline;
  }
`;
