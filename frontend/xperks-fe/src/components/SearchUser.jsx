
import React, { useState, useEffect } from 'react';
import useAuth from '../hooks/useAuth';
import axios from 'axios';
import Select, { components } from "react-select";

function SearchUser() {
    const {auth} = useAuth()
    const [userList, setUserList] = useState([]);
    const [inputUser, setInputUser] = useState("");

    const handleUserChange = (e) => {
        setInputUser(e.id);
        console.log(e.id)
      };

      useEffect(() => {
        findUser()
    },[] )

    const findUser = async () =>{
        //get
        
        await axios({
            method: 'GET',
            url: '/api/user/list',
            headers: {
              Authorization: `Bearer ${auth.token}}`
            },
          }).then(res => {
            
            res.data?.map((data,idx) =>(
                userList.push({label:data.firstName + " " + data.lastName, id:data.id})
            ))
                console.log(userList)
                })
            .catch((error) => {
               
                console.log(error)
                });

                
            };
            

    return (
        <>
        
        
            <Select
                options={userList}
                className='w-2/4 text-black'
                onChange={handleUserChange}
            />
        </>
    )
}

export default SearchUser