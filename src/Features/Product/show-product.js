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

  if (loading) return <div>Loading product details...</div>;
  if (error) return <div>Failed to load product details. Please try again later.</div>;
  if (!product) return <div>Product not found.</div>;

  const productImage = product.imageURL || defaultImage;
  const productName = product.name || 'Unknown Product Name';
  const showtimes = product.showtimes || ['11:00-13:00', '14:00-16:00'];

  return (
    <div className={className}>
      <div className="product-details">
        <img className="product-image" src={productImage} alt={productName} />
        <div className="product-info">
          <h1 className="product-name">{productName}</h1>
          <p className="product-type">{product.genre || 'Unknown Type'}</p>
          <p className="product-length">{product.length || 'Unknown time'}</p>
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
  .showtime-selector {
    margin: 20px 0;
  }

  .showtime-selector select {
    padding: 10px;
    font-size: 1rem;
  }

  .cinema {
    margin-top: 20px;
  }

  .screen {
    margin: 20px auto;
    width: 80%;
    background-color: #333;
    color: #fff;
    padding: 10px;
    text-align: center;
    border-radius: 5px;
  }

  .row {
    display: flex;
    justify-content: center;
    gap: 10px;
    margin-bottom: 10px;
  }

  .seat {
    width: 30px;
    height: 30px;
    background-color: #555;
    color: white;
    border-radius: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
  }

  .seat.selected {
    background-color: #FFC0CB;
  }

  .seat:hover {
    background-color: #ff7f7f;
  }
`;
