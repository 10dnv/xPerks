import React from 'react'
import useAuth from '../hooks/useAuth'
import Notification from '../components/Notification'

function Dashboard() {
  const {auth} = useAuth()

  return (
  <div className='text-white min-h-[80vh] h-auto mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <div className='flex flex-col items-center  '>
            <h1 className='text-[50px]  pb-10  text-white font-bold'>Dashboard</h1>
        </div>

        <div className='mx-5'>

          <h1 className='text-2xl pb-10'> Welcome back, {auth.firstName +" "+ auth.lastName}!</h1>

          <Notification title="New Recognition 🔥" type ="sent" sender="Alin Ludu" amount="50"/>
        </div>
  </div>
  )
}

export default Dashboard