import { createContext, useContext, useEffect, useState, type ReactNode } from "react";
import { getToken, isAuthenticated, unauthenticate } from "../services/AuthService";

interface AuthContext{
    token: string | null,
    username: string | null,
    handleLogin: (token:string) => void,
    handleLogout: () => void;
}

export const AuthContext = createContext<AuthContext | undefined>(undefined);

export const AuthProvider = ({children}:{children: ReactNode}) => {
    const [token, setToken] = useState<string | null>(null);
    const [username, setUsername] = useState<string | null>(null);

    useEffect(() => {
        if(isAuthenticated()) {
            setToken(getToken());
            setUsername("MrTousty");
        }
    }, []);

    const handleLogin = (token: string) => {
        setToken(token);
        setUsername("MrTousty");
    }

    const handleLogout = () => {
        unauthenticate();
        setToken(null);
        setUsername(null);
    }

    return (
        <AuthContext.Provider value={{token, username, handleLogin, handleLogout}}>
            {children}
        </AuthContext.Provider>
    );
}

export const useAuth = () => {
    const context = useContext(AuthContext);
    if(!context) {
        throw new Error('useAuth must be used with an AuthContext');
    }
    return context;
}