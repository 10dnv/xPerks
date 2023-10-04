import React, { useState } from 'react'
import {AiOutlineMenu, AiOutlineClose} from 'react-icons/ai'
import {LuLogOut} from 'react-icons/lu'
import {BsCurrencyExchange} from 'react-icons/bs'
import Avatar from 'react-avatar';

const Navbar = () => {

    const [nav, setNav] = useState(false)

    const handleNav = () => {
        setNav(!nav);
    };

  return (
    <div className='text-white flex justify-between items-center h-[96px] max-w-[2300px] px-4' >
        <h1 className='text-5xl font-bold text-[#24f7dd]'>xPerks</h1>
        <ul className='hidden md:flex items-center '>
            
            <li className='p-4 mr-20 flex'>
                <BsCurrencyExchange size={20}/>
                <span className='pl-3'>Rate 1p = 0.025 EGLD</span>
            </li>
            <li className='p-4'>
                <span className='mx-2'>
                    <Avatar size={45} name="Username" round={true} />
                </span>
                <span className='ml-3'>username</span>
            </li>
            <li className='p4'>
                <button className='bg-white text-black rounded-md p-1.5 ml-3 hover:bg-gray-300 flex items-center px-4'>
                    <LuLogOut/>
                    <span className='ml-2'>Logout</span>
                </button>
            </li>
        </ul>

        <div onClick={handleNav} className='block md:hidden'>
            {!nav ? <AiOutlineClose size={30}/> : <AiOutlineMenu size={30}/>}
        </div>

        <div className={!nav ? 'fixed left-0 top-0 w-[300px] bg-[#000300] border-r border-r-gray-900 h-full ease-in-out duration-300' : 'fixed left-[-100%]'}>
            <h1 className='text-5xl font-bold text-[#24f7dd] m-4'>xPerks</h1>
            <ul className='p-4'>
                <li className='p-4 ml-7 flex'>
                    <BsCurrencyExchange size={20}/>
                    <span className='pl-3'>Rate: 1p = 0.025 EGLD</span>
                </li>
                <li className='p-4 ' >
                    <span className='pr-5 ml-8'>
                        <Avatar size={45} name="Username" round={true} />
                    </span>
                    <span >username</span>
                </li>
                <li className='p-4 border-b border-b-gray-500 pl-20'>
                    <button className='bg-white text-black rounded-md p-1.5 ml-3 hover:bg-gray-300 flex items-center px-4'>
                        <LuLogOut/>
                        <span className='ml-2'>Logout</span>
                    </button>
                </li>
                <li className='p-4 mt-5 uppercase border-b border-b-gray-900'>Profile</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Point History</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Point Balance</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Peer recognise</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Redeem points</li>
                <li className='p-4 uppercase border-b border-b-gray-900'>Upcoming events</li>
            </ul>
        </div>
    </div>
  )
}

export default Navbar