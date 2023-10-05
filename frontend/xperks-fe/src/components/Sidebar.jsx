import React from 'react'
import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div className='hidden md:flex text-white  h-full  w-[300px]'>
            <ul className='p-4'>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/profile">Profile</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/history">Point History</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/balance">Point Balance</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/recognise">Peer recognise</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/redeem">Redeem points</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/events">Upcoming events</Link></li>
            </ul>
    </div>
  )
}

export default Sidebar