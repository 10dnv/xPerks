import React, { useState, useEffect } from 'react'
import {NotificationContainer, NotificationManager} from 'react-notifications';
import useAuth from '../hooks/useAuth';
import axios from 'axios';

function Redeem() {
    const {auth} = useAuth()
    const [balance, setBalance] = useState(0)
    const [erdAddress, setErdAddress] = useState("")
    const [pointsToBeRedeemed, setPointsToBeRedeemed] = useState(0)
    const [egldToBeRedeemed, setEgldToBeRedeemed] = useState(0)
    const [egldPrice, setEgldPrice] = useState(0)

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_URL}/api/user/${auth.id}` , { headers: {"Authorization" : `Bearer ${auth.token}`} })
        .then(res => {
            // console.log(res.data?.balance)
            setBalance(res.data?.balance)
            setErdAddress(res.data?.erdAddress)
        })
        .catch((error) => {
        console.log(error)
        });
    }, [])

    useEffect(() => {
        axios.get("https://api.multiversx.com/mex-pairs?from=1&size=1")
        .then(res => {
        // console.log(res.data[0]?.basePrice)
        setEgldPrice(res.data[0]?.basePrice)
        })
        .catch((error) => {
        console.log(error)
        });
    }, [])

    function handleSliderVal(e){
        setPointsToBeRedeemed(parseInt(e.target.value))
        setEgldToBeRedeemed(Number(pointsToBeRedeemed * 1/egldPrice).toFixed(3))
    }

    const handleFormSubmit = async (e) => {
        e.preventDefault();

    // try {
    //     const response = await axios.post();
    //     console.log(response)

    // } catch (err) {
    //    console.log(err)
    // }
}

  return (
    <div className='text-white h-auto mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <div className='flex flex-col items-center justify-center h-auto flex-wrap '>
            <h1 className='text-[50px] pb-20 text-[#8D8D8D]'>Redeem</h1>
            <form onSubmit={handleFormSubmit}>
                <h2 className='text-3xl pb-5'>You currently own <span className='text-elrond'>{balance}</span> points.</h2>
                <h2 className='text-3xl pb-5'>Your MultiversX wallet: <span className='text-elrond text-xl'>{erdAddress}</span></h2>
                <div className='flex items-center text-3xl justify-between'>
                    <h2 className='text-3xl'>Amount to be withdrawn:</h2>
                    <p> Points:{pointsToBeRedeemed}</p>
                    <input type="range" id="points" name="volume" step="1" min="0" max={balance} onChange={handleSliderVal} className='w-auto mx-0 px-0 h-auto' value={pointsToBeRedeemed}/>
                    <p> Egld:{egldToBeRedeemed}</p>
                </div>

                <div className='flex justify-center'>
                    <button className='text-black bg-white rounded-lg px-5 py-1 my-16 font-bold transition ease-in-out delay-100 hover:-translate-y-1 hover:scale-105 hover:bg-elrond duration-200 '>Redeem</button>
                </div>
            </form>
        </div>

    </div>
  )
}

export default Redeem