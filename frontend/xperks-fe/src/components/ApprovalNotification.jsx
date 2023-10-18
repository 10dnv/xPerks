import React, { useState } from 'react'
import Avatar from 'react-avatar';
import useAuth from '../hooks/useAuth';
import axios from 'axios';

function ApprovalNotification() {
    const {auth} = useAuth()
    const [notifications, setNotifications] = useState(0)
    
    //Retunrs the number of transactions current user's have to sign
    const getApprovalNumber = async () =>{
        await axios({
            method: 'GET',
            url: `${process.env.REACT_APP_API_URL}/api/transaction/pending`,
            headers: {
              Authorization: `Bearer ${auth.token}}`
            },
          }).then(res => {
                setNotifications(res.data)
                })
            .catch((error) => {
               
                console.log(error)
                });
    };

    getApprovalNumber()

  return (
    <Avatar value={notifications}  className={notifications?'mx-1 font-bold' : 'hidden'} size='25' round={true} color='#24f7dd' fgColor='black' textSizeRatio={1.8}/>
  )
}

export default ApprovalNotification