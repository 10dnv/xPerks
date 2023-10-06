import React from 'react'

function Login() {
  return (
    <div className='flex flex-col items-center justify-center h-screen gap-[70px]'>
        <h1 className='text-[100px] font-bold text-elrond'>xPerks</h1>
        
        <div className=' text-white  rounded-md   bg-gradient-to-b from-gray-900 w-[350px] shadow-elrond-900 shadow-2xl'>
            <form>
                <div className='flex p-4'>
                    <h3 className='font-bold'>USERNAME</h3>
                    <input className='mx-4 w-[100%] text-black px-2' type="email" />
                </div>
                <div className='flex p-4'>
                    <h3 className='font-bold'>PASSWORD</h3>
                    <input className='mx-4 w-[100%] text-black px-2' type="password" />
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