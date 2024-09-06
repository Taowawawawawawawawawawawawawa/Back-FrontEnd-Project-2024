import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';

import Product from './Product';

function Home({ className, products = [] }) {  // กำหนดค่าเริ่มต้นให้กับ products
  const isArray = Array.isArray(products);

  return (
    <div className={className}>
      <h1>Movie</h1>
      <ul className="Home__products">
        {isArray && products.length > 0 ? (
          products.map((product) => (
            <Product key={product.id} item={product} />
          ))
        ) : (
          <p>No products available</p>
        )}
      </ul>
    </div>
  );
}

Home.propTypes = {
  className: PropTypes.string.isRequired,
  products: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      name: PropTypes.string.isRequired,
      description: PropTypes.string,
      length: PropTypes.string,
      genre: PropTypes.string,
      picture: PropTypes.string
    })
  )
};

export default styled(Home)`
  h1 {
    color: #fff;
    text-align: center; 
    margin-bottom: 20px;
  }

  .Home__products {
    display: flex;
    flex-wrap: wrap;
    justify-content: center; 
    background-color: #262626;
    list-style-type: none;
    padding: 0;
    margin: 0 -12px;
  }

  p {
    color: #fff; 
    text-align: center;
    font-size: 18px;
  }
`;
  