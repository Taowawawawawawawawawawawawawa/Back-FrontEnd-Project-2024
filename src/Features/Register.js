import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios'; // Import axios to make HTTP requests

const Register = ({ className }) => {
  const [username, setUsername] = useState('');
  const [age, setAge] = useState('');
  const [telnumber, setTelnumber] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleRegister = async () => {
    if (!telnumber || !username ||!age || !password || !confirmPassword) {
      setError('Please fill out all fields.');
      return;
    }

    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }

    try {
      // Send registration request to backend
      const response = await axios.post('http://localhost:8200/customers', {
        
        name: username,
        age: age,
        phoneNum: telnumber,
        pass: password,
      });

      if (response.status === 201) {
        alert('Registration successful!');
        navigate('/login'); // Navigate to login page after successful registration
      }
    } catch (error) {
      if (error.response && error.response.status === 409) {
        setError('Phone number is already registered.');
      } else {
        setError('Registration failed. Please try again.');
      }
    }
  };

  return (
    <div className={className}>
      <h2>Register</h2>
      {error && <p className="error">{error}</p>}
      <input
        type="text"
        placeholder="เบอร์โทรศัพท์"
        value={telnumber}
        onChange={(e) => setTelnumber(e.target.value)}
      />
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="Number"
        placeholder="Age"
        value={age}
        onChange={(e) => setAge(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <input
        type="password"
        placeholder="Confirm Password"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
      />
      <div className="button-container">
        <button className="register-button" onClick={handleRegister}>Register</button>
      </div>
    </div>
  );
};

const StyledRegister = styled(Register)`
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
    gap: 15px; /* Adjust spacing between buttons */
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

  .error {
    color: red;
    margin-bottom: 20px;
  }
`;

export default StyledRegister;
