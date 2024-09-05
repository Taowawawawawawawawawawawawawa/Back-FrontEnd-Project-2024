import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import { useDispatch } from 'react-redux'; 
import axios from 'axios';
import { updateProfile, deleteProfile } from './Product/actions'; 
import { useNavigate } from 'react-router-dom'; 
function Profile({ className }) {
  const [user, setUser] = useState(null);
  const dispatch = useDispatch(); 
  const navigate = useNavigate(); 

  useEffect(() => {
    async function fetchUser() {
      try {
        const response = await axios.get('/'); 
        setUser(response.data);
      } catch (error) {
        console.error('Error fetching user profile:', error);
      }
    }
    fetchUser();
  }, []);

  if (!user) return <div>Loading user profile...</div>;

  const handleUpdate = () => {
    dispatch(updateProfile(user));
    navigate('/Product/update'); // แก้ให้ตรงกับเส้นทางใน App.js
  };
  

  const handleDelete = () => {
    dispatch(deleteProfile(user.id)); 
    navigate('/');  
  };

  return (
    <div className={className}>
      <div className="profile-header">
        <h1 className="profile-name">{user.name}</h1>
      </div>
      <div className="profile-details">
        <h2>Profile</h2>
        <p><strong>Username:</strong> {user.email}</p>
        <p><strong>Phone:</strong> {user.phone}</p>
      </div>
      <div className="button-group">
        <button className="edit-button" onClick={handleUpdate}>Edit Profile</button>
        <button className="delete-button" onClick={handleDelete}>Delete Account</button>
      </div>
    </div>
  );
}

Profile.propTypes = {
  className: PropTypes.string.isRequired,
};

export default styled(Profile)`
  h2 {
    color: #333;
  }
  
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .profile-header {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }

  .profile-name {
    font-size: 2rem;
    color: #333;
  }

  .profile-details {
    background-color: #f4f4f4;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 20px;
  }

  .button-group {
    display: flex;
    gap: 10px;
  }

  .edit-button, .delete-button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
  }

  .edit-button:hover {
    background-color: #45a049;
  }

  .delete-button {
    background-color: #f44336;
  }

  .delete-button:hover {
    background-color: #e53935;
  }
`;
