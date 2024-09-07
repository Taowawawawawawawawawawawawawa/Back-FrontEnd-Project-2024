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
  justify-content: space-between; /* Ensure brand and nav items are spaced out */
  
  .brand {
    font-family: "Copperplate", fantasy;
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

  /* Media queries for responsiveness */
  @media (max-width: 768px) {
    .nav-items {
      flex-direction: column;
      position: absolute;
      top: 80px;
      right: 0;
      background-color: #FFC0CB;
      width: 100%;
      padding: 1rem;
      display: none; /* Initially hidden for mobile */
    }

    .nav-items.active {
      display: flex; /* Show when active */
    }

    .search-input {
      width: 100%; /* Full width search bar on smaller screens */
      margin-bottom: 1rem;
    }

    .Login-product {
      margin-bottom: 1rem;
    }

    .brand {
      font-size: 1.5rem; /* Adjust font size for brand */
    }

    .search-container {
      width: 100%;
      margin-right: 0;
    }
  }

  @media (max-width: 480px) {
    .brand {
      font-size: 1.2rem; /* Further reduce brand size on very small screens */
    }

    .search-input {
      font-size: 0.9rem;
    }

    .Login-product {
      font-size: 0.9rem;
    }
  }

  /* Hamburger menu for mobile screens */
  .hamburger {
    display: none;
    cursor: pointer;
    font-size: 2rem;
  }

  @media (max-width: 768px) {
    .hamburger {
      display: block; /* Show hamburger menu on mobile */
    }
  }
`;