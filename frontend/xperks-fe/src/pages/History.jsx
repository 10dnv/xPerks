import axios from 'axios';
import {NotificationContainer, NotificationManager} from 'react-notifications';
import { useEffect, useState } from 'react';
import useAuth from '../hooks/useAuth';

function History() {

    const {auth} = useAuth();
    const [sentTrans, setSentTrans] = useState([])
    const [recvTrans, setRecvTrans] = useState([])

      const fetchData = async () =>{
        await axios.get(`${process.env.REACT_APP_API_URL}/api/transaction/history` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
        .then(res => {
            res.data?.map((data,idx) =>(
               data.transactionType === "SENT"?
                    setSentTrans(sentTrans => [...sentTrans, data]):
                    setRecvTrans(recvTrans => [...recvTrans, data])
            ))
        })
        .catch((error) => {
        console.log(error)
        });
      }

     
            
    useEffect(() => {
       fetchData()
    }, [])
  return (
    <div className='text-white h-auto mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <div className='flex flex-col items-center  '>
            <h1 className='text-[50px]   text-[#8D8D8D]'>Point History</h1>
        </div>

        <div className='relative overflow-x-auto shadow-md sm:rounded-lg'>
            <h1 className='text-lg p-5 mt-10'>SENT</h1>
            <table className='w-full text-sm text-left text-gray-500 dark:text-gray-400'>
                <thead className='text-xs text-gray-700 uppercase bg-elrond'>
                    <th className='px-6 py-3'>Receiver</th>
                    <th className='px-6 py-3'>Amount</th>
                    <th className='px-6 py-3'>Reason</th>
                    <th className='px-6 py-3'>Approver</th>
                    <th className='px-6 py-3'>Date</th>
                    <th className='px-6 py-3'>Description</th> 
                    <th className='px-6 py-3'>Status</th>
                </thead>
                
                <tbody>
                {
                     sentTrans.map((data,idx)=>(
                        <tr id={idx}> 
                            <td className='px-6 py-4'>{data.receiver?.firstName + " " + data.receiver?.lastName}</td>
                            <td className='px-6 py-4'>{String(data.amount).substring(1)}</td>
                            <td className='px-6 py-4'>{data.reason}</td>
                            <td className='px-6 py-4'>{data.approver?.firstName + " " + data.receiver?.lastName}</td>
                            <td className='px-6 py-4'>{data.transactionDate}</td>
                            <td className='px-6 py-4'>{data.description}</td>
                            <td className='px-6 py-4'>{data.status}</td>
                        </tr>
                    ))
                   
                }
                </tbody>

            </table>
        </div>

        <div className='relative overflow-x-auto shadow-md sm:rounded-lg'>
            <h1 className='text-lg p-5 mt-10'>RECEIVED</h1>
            <table className='w-full text-sm text-left text-gray-500 dark:text-gray-400'>
                <thead className='text-xs text-gray-700 uppercase bg-elrond'>
                    <th className='px-6 py-3'>Sender</th>
                    <th className='px-6 py-3'>Amount</th>
                    <th className='px-6 py-3'>Reason</th>
                    <th className='px-6 py-3'>Approver</th>
                    <th className='px-6 py-3'>Date</th>
                    <th className='px-6 py-3'>Description</th> 
                    <th className='px-6 py-3'>Status</th>
                </thead>
                
                <tbody>
                {
                     recvTrans.map((data,idx)=>(
                        <tr id={idx}> 
                            <td className='px-6 py-4'>{data.sender?.firstName + " " + data.sender?.lastName}</td>
                            <td className='px-6 py-4'>{String(data.amount).substring(1)}</td>
                            <td className='px-6 py-4'>{data.reason}</td>
                            <td className='px-6 py-4'>{data.approver?.firstName + " " + data.receiver?.lastName}</td>
                            <td className='px-6 py-4'>{data.transactionDate}</td>
                            <td className='px-6 py-4'>{data.description}</td>
                            <td className='px-6 py-4'>{data.status}</td>
                        </tr>
                    ))
                   
                }
                </tbody>

            </table>
        </div>
    </div>
  )
}

export default History