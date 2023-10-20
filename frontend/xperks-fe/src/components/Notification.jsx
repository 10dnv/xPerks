import React from 'react'

function Notification(props) {
  return (
    <div className="w-full md:w-1/2 lg:w-1/3 pb-7"  >
    <div className="flex flex-col space-y-4">
        <div className="flex justify-between py-2 px-4 bg-elrond rounded-lg">
            <div className="flex items-center space-x-4">
                <div className="flex flex-col space-y-1 text-gray-800">
                    <span className="font-bold">{props.title}</span>

                    {props.type == "RECEIVED"?<span className="text-sm">You've been recognized by <span className="font-bold">{props.sender}</span> with <span className="font-bold">{props.amount}</span> points!</span>:
                    <span className="text-sm">Your request to send <span className="font-bold">{props.amount}</span> pts. to <span className="font-bold">{props.sender}</span> was <span className="font-bold">{props.status}</span>!</span>}
                    
                </div>
            </div>
            <div className="flex-none   text-stone-600 text-xs md:text-sm" onClick={()=>props.handle(props.id)}>x</div>
        </div>
        </div>
        </div>
  )
}

export default Notification