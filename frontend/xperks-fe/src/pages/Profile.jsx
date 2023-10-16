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
        axios.get(`/api/user/${auth.id}` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
        .then(res => {
            console.log(res.data)
        setUserData(res.data)
        setInputAddressVal(res.data.erdAddress)
        })
        .catch((error) => {
        console.log(error)
        });
    }, [userData.erdAddress])
    

    const  updateAddrHandle = async () =>{

         await axios({
            method: 'PUT',
            url: `/api/user/${auth.id}/erd-address`,
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
    <div className='text-white h-[80vh] mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <div className='flex flex-col items-center justify-center h-96 '>
            <h1 className='text-[50px]  p-20 text-[#8D8D8D]'>My Profile</h1>
                <dl className="grid grid-cols-[repeat(2,auto)] gap-x-5 w-max  gap-y-3 text-sm md:text-xl">
                    <dt>First name:</dt>
                    <dd className="text-left">{userData.firstName}</dd>

                    <dt>Last name:</dt>
                    <dd className="text-left">{userData.lastName}</dd>

                    <dt>Email:</dt>
                    <dd className="text-left">{userData.emailAddress}</dd>

                    <dt>Birthdate:</dt>
                    <dd className="text-left">{userData.dateOfBirth}</dd>

                    <dt>Empl. Date:</dt>
                    <dd className="text-left">{userData.employmentDate}</dd>

                    <dt>Superior:</dt>
                    <dd className="text-left">{userData.superior?.firstName + " " + userData.superior?.lastName}</dd>
                    
                    <dt>Elrond wallet:</dt>
                    <dd className="text-left">
                        <input className='text-black px-2' type='text' ref={inputRef_mvsx_addr} value={inputAddressVal} onChange={inputAddrHandler}></input>
                    </dd>
                    <dd className="text-left">
                        <button className='mt-2 bg-white text-black px-2' onClick={updateAddrHandle}>Update</button>
                    </dd>
                </dl>
        </div>
    </div>
  )
}

export default Profile