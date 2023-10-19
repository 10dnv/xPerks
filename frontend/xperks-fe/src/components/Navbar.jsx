import React, { useEffect, useState } from 'react'
import {AiOutlineMenu, AiOutlineClose} from 'react-icons/ai'
import {LuLogOut} from 'react-icons/lu'
import {BsCurrencyExchange} from 'react-icons/bs'
import Avatar from 'react-avatar';
import { Link } from "react-router-dom";
import Logo from './Logo';
import useAuth from '../hooks/useAuth';
import axios from 'axios';
import Price from './Price';

const Navbar = () => {

    const [nav, setNav] = useState(true)
    const {auth, logout} = useAuth();
    const [isSuperior, setIsSuperior] = useState(false)

    const handleNav = () => {
        setNav(!nav);
    };

    const  handleLogout =() =>{
        logout()
    };

    // Check if logged user is superior to render extra page
    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/api/user/superior` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
        .then(res => {
            setIsSuperior(res.data)
        })
        .catch((error) => {
        console.log("An error occured while checking if you are a superior")
        console.log(error)
        });
    }, [])
    

  return (
    <div className='text-white flex justify-between items-center h-[96px] max-w-[2300px] px-4' >
        <Link to="/"><Logo size='small'/></Link>
        <ul className='hidden md:flex items-center '>
            
            <li className='p-4 mr-20 flex'>
                <BsCurrencyExchange size={20}/>
             
                <span className='pl-3'>Rate 1p = <Price/> EGLD</span>
            </li>
            <li className='p-4'>
                <span className='mx-2'>
                    <Avatar size={45} name={auth.firstName + " " + auth.lastName} round={true} />
                </span>
                <span className='ml-3'>{auth.firstName + " " + auth.lastName}</span>
            </li>
            <li className='p4'>
                <button className='bg-white text-black rounded-md p-1.5 ml-3 hover:bg-gray-300 flex items-center px-4' onClick={handleLogout}>
                    <LuLogOut/>
                    <span className='ml-2'>Logout</span>
                </button>
            </li>
        </ul>

        <div onClick={handleNav} className='block md:hidden'>
            {!nav ? <AiOutlineClose size={30}/> : <AiOutlineMenu size={30}/>}
        </div>

        <div className={!nav ? 'fixed left-0 top-0 w-[300px] bg-[#000300] border-r border-r-gray-900 h-full ease-in-out duration-300' : 'fixed left-[-100%]'}>
        <div className='m-4'><Link to="/"><Logo size='small'/></Link></div>
            <ul className='p-4'>
                <li className='p-4 ml-7 flex'>
                    <BsCurrencyExchange size={20}/>
                    <span className='pl-3'>Rate: 1p = <Price/> EGLD</span>
                </li>
                <li className='p-4 ' >
                    <span className='pr-5 ml-8'>
                        <Avatar size={45} name={auth.firstName + " " + auth.lastName}  round={true} />
                    </span>
                    <span >{auth.firstName + " " + auth.lastName} </span>
                </li>
                <li className='p-4 border-b border-b-gray-500 pl-20'>
                    <button className='bg-white text-black rounded-md p-1.5 ml-3 hover:bg-gray-300 flex items-center px-4' onClick={handleLogout}>
                        <LuLogOut/>
                        <span className='ml-2' >Logout</span>
                    </button>
                </li>
                <li className='p-4 mt-5 uppercase border-b border-b-gray-900'><Link to="/profile">Profile</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/history">Point History</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/recognize">Peer recognize</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/redeem">Redeem points</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/events">Upcoming events</Link></li>
                <li className={isSuperior?'p-4 uppercase border-b border-b-gray-900': 'hidden'}><Link to="/approve">Approvals</Link></li>
            </ul>
        </div>
    </div>
  )
}

export default Navbar