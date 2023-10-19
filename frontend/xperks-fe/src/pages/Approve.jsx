import React, { useState } from 'react'
import {NotificationContainer, NotificationManager} from 'react-notifications';
import axios from 'axios';
import useAuth from '../hooks/useAuth';
import { useEffect } from 'react';
import {BiCheckCircle, BiXCircle} from  "react-icons/bi";

function Approve() {
  const {auth} = useAuth();
  const [transactions, setTransactions] = useState([])

  const fetchData = async () =>{
    setTransactions([])
    await axios.get(`${process.env.REACT_APP_API_URL}/api/transaction/approval-request` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
    .then(res => {
      // console.log(res)
        res.data?.map((data,idx) =>(
          setTransactions(transactions => [...transactions, data])
        ))
    })
    .catch((error) => {
    console.log(error)
    });
  }

 
        
useEffect(() => {
   fetchData()
}, [])

const handleAcceptBtn = async (id) => {

  await axios({
    method: 'PUT',
    url: `${process.env.REACT_APP_API_URL}/api/transaction/${id}/handle-request`,
    headers: {
        Authorization: `Bearer ${auth.token}}`
    },
    params :  {responseType: 'APPROVE' }
    
}).then(res => {
    // NotificationManager.success("Recognition was submitted!", "Success!")
    fetchData()
        })
    .catch((error) => {
        // NotificationManager.error("Error sending the transaction!", "Error")
        console.log(error)
        });


}
const handleDenyBtn = async (id) => {

  await axios({
    method: 'PUT',
    url: `${process.env.REACT_APP_API_URL}/api/transaction/${id}/handle-request`,
    headers: {
        Authorization: `Bearer ${auth.token}}`
    },
    params :  {responseType: 'DENY' }
}).then(res => {
    console.log(res.data)
    // NotificationManager.success("Recognition was submitted!", "Success!")
    fetchData()
        })
    .catch((error) => {
        // NotificationManager.error("Error sending the transaction!", "Error")
        console.log(error)
        });
}


  return (
    <div className='text-white h-[80vh] mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <div className='flex flex-col items-center justify-center h-auto '>
            <h1 className='text-[50px]   text-[#8D8D8D]'>Manager Approvals</h1>
        </div>

        <div className='relative overflow-x-auto shadow-md sm:rounded-lg'>
            <h1 className='text-lg p-5 mt-10'>TRANSACTIONS TO BE SIGNED</h1>
            <table className='w-full text-sm text-left text-gray-500 dark:text-gray-400'>
                <thead className='text-xs text-gray-700 uppercase bg-elrond'>
                    <th className='px-6 py-3'>Sender</th>
                    <th className='px-6 py-3'>Receiver</th>
                    <th className='px-6 py-3'>Amount</th>
                    <th className='px-6 py-3'>Reason</th>
                    <th className='px-6 py-3'>Date</th>
                    <th className='px-6 py-3'>Description</th> 
                    <th className='px-6 py-3'>Action</th> 
                </thead>
                
                <tbody>
                {
                     transactions.map((data,idx)=>(
                        <tr id={idx}> 
                            <td className='px-6 py-4'>{data.sender?.firstName + " " + data.sender?.lastName}</td>
                            <td className='px-6 py-4'>{data.receiver?.firstName + " " + data.receiver?.lastName}</td>
                            <td className='px-6 py-4'>{String(data.amount).substring(1)}</td>
                            <td className='px-6 py-4'>{data.reason}</td>
                            <td className='px-6 py-4'>{data.transactionDate}</td>
                            <td className='px-6 py-4'>{data.description}</td>
                            <td className='px-6 py-4 flex'>
                              <button id="accept" className='mx-2 text-elrond text-2xl' onClick={() => handleAcceptBtn(data.id)}><BiCheckCircle/></button>
                              <button id="deny"  className='mx-2 text-white text-2xl' onClick={() =>handleDenyBtn(data.id)}><BiXCircle/></button>
                            </td>

                        </tr>
                    ))
                   
                }
                </tbody>

            </table>
        </div>


    </div>
  )
}

export default Approve