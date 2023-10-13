import { createContext, useState } from "react";

const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
    const [auth, setAuth] = useState({
        user:'',
        firstName:'',
        lastName:'',
        token:'',
        id:'',
        isAuthenticated:false
    });

    const logout =() =>{
        setAuth({isAuthenticated:false})
    }

    return (
        <AuthContext.Provider value={{ auth, setAuth, logout }}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthContext;