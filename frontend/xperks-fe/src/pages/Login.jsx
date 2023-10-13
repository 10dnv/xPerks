import { useState} from 'react'
import Logo from '../components/Logo'
import {NotificationContainer, NotificationManager} from 'react-notifications';
import 'react-notifications/lib/notifications.css';
import useAuth from '../hooks/useAuth';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

// const instance = axios.create({
//     baseURL: 'http://192.168.0.174:8000'
// });

const LOGIN_URL = "api/auth/login"

const Login = () => {
    const { setAuth} = useAuth();
    
    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const navigate = useNavigate();

    const handleOnChangeUser = e => {
          setUser(e.target.value);
      };

    const handleOnChangePass = e => {
          setPwd(e.target.value);
      };

    const handleFormSubmit = async (e) => {
        e.preventDefault();


    const userData = {
        "userEmail": user,
        "password": pwd
    }

    try {
        const response = await axios.post(LOGIN_URL, userData);

        // console.log(JSON.stringify(response?.data));
        // console.log(JSON.stringify(response));

        setAuth({
            user:user,
            firstName:response?.data.firstName,
            lastName:response?.data.lastName,
            token:response?.data?.token,
            isAuthenticated:true,
            id:response?.data.id
        });

        navigate("/");
    } catch (err) {
        if (!err?.response && err?.response != null) {
            setErrMsg('No Server Response');
        } else if (err.response?.status === 400) {
            setErrMsg('Missing Username or Password');
        } else if (err.response?.status === 401) {
            setErrMsg('Unauthorized');
        } else {
            setErrMsg('Login Failed');
        }

        if (errMsg != ''){
            NotificationManager.error(errMsg, "Login")
        }
    }
}

  return (
    <div className='flex flex-col items-center justify-center h-screen gap-[70px]'>
        <Logo />
        <NotificationContainer/>
        <div className=' text-white  rounded-md   bg-gradient-to-b from-gray-900 w-[350px] shadow-elrond-900 shadow-2xl'>
            <form onSubmit={handleFormSubmit}>
                <div className='flex p-4'>
                    <h3 className='font-bold text-elrond'>USERNAME</h3>
                    <input className='mx-4 w-[100%] text-black px-2' autocomplete='on' value={user}
                     onChange={handleOnChangeUser}/>
                </div>
                <div className='flex p-4'>
                    <h3 className='font-bold text-elrond'>PASSWORD</h3>
                    <input className='mx-4 w-[100%] text-black px-2' type="password" value={pwd} 
                    onChange={handleOnChangePass} />
                </div>

                <div className='flex p-4 justify-center'>
                    <button className='text-black bg-white rounded-lg px-5 py-1 font-bold transition ease-in-out delay-100 hover:-translate-y-1 hover:scale-105 hover:bg-elrond duration-200'>Login</button>
                </div>
            </form>
        </div>

    </div>
  )
}

export default Login