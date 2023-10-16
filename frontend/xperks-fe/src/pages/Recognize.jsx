import React from 'react'
import {NotificationContainer, NotificationManager} from 'react-notifications';
import useAuth from '../hooks/useAuth';
import axios from "axios";
import SearchUser from '../components/SearchUser';
import Select from "react-select";
import { useState } from 'react';

function Recognize() {

    const {auth} = useAuth()
    const [inputUser, setInputUser] = useState("");
    const [transType, setTransType] = useState("")
    const [amount, setAmount] = useState("")

    

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        
        const userData = {
            "receiverId": inputUser,
            "type": transType,
            "amount": amount,
            "description": ""
        }

        await axios({
            method: 'POST',
            url: "/api/transaction",
            headers: {
                Authorization: `Bearer ${auth.token}}`
            },
            data : userData
        }).then(res => {
            console.log(res.data)
            NotificationManager.success("Successfully updated mvsx address!", "Success!")
                })
            .catch((error) => {
                NotificationManager.error("Error sending the transaction!", "Error")
                console.log(error)
                });
        
    }

    const options_transaction_type = [
        { value: 'P2P', label: 'P2P' },
        { value: 'BIRTHDAY', label: 'Birthday' },
        { value: 'LOYALTY', label: 'Loyalty' },
        { value: 'OTHER', label: 'Other' }
      ]

      const options_point_amount = [
        { value: '_50', label: '50' },
        { value: '_100', label: '100' },
        { value: '_250', label: '250' },
        { value: '_500', label: '500' }
      ]

      const handleTransTypeChange = (e) => {
        setTransType(e.value);
        console.log(e.value);
      };

      const handleAmountChange = (e) => {
        setAmount(e.value);
        console.log(e.value);
      };


  return (
    <div className='text-white h-[80vh] mx-10 w-2/3  rounded-md  shadow-elrond-900 shadow-2xl'>
        <NotificationContainer/>
        <div className='flex flex-col items-center justify-center h-96 '>
            <h1 className='text-[50px]  p-20 text-[#8D8D8D]'>Recognize</h1>

            <form onSubmit={handleFormSubmit}>
                <dl className="grid grid-cols-[repeat(2,auto)] gap-x-5 w-max  gap-y-3 text-sm md:text-xl">
                        <dt>Who do you want to recognize?</dt>
                        <dd className="text-left"> <SearchUser handle={setInputUser}/> </dd>

                        <dt>Type of recognition:</dt>
                        <dd className="text-left">
                            <Select
                            options={options_transaction_type}
                            className='w-2/4 text-black'
                            onChange={handleTransTypeChange}
                            />
                        </dd>

                        <dt>Amount:</dt>
                        <dd className="text-left">
                            <Select
                                options={options_point_amount}
                                className='w-2/4 text-black'
                                onChange={handleAmountChange}
                                />
                        </dd>

                        <dt>Description:</dt>
                        <dd className="text-left max-w-sm">I'd like to express my gratitude for all the good work you've done!</dd>
                    
                        <dt></dt>
                        <dd>
                            <button className='mt-2 bg-white text-black px-2 w-[100px]'>Send</button>
                        </dd>
                </dl>
            </form>
        </div>
    </div>
  )
}

export default Recognize