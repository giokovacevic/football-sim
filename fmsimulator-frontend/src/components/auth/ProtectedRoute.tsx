import { useEffect, type JSX } from "react";
import { isAuthenticated } from "../../services/AuthService";
import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext";

type ProtectedRouteProps = {
    children: JSX.Element;
};

const ProtectedRoute = ({children}:ProtectedRouteProps) => {
    const {token, handleLogout} = useAuth();
    const location = useLocation();

    const isAuth:boolean = isAuthenticated();

    useEffect(() => {
        if(!isAuthenticated() && token) {
            handleLogout();
        }
    }, []);

    if(!isAuthenticated() || !token) {
        return (<Navigate to="/" replace state={{previousLocation: location}}/>);

    }

    return children;
}
export default ProtectedRoute;