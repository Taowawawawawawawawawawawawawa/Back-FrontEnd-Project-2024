import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios'; 

const Login = ({ className }) => {
  const [telnumber, setTelnumber] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (telnumber && password) {
      try {
        // Sending login request to backend
        const response = await axios.post('http://localhost:8200/login', {
          phoneNum: telnumber,
          pass: password,
        });
  
        if (response.status === 200) {
          const token = response.data; // Assume token is returned in the response
          localStorage.setItem('authToken', token); // Store token in localStorage
          navigate('/'); // Redirect to profile
        }
      } catch (error) {
        if (error.response) {
          alert(error.response.data); // Display the error message from the backend
        } else {
          alert('An error occurred: ' + error.message);
        }
      }
    } else {
      alert('โปรดใส่ข้อมูลให้ครบ');
    }
  };

  const handleRegister = () => {
    navigate('/Register');
  };

  return (
    <div className={className}>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="เบอร์โทรศัพท์"
        value={telnumber}
        onChange={(e) => setTelnumber(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <div className="button-container">
        <button onClick={handleLogin}>Login</button>
        <button className="register-button" onClick={handleRegister}>Register</button>
      </div>
    </div>
  );
};

const StyledLogin = styled(Login)`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #262626;
  padding: 40px;
  color: #fff;

  input {
    margin: 10px;
    padding: 15px;
    width: 700px;
    border: 1px solid #ddd;
    border-radius: 6px;
    font-size: 16px;
  }

  .button-container {
    display: flex;
    gap: 15px;
  }

  button {
    padding: 15px 25px; 
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 18px;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #45a049;
  }

  .register-button {
    background-color: #007bff;
    color: white;
  }

  .register-button:hover {
    background-color: #0056b3;
  }

  h2 {
    margin-bottom: 20px;
    font-size: 2rem;
  }
`;

export default StyledLogin;
