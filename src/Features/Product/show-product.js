import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import axios from 'axios';

function ShowProduct({ className }) {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [showPaymentScreen, setShowPaymentScreen] = useState(false);
  const [showtimes, setShowtimes] = useState([]); // เก็บข้อมูลรอบฉาย
  const [selectedShowtime, setSelectedShowtime] = useState(null); // เก็บค่าที่เลือก

  useEffect(() => {
    async function fetchProduct() {
      try {
        const response = await axios.get(`https://apimocha.com/662110195/products/${id}`);
        setProduct(response.data);
      } catch (error) {
        console.error('Error fetching product details:', error);
      }
    }

    async function fetchShowtimes() {
      try {
        const response = await axios.get(`https://apimocha.com/662110195/showtimes/${id}`); // จำลองการดึงข้อมูล showtimes
        setShowtimes(response.data);
      } catch (error) {
        console.error('Error fetching showtimes:', error);
      }
    }

    fetchProduct();
    fetchShowtimes();
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
    setShowPaymentScreen(true);
  };

  const closePaymentScreen = () => {
    setShowPaymentScreen(false);
  };

  if (!product) return <div>Loading product details...</div>;

  const defaultImage = '/path/to/default/image.jpg';
  const productImage = product.imageURL ? require(`../../assets/${product.imageURL}`) : defaultImage;
  const productName = product.name || 'Unknown Product Name';

  return (
    <div className={className}>
      <div className="product-details">
        <img className="product-image" src={productImage.default || productImage} alt={productName} />
        <div className="product-info">
          <h1 className="product-name">{productName}</h1>
          <p className="product-type">{product.genre || 'Unknown Type'}</p>
          <p className="product-length">{product.length || 'Unknown time'}</p>
          <p className="product-description">{product.description || 'No description available.'}</p>

          {/* ส่วนการเลือกโรงหนังและเวลา */}
          <div className="showtime-selector">
            <label htmlFor="showtime-select">เลือกโรงหนังและเวลา:</label>
            <select
              id="showtime-select"
              value={selectedShowtime}
              onChange={(e) => setSelectedShowtime(e.target.value)}
            >
              <option value="">-- กรุณาเลือก --</option>
              {showtimes.map((showtime) => (
                <option key={showtime.id} value={showtime.id}>
                  โรงที่ {showtime.theatreID} เวลา {showtime.time} วันที่ {showtime.date}
                </option>
              ))}
            </select>
          </div>

  
          {selectedShowtime && (
            <>
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
              <p className="product-price">{selectedSeats.length * 170 || product.price || 0} บาท</p>
              <StyledButton 
                onClick={handlePayment}
                disabled={selectedSeats.length === 0}
              >
                จองตั๋ว
              </StyledButton>
            </>
          )}
        </div>
      </div>

      {showPaymentScreen && (
        <div className="payment-screen">
          <div className="payment-details">
            <h2>ชำระเงิน</h2>
            <p>ชื่อหนัง : {productName}</p>
            <p>ที่นั่งที่เลือก: {selectedSeats.join(', ')}</p>
            <p>ราคารวม: {selectedSeats.length * 170} บาท</p>
            <img src={require('../img/Qr-Nungnon.jpg')} alt="QR Code" />
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

// Styled button 
const StyledButton = styled.button`
  background-color: ${(props) => (props.disabled ? '#ccc' : '#FFC0CB')};
  border: none;
  color: ${(props) => (props.disabled ? '#666' : 'black')};
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  transition-duration: 0.4s;;
  margin-top: 50px;

  &:hover {
    background-color: ${(props) => (props.disabled ? '#ccc' : '#FFC0CB')};
    color: ${(props) => (props.disabled ? '#666' : 'white')};
  }
`;

export default styled(ShowProduct)`
  /* Your existing styles */
  margin-top: 50px;

  .showtime-selector {
    margin-bottom: 20px;
  }

  .screen {
    margin: 20px auto;
    width: 80%;
    background-color: #333;
    color: #fff;
    padding: 10px;
    border-radius: 5px;
    text-align: center;
  }

  .cinema {
    background-color: #444;
    padding: 20px;
    border-radius: 10px;
    display: grid;
    gap: 20px;
  }

  .row {
    display: flex;
    justify-content: center;
    gap: 20px;
  }

  .seat {
    width: 50px;
    height: 50px;
    background-color: #555;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s;
  }

  .seat.selected {
    background-color: #FFC0CB;
  }

  .seat:hover {
    background-color: #FFC0CB;
  }

  .product-details {
    display: flex;
    padding: 20px;
    background-color: #1c1c1c;
    border-radius: 8px;
  }

  .product-image {
    width: 300px;
    height: 450px;
    object-fit: cover;
    border-radius: 8px;
    margin-right: 20px;
  }

  .product-info {
    color: #ffffff;
    flex: 1;
  }

  .product-name {
    font-size: 2rem;
    margin-bottom: 10px;
  }

  .product-type {
    font-size: 1.2rem;
    color: #ff6347;
    margin-bottom: 20px;
  }

  .product-description {
    font-size: 1rem;
    line-height: 1.5;
  }

  .product-price {
    font-size: 25px;
  }

  /* Payment screen styles */
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
    padding: 40px;
    border-radius: 10px;
    text-align: center;
    max-width: 500px;
  }

  .payment-details h2 {
    margin-bottom: 20px;
  }

  .payment-details img {
    margin-top: 30px;
  }
`;
