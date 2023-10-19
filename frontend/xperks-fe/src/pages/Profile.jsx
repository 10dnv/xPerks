import React from 'react'
import { useState, useEffect, useRef } from 'react';
import useAuth from '../hooks/useAuth';
import axios from 'axios';
import {NotificationContainer, NotificationManager} from 'react-notifications';

const Profile = () => {
    const [userData, setUserData] = useState([]);
    const {auth} = useAuth()
    const inputRef_mvsx_addr = useRef(null);

    const [inputAddressVal, setInputAddressVal] = useState("")

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/api/user/${auth.id}` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
        .then(res => {
            console.log(res.data)
        setUserData(res.data)
        setInputAddressVal(res.data.erdAddress)
        })
        .catch((error) => {
        console.log(error)
        });
    }, [])
    

    const  updateAddrHandle = async () =>{

         await axios({
            method: 'PUT',
            url: `${process.env.REACT_APP_API_URL}/api/user/erd-address`,
            headers: {
              Authorization: `Bearer ${auth.token}}`
            },
            params :{'erdAddress': `${inputRef_mvsx_addr.current.value}`}
          }).then(res => {
              NotificationManager.success("Successfully updated mvsx address!", "Success!")
                })
            .catch((error) => {
                NotificationManager.error("Error updating mvsx address!", "Error")
                console.log(error)
                });
    }

    const inputAddrHandler =(e) =>{
        setInputAddressVal(e.target.value)
    }

  return (
    <div className='text-white h-auto mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <h1 className='text-[50px]  text-center text-[#8D8D8D]'>My Profile</h1>
  <div class="relative wrap overflow-hidden p-10 h-full">
    <div class="border-2-2 absolute border-opacity-20 border-gray-700 h-[80%] border" style={{left: "50%"}}></div>
        {/* <!-- right timeline --> */}
    <div class="mb-8 flex justify-between items-center w-full right-timeline">
      <div class="order-1 w-5/12"></div>
      <div class="z-20 flex items-center order-1 bg-elrond shadow-xl w-8 h-8 rounded-full">
        <h1 class="mx-auto font-semibold text-lg text-gray-900">1</h1>
      </div>
      <div class="order-1 bg-elrond rounded-lg shadow-xl w-5/12 px-6 py-4">
        <h3 class="mb-3 font-bold text-gray-800 text-xl">Full Name</h3>
        <p class="text-sm leading-snug tracking-wide text-gray-900 text-opacity-100">{userData.firstName + " " + userData.lastName} </p>
      </div>
    </div>

    {/* <!-- left timeline --> */}
    <div class="mb-8 flex justify-between flex-row-reverse items-center w-full left-timeline">
      <div class="border-1 w-5/12"></div>
      <div class="z-20 flex items-center order-1 bg-elrond shadow-xl w-8 h-8 rounded-full">
        <h1 class="mx-auto text-gray-800 font-semibold text-lg">2</h1>
      </div>
      <div class="order-1 bg-elrond rounded-lg shadow-xl w-5/12 px-6 py-4">
        <h3 class="mb-3 font-bold text-gray-800 text-xl">Email</h3>
        <p class="text-sm font-medium leading-snug tracking-wide text-gray-800 text-opacity-100">{userData.emailAddress}</p>
      </div>
    </div>
    
    {/* <!-- right timeline --> */}
    <div class="mb-8 flex justify-between items-center w-full right-timeline">
      <div class="order-1 w-5/12"></div>
      <div class="z-20 flex items-center order-1 bg-elrond shadow-xl w-8 h-8 rounded-full">
        <h1 class="mx-auto font-semibold text-lg text-gray-800">3</h1>
      </div>
      <div class="order-1 bg-elrond rounded-lg shadow-xl w-5/12 px-6 py-4">
        <h3 class="mb-3 font-bold text-gray-800 text-xl">Birthdate</h3>
        <p class="text-sm leading-snug tracking-wide text-gray-900 text-opacity-100">{userData.dateOfBirth}</p>
      </div>
    </div>

    {/* <!-- left timeline --> */}
    <div class="mb-8 flex justify-between flex-row-reverse items-center w-full left-timeline">
      <div class="order-1 w-5/12"></div>
      <div class="z-20 flex items-center order-1 bg-elrond shadow-xl w-8 h-8 rounded-full">
        <h1 class="mx-auto text-gray-800 font-semibold text-lg">4</h1>
      </div>
      <div class="order-1 bg-elrond rounded-lg shadow-xl w-5/12 px-6 py-4">
        <h3 class="mb-3 font-bold text-gray-800 text-xl">Employment date</h3>
        <p class="text-sm font-medium leading-snug tracking-wide text-gray-800 text-opacity-100">{userData.employmentDate}</p>
      </div>
    </div>

     {/* <!-- right timeline --> */}
     <div class="mb-8 flex justify-between items-center w-full right-timeline">
      <div class="order-1 w-5/12"></div>
      <div class="z-20 flex items-center order-1 bg-elrond shadow-xl w-8 h-8 rounded-full">
        <h1 class="mx-auto font-semibold text-lg text-gray-800">5</h1>
      </div>
      <div class="order-1 bg-elrond rounded-lg shadow-xl w-5/12 px-6 py-4">
        <h3 class="mb-3 font-bold text-gray-800 text-xl">Superior</h3>
        <p class="text-sm leading-snug tracking-wide text-gray-900 text-opacity-100">{userData.superior?.firstName + " " + userData.superior?.lastName}</p>
      </div>
    </div>
{/* <!-- left timeline --> */}
<div class="mb-8 flex justify-between flex-row-reverse items-center w-full left-timeline">
      <div class="order-1 w-5/12"></div>
      <div class="z-20 flex items-center order-1 bg-elrond shadow-xl w-8 h-8 rounded-full">
        <h1 class="mx-auto text-gray-800 font-semibold text-lg">6</h1>
      </div>
      <div class="order-1 bg-elrond rounded-lg shadow-xl w-5/12 px-6 py-4">
        <h3 class="mb-3 font-bold text-gray-800 text-xl">MultiversX wallet</h3>
        <p class="text-sm font-medium leading-snug tracking-wide text-gray-800 text-opacity-100">
            <div className='flex-col'>
                <input className='text-black px-1 w-[100%]' type='text' ref={inputRef_mvsx_addr} value={inputAddressVal} onChange={inputAddrHandler}></input>
                <button className='mt-2 bg-white text-black px-2' onClick={updateAddrHandle}>Update</button>
            </div>
        </p>
      </div>
    </div>

  </div>
</div>
// </div>
  )
}

export default Profile