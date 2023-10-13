import { useEffect, useState } from 'react';
import React from 'react'
import axios from 'axios';

function Price() {

const [price, setPrice] = useState(0)

useEffect(() => {
    axios.get("https://api.multiversx.com/mex-pairs?from=1&size=1")
    .then(res => {
    // console.log(res.data[0]?.basePrice)
    setPrice(res.data[0]?.basePrice)
    })
    .catch((error) => {
    console.log(error)
    });
}, [])



  return (
    <>{parseFloat(1/price).toFixed(4)}</>
  )
}

export default Price