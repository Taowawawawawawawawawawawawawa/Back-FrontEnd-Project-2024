import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import axios from 'axios';
import defaultImage from '../../assets/default-image.jpg';
import qrCodeImage from '../img/Qr-Nungnon.jpg';

function ShowProduct({ className }) {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [selectedShowtime, setSelectedShowtime] = useState('');
  const [showPaymentScreen, setShowPaymentScreen] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    async function fetchProduct() {
      try {
        const response = await axios.get(`http://localhost:8100/movies/${id}`);
        setProduct(response.data);
      } catch (error) {
        console.error('Error fetching product details:', error);
        setError(true);
      } finally {
        setLoading(false);
      }
    }

    fetchProduct();
  }, [id]);

  const toggleSeatSelection = (seat) => {
    setSelectedSeats((prevSelectedSeats) =>
      prevSelectedSeats.includes(seat)
        ? prevSelectedSeats.filter((s) => s !== seat)
        : [...prevSelectedSeats, seat]
    );
  };

  const handlePayment = () => {
    if (selectedSeats.length === 0) {
      alert('กรุณาเลือกที่นั่งก่อนจองตั๋ว!');
      return;
    }
    if (!selectedShowtime) {
      alert('กรุณาเลือกรอบหนัง!');
      return;
    }
    setShowPaymentScreen(true);
  };

  const closePaymentScreen = () => {
    setShowPaymentScreen(false);
  };

  const getProductImage = (imageName) => {
    console.log(imageName)
    if (imageName.startsWith('data:image')) {
      console.log(imageName)
      return imageName;
    }
    
    try {
      return require(`../../assets/${imageName}`);
    } catch (err) {
      return defaultImage; 
    }
  };

  if (loading) return <div>Loading product details...</div>;
  if (error) return <div>Failed to load product details. Please try again later.</div>;
  if (!product) return <div>Product not found.</div>;

  const productImage = getProductImage(product.picture || 'default.jpg'); // Get the product image
  console.log(product)
  const productName = product.name || 'Unknown Product Name';
  const showtimes = product.showtimes || ['11:00-13:00', '14:00-16:00', '16.00-18:00' , '18:00-20:00' , '20:00-22:00' , '22:00-24:00'];

  return (
    <div className={className}>
      <div className="product-details">
        <img
          className="product-image"
          src={productImage}
          alt={productName}
          onError={(e) => (e.target.src = defaultImage)} // Fallback to default image on error
        />
        <div className="product-info">
          <h1 className="product-name">{productName}</h1>
          <p className="product-type">{product.genre || 'Unknown Type'}</p>
          <p className="product-length">{product.length || 'Unknown time'}นาที</p>
          <p className="product-description">
            {product.description || 'No description available.'}
          </p>

          <div className="showtime-selector">
            <label htmlFor="showtime-select">เลือกรอบหนัง:</label>
            <select
              id="showtime-select"
              value={selectedShowtime}
              onChange={(e) => setSelectedShowtime(e.target.value)}
            >
              <option value="">กรุณาเลือกรอบหนัง</option>
              {showtimes.map((time) => (
                <option key={time} value={time}>
                  {time}
                </option>
              ))}
            </select>
          </div>

          <div className="screen">Screen</div>
          <div className="cinema">
            {['A', 'B', 'C', 'D', 'E', 'F'].map((row) => (
              <div key={row} className="row">
                {[...Array(10).keys()].map((_, index) => {
                  const seat = `${row}${index + 1}`;
                  return (
                    <div
                      key={seat}
                      className={`seat ${selectedSeats.includes(seat) ? 'selected' : ''}`}
                      onClick={() => toggleSeatSelection(seat)}
                      role="button"
                      aria-label={`Seat ${seat}`}
                    >
                      {seat}
                    </div>
                  );
                })}
              </div>
            ))}
          </div>
          <p className="product-price">
            {selectedSeats.length > 0 ? selectedSeats.length * 170 : product.price || 0} บาท
          </p>
          <StyledButton
            onClick={handlePayment}
            disabled={selectedSeats.length === 0 || !selectedShowtime}
          >
            จองตั๋ว
          </StyledButton>
        </div>
      </div>

      {showPaymentScreen && (
        <div className="payment-screen">
          <div className="payment-details">
            <h2>ชำระเงิน</h2>
            <p>ชื่อหนัง : {productName}</p>
            <p>รอบหนัง: {selectedShowtime}</p>
            <p>ที่นั่งที่เลือก: {selectedSeats.join(', ')}</p>
            <p>ราคารวม: {selectedSeats.length * 170} บาท</p>
            <img src={qrCodeImage} alt="QR Code" />
            <button onClick={closePaymentScreen}>ปิดหน้าต่าง</button>
          </div>
        </div>
      )}
    </div>
  );
}

ShowProduct.propTypes = {
  className: PropTypes.string.isRequired,
};

const StyledButton = styled.button`
  background-color: ${(props) => (props.disabled ? '#ccc' : '#FFC0CB')};
  border: none;
  color: ${(props) => (props.disabled ? '#666' : 'black')};
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  transition-duration: 0.4s;
  margin-top: 50px;

  &:hover {
    background-color: ${(props) => (props.disabled ? '#ccc' : '#FFC0CB')};
    color: ${(props) => (props.disabled ? '#666' : 'white')};
  } 
`;

export default styled(ShowProduct)`
  .product-details {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    margin: 20px auto;
    max-width: 1000px;
    width: 100%; // Make it responsive to screen size
  }

  .product-image {
    width: 100%;
    max-width: 400px;
    height: auto;
    border-radius: 10px;
    object-fit: cover;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
  }

  .product-info {
    text-align: center;
    padding: 0 20px;
    max-width: 100%;
  }

  .product-name {
    font-size: 2rem;
    font-weight: bold;
    margin-bottom: 10px;
    color: #333;
  }

  .product-type, .product-length {
    font-size: 1.2rem;
    color: #666;
    margin-bottom: 10px;
  }

  .product-description {
    font-size: 1rem;
    color: #666;
    margin-bottom: 10px;
    max-height: 150px;
    overflow-y: auto;
    padding: 10px;
    text-align: justify;
  }

  .showtime-selector {
    margin: 20px 0;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .showtime-selector select {
    padding: 10px;
    font-size: 1rem;
    border-radius: 5px;
    border: 1px solid #ccc;
    width: 200px;
  }

  .screen {
    width: 100%;
    max-width: 100%;
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 10px;
    border-radius: 5px;
    margin: 20px 0;
    font-weight: bold;
    font-size: 1.2rem;
    display: flex;
    justify-content: center;
  }

  .cinema {
    display: flex;
    flex-direction: column;
    gap: 15px;
    margin-top: 10px;
  }

  .row {
    display: flex;
    justify-content: center;
    gap: 10px;
  }

  .seat {
    width: 40px;
    height: 40px;
    background-color: #555;
    color: white;
    border-radius: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .seat.selected {
    background-color: #FFC0CB;
  }

  .seat:hover {
    background-color: #ff7f7f;
  }
  
  .product-price {
    font-size: 1.5rem;
    font-weight: bold;
    color: #333;
    margin: 20px 0;
  }

  .payment-screen {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.7);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }

  .payment-details {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    width: 300px;
    text-align: center;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
  }

  .payment-details h2 {
    margin-bottom: 20px;
    color: #333;
    font-size: 1.8rem;
  }

  .payment-details p {
    font-size: 1.2rem;
    margin: 10px 0;
    color: #666;
  }

  .payment-details img {
    margin: 20px 0;
    width: 150px;
    height: 150px;
    object-fit: contain;
  }

  .payment-details button {
    background-color: #ff7f7f;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
    transition: background-color 0.3s ease;
  }

  .payment-details button:hover {
    background-color: #ff5252;
  }

  /* Media Queries for Responsiveness */
  @media (max-width: 768px) {
    .product-details {
      max-width: 95%; // Reduce max-width on smaller screens
    }

    .product-name {
      font-size: 1.5rem;
    }

    .product-type, .product-length {
      font-size: 1rem;
    }

    .screen {
      font-size: 1rem;
    }

    .cinema .seat {
      width: 30px;
      height: 30px;
    }

    .product-price {
      font-size: 1.2rem;
    }
  }

  @media (max-width: 480px) {
    .product-details {
      max-width: 100%;
      padding: 10px; // Reduce padding on very small screens
    }

    .product-name {
      font-size: 1.2rem;
    }

    .product-type, .product-length, .product-price {
      font-size: 0.9rem;
    }

    .showtime-selector select {
      width: 100%; // Make select box full width on small screens
    }

    .cinema .seat {
      width: 25px;
      height: 25px; // Make seats smaller on mobile
    }

    .payment-details {
      width: 90%; // Make payment details section take most of the screen on mobile
    }

    .payment-details h2 {
      font-size: 1.5rem;
    }

    .payment-details p {
      font-size: 1rem;
    }
  }
`;
