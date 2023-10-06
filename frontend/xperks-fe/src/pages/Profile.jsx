import React from 'react'
import { useState, useEffect } from 'react';

const Profile = () => {
    const [userData, setUserData] = useState([]);

    useEffect(() => {
        fetch('/api/user/2')
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
    <div className='text-white h-[90vh] mx-10 w-screen'>
        <div className='flex flex-col items-center justify-center h-96 '>
            <h1 className='text-3xl py-4'>My Profile</h1>
            <ul>
                <li>First name: {userData.firstName} </li>
                <li>Last name: {userData.lastName} </li>
                <li>Email: {userData.emailAddress} </li>
                <li>Birthdate: {userData.dateOfBirth} </li>
                <li>Employment Date: {userData.employmentDate} </li>
                <li>Superior: {userData.superior?.firstName} </li>
                <li>
                    <span className='mr-4'> Elrond wallet: </span>
                    <input className='text-black px-2' type='text' value={userData.wallet?.erdAddress}></input>
                    <button className='mx-4 bg-white text-black px-2'>Update</button>
                </li>
            </ul>
        </div>
    </div>
  )
}

export default Profile