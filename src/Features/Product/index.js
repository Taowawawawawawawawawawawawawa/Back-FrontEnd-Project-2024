  import React, { useEffect, useState } from 'react';
  import styled from 'styled-components';
  import { Link, useNavigate } from 'react-router-dom';
  import axios from 'axios';
  import defaultImage from '../../assets/default-image.jpg'; // Import default image

  const ProductList = () => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate(); // useNavigate to programmatically navigate

    // Fetch products from the API
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8100/movies/all');
        setProducts(response.data);
      } catch (error) {
        console.error('Error fetching products:', error);
      } finally {
        setLoading(false); 
      }
    };

    useEffect(() => {
      fetchProducts();
    }, []);

    // Function to load image from assets or use base64 encoded image
    const getProductImage = (imageName) => {
      if (imageName.startsWith('data:image')) {
        return imageName; 
      }
      try {
        return require(`../../assets/${imageName}`);
      } catch (err) {
        return defaultImage; 
      }
    };

    // const handleProductClick = (id, name) => {
    //   console.log(`Product clicked: ${name}`);
    //   navigate(`/show-product/${id}`); // Navigate to ShowProduct with the selected product ID
    // };

    if (loading) {
      return <div>Loading products...</div>;
    }

    return (
      <StyledProductList>
        {products.map((item) => {
          const { id, name, genre, picture } = item;
          const productImage = getProductImage(picture);

          return (
            <Link to={`/show-product/${id}`} key={id} className="Products__item">
            <img
              className="Products__image"
              src={productImage}
              alt={name}
              onError={(e) => (e.target.src = defaultImage)}
            />
            <div className="Products__details">
              <div className="Products__name">{name}</div>
              <small className="Products__type">{genre}</small>
            </div>
          </Link>
          );
        })}
      </StyledProductList>
    );
  };


  export default styled(ProductList)``;

  // Styled component for the product list
  const StyledProductList = styled.ul`
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
    padding: 20px;
    list-style: none;

    .Products__item {
      background-color: #1e1e1e;
      border-radius: 10px;
      padding: 10px;
      width: 260px;
      text-align: center;
      transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);

      &:hover {
        transform: translateY(-10px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
      }

      .clickable {
        cursor: pointer;  // Make it clickable
        text-decoration: none;
        color: inherit;
      }

      .Products__image {
        width: 100%;
        height: 300px;
        object-fit: cover;
        border-radius: 8px;
      }

      .Products__details {
        padding: 10px 0;
      }

      .Products__name {
        color: #fff;
        font-size: 18px;
        font-weight: bold;
        margin: 10px 0;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }

      .Products__type {
        color: #767676;
        font-size: 14px;
      }
    }

    @media (max-width: 768px) {
      .Products__item {
        width: 150px;

        .Products__image {
          height: 200px;
        }
      }
    }
  `;
