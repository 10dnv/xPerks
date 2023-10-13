import React from 'react'

function Logo(props) {
    if (props.size === 'small')
  return (
        <div className='flex items-baseline'>
            <h1 className='text-[50px]  font-bold  rotate-90 text-elrond pb-4'>x</h1>
            <h1 className='text-[70px] font-bold text-while'>P</h1>
            <h1 className='text-[50px] font-bold -ml-2 text-white'>erks</h1>
        </div>
  )
  else
  return (
    <div className='flex items-baseline'>
        <h1 className='text-[80px]  font-bold  rotate-90 text-elrond pb-4'>x</h1>
        <h1 className='text-[100px] font-bold text-white'>P</h1>
        <h1 className='text-[80px] font-bold -ml-2 text-white'>erks</h1>
    </div>
)
}

export default Logo