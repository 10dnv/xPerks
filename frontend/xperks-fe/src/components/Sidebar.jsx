import {useState, useEffect} from 'react'
import { Link } from "react-router-dom";
import useAuth from '../hooks/useAuth';
import axios from 'axios';
import ApprovalNotification from './ApprovalNotification';

function Sidebar() {
    const [isSuperior, setIsSuperior] = useState(false)
    const {auth} = useAuth();

    // Check if logged user is superior to render extra page
    useEffect(() => {
        axios.get("api/user/superior" , { headers: {"Authorization" : `Bearer ${auth.token}`} })
        .then(res => {
            setIsSuperior(res.data)
        })
        .catch((error) => {
        console.log("An error occured while checking if you are a superior")
        console.log(error)
        });
    }, [])
  return (
    <div className='hidden md:flex text-white  h-full  w-[300px]'>
            <ul className='p-4'>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/profile">Profile</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/history">Point History</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/balance">Point Balance</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/recognize">Peer recognize</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/redeem">Redeem points</Link></li>
                <li className='p-4 uppercase border-b border-b-gray-900'><Link to="/events">Upcoming events</Link></li>
                <li className={isSuperior?'p-4 uppercase border-b border-b-gray-900 flex align-middle': 'hidden'}>
                    <Link to="/approve">
                      Approvals
                      <ApprovalNotification/>
                    </Link>
                </li>
            </ul>
    </div>
  )
}

export default Sidebar