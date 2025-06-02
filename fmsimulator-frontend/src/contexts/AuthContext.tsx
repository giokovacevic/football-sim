import { createContext, useContext, useEffect, useState, type ReactNode } from "react";
import { getToken, getUser, isAuthenticated, unauthenticate } from "../services/AuthService";
import type { User } from "../types/models/user/User";

type AuthContext = {
    token: string | null,
    user: User | null,
    handleLogin: (token:string, user:User) => void,
    handleLogout: () => void;
};

export const AuthContext = createContext<AuthContext | undefined>(undefined);

export const AuthProvider = ({children}:{children: ReactNode}) => {
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<User | null>(null);

    useEffect(() => {
        if(isAuthenticated()) {
            setToken(getToken());
            setUser(getUser());
        }
    }, []);

    const handleLogin = (token: string, user: User) => {
        setToken(token);
        setUser(user);
    }

    const handleLogout = () => {
        setToken(null);
        setUser(null);
        unauthenticate();
    }

    return (
        <AuthContext.Provider value={{token, user, handleLogin, handleLogout}}>
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