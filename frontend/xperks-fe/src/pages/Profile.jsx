import React from 'react'
import { useState, useEffect } from 'react';

const Profile = () => {
    const [userData, setUserData] = useState([]);

    useEffect(() => {
       fetch('http://192.168.0.174:8080/user/1')
          .then((response) => response.json())
          .then((data) => {
             console.log(data);
             setUserData(data);
          })
          .catch((err) => {
             console.log(err.message);
          });
        }, []);

  return (
    <div className='text-white bg-red-500  h-[90vh] mx-10 w-screen'>
        <p>Show user information</p>
        <h1>Hello, {userData.firstName}</h1>
        <h1>your email is: {userData.emailAddress}</h1>
        <h1>you were born on: {userData.dateOfBirth}</h1>
    </div>
  )
}

export default Profile