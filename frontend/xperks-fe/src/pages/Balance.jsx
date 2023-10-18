import React from 'react'
import {NotificationContainer, NotificationManager} from 'react-notifications';

function Balance() {
  return (
    <div className='text-white h-[80vh] mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <div className='flex flex-col items-center justify-center h-96 '>
            <h1 className='text-[50px]  p-20 text-[#8D8D8D]'>Point Balance</h1>
        </div>
    </div>
  )
}

export default Balance