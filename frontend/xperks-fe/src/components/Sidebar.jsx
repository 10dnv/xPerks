import React from 'react'

function Sidebar() {
  return (
    <div className='hidden md:flex text-white  h-full  w-[300px]'>
            <ul className='p-4'>
                <li className='p-4 uppercase border-b border-b-gray-900'>Profile</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Point History</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Point Balance</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Peer recognise</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Redeem points</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Upcoming events</li>
            </ul>
    </div>
  )
}

export default Sidebar