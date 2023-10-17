
import React, { useState, useEffect } from 'react';
import useAuth from '../hooks/useAuth';
import axios from 'axios';
import Select from "react-select";

function SearchUser(props) {
    const {auth} = useAuth()
    const [userList, setUserList] = useState([]);
    // const [inputUser, setInputUser] = useState("");

    const handleUserChange = (e) => {
        props.handle(e.id);
        // console.log(e.id)
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
            <Select
                options={userList}
                className='w-[300px] text-black'
                onChange={handleUserChange}
                getOptionValue={(option) => option.id}
            />
    )
}

export default SearchUser