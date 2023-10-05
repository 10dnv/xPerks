import React from 'react'
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { Outlet, Link } from "react-router-dom";

function Layout() {
  return (
    <div>
        <Navbar />
        <div className="flex">
            <Sidebar />
            <Outlet />
        </div> 
        
    </div>

  )
}

export default Layout