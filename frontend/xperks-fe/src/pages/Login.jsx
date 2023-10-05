import React from 'react'

function Login() {
  return (
    <div className='text-white mx-auto  rounded-md items-center justify-center bg-gradient-to-b from-gray-900'>
        <h1 className=' font-bold text-3xl text-center p-8'>Login</h1>
        <form>
            <div className='flex  p-4 '>
                <h3>USERNAME</h3>
                <input className='mx-4 w-[100%] text-black' type="email" />
            </div>
            <div className='flex p-4'>
                <h3>PASSWORD</h3>
                <input className='mx-4 w-[100%] text-black' type="password" />
            </div>

            <div className='flex p-4 justify-center'>
                <button className='text-black bg-white rounded-lg px-5 py-1'>Login</button>
            </div>
        </form>
    </div>
  )
}

export default Login