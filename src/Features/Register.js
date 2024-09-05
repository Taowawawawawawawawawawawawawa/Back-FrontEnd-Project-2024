import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const Login = ({ onLogin, className }) => {
  const [username, setUsername] = useState('');
  const [telnumber, setTelnumber] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  const handleRegister = () => {
    if (password !== confirmPassword) {
      alert('Passwords do not match');
      navigate('/Login');
      return;
    }
    else {
        alert('โปรดใส่ข้อมูลให้ครบ');
      }
  
  };




  return (
    <div className={className}>
      <h2>Register</h2>
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
`;

export default StyledLogin;
