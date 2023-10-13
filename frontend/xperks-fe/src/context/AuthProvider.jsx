import { createContext, useState } from "react";

const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
    const [auth, setAuth] = useState({
        user:'',
        firstName:'',
        token:'',
        id:'',
        isAuthenticated:false
    });

    const logout =() =>{
        console.log("Logout triggered")
        setAuth({isAuthenticated:false})
    }

    return (
        <AuthContext.Provider value={{ auth, setAuth, logout }}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthContext;