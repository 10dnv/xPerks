import React from 'react'
import useAuth from '../hooks/useAuth'
import Notification from '../components/Notification'
import axios from 'axios'
import { useEffect, useState} from 'react'

function Dashboard() {
  const {auth} = useAuth()
  const [sentNotif, setSentNotif] = useState([])
  const [recvNotif, setRecvNotif] = useState([])

  const fetchData = async () =>{
    setSentNotif([])
    setRecvNotif([])
    await axios.get(`${process.env.REACT_APP_API_URL}/api/notification` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
    .then(res => {
        res.data?.map((data,idx) =>(
          // console.log(data)
            (data.type == "SENT" ?
                setSentNotif(sentNotif => [...sentNotif, data]):
                setRecvNotif(recvNotif => [...recvNotif, data]))
        ))
        console.log(sentNotif)
        console.log(recvNotif)
    })
    .catch((error) => {
    console.log(error)
    });
  }

 
        
useEffect(() => {
   fetchData()
}, [])


const handleReadNotif = async (id) => {

  await axios({
    method: 'PUT',
    url: `${process.env.REACT_APP_API_URL}/api/notification/read/${id}`,
    headers: {
        Authorization: `Bearer ${auth.token}}`
    },
    // params :  {responseType: 'DENY' }
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
  <div className='text-white min-h-[80vh] h-auto mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <div className='flex flex-col items-center  '>
            <h1 className='text-[50px]  pb-20  text-white font-bold'>Dashboard</h1>
            <h1 className='text-5xl pb-10 font-bold'> Welcome back, <span className='text-elrond' >{auth.firstName +" "+ auth.lastName}</span>!</h1>
         

            {
              sentNotif.map((data,idx)=>(
                data.unread?
                <Notification  key={idx} title="Transaction 🔥" type ="SENT" sender={data.transactionModel?.receiver.firstName + " " + data.transactionModel?.receiver.lastName} amount={String(data.transactionModel?.amount).substring(1)} status={data.transactionModel?.status} handle={handleReadNotif} id={data.id}/>
                :console.log("")
            ))
            }

            {
              recvNotif.map((data,idx)=>(
                data.unread?
                <Notification  key={idx} title="New Recognition 🔥" type ="RECEIVED" sender={data.transactionModel?.sender.firstName + " " + data.transactionModel?.sender.lastName} amount={String(data.transactionModel?.amount).substring(1)} status={data.transactionModel?.status} handle={handleReadNotif} id={data.id}/>
                :console.log("")
            ))
            }

          {/* <button onClick={fetchData}>FETCH</button> */}
        </div>

  </div>
  )
}

export default Dashboard