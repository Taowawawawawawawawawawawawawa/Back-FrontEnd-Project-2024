import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

function Product({ item, className }) {

  const productImage = item.imageURL || '/default-image.jpg';

  return (
    <li className={className}>
      <Link to={`/show-product/${item.id}`}>
        <img
          className="Products__image"
          src={productImage}
          alt={item.name}
          onError={(e) => (e.target.src = '/default-image.jpg')} 
        />
        <div className="Products__name">{item.name}</div>
        <small className="Products__type">{item.genre}</small>
      </Link>
    </li>
  );
}

Product.propTypes = {
  item: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    genre: PropTypes.string,
    imageURL: PropTypes.string 
  }).isRequired,
  className: PropTypes.string.isRequired
};

export default styled(Product)`
  padding-right: 12px;
  padding-bottom: 36px;
  padding-left: 12px;
  width: 100%;
  max-width: 300px; 
  position: relative;

  .Products__name {
    color: #fff;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    width: 100%;
    display: block;
  }

  .Products__type {
    color: #767676;
  }

  .Products__image {
    width: 100%;
    height: 450px;
    object-fit: cover;
    border-radius: 8px;
  }

  @media (max-width: 768px) {
    max-width: 200px; /* ปรับขนาดสำหรับหน้าจอขนาดเล็ก */
  }
`;
