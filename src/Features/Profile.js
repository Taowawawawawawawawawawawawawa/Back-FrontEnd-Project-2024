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

  // Fetch the user data using the token
  useEffect(() => {
    async function fetchUser() {
      const token = localStorage.getItem('authToken'); // Get the auth token from localStorage

      if (!token) {
        alert('You are not logged in!');
        navigate('/login');
        return;
      }

      try {
        const response = await axios.get('http://localhost:8200/profile', {
          headers: {
            Authorization: `Bearer ${token}` // Send the token in the Authorization header
          }
        });
        setUser(response.data); // Assuming response.data contains the user information
      } catch (error) {
        console.error('Error fetching user profile:', error);
        if (error.response && error.response.status === 401) {
          alert('Your session has expired, please log in again.');
          navigate('/login');
        } else {
          alert('Error occurred while fetching profile');
        }
      }
    }
    fetchUser();
  }, [navigate]);

  // If user data hasn't been fetched yet
  if (!user) return <div>Loading user profile...</div>;

  const handleUpdate = () => {
    dispatch(updateProfile(user)); // Use the updateProfile action
    navigate('/Product/update');
  };

  const handleDelete = () => {
    if (window.confirm('Are you sure you want to delete your account? This action cannot be undone.')) {
      dispatch(deleteProfile(user.id)) // Dispatch the delete action
        .then(() => {
          // This will only be triggered if the deleteProfile action succeeds
          alert('Your account has been successfully deleted.');
          navigate('/login'); // Redirect to login page
        })
        .catch((error) => {
          console.error('Failed to delete profile:', error);
          alert('Failed to delete your account. Please try again.');
        });
    } else {
      alert("หว่ายยยยย ไม่แน่จริงนี่หว่าาา");
    }
  };
  


  const handleLogout = () => {
    localStorage.removeItem('authToken'); // Clear token from localStorage
    navigate('/login'); // Redirect to login page
  };

  return (
    <div className={className}>
      <div className="profile-header">
        <h1 className="profile-name">{user.name}</h1>
      </div>
      <div className="profile-details">
        <h2>Profile</h2>
        <p><strong>Username:</strong> {user.name}</p>
        <p><strong>Phone:</strong> {user.phoneNum}</p>
      </div>
      <div className="button-group">
        <button className="edit-button" onClick={handleUpdate}>Edit Profile</button>
        <button className="delete-button" onClick={handleDelete}>Delete Account</button>
        <button className="logout-button" onClick={handleLogout}>Logout</button>
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
    color: #f4f4f4;
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

  .edit-button, .delete-button, .logout-button {
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

  .logout-button {
    background-color: #007bff;
  }

  .logout-button:hover {
    background-color: #0056b3;
  }
`;
