import type { JSX } from "react";
import { isAuthenticated } from "../../services/AuthService";
import { Navigate, useLocation } from "react-router-dom";

type ProtectedRouteProps = {
    children: JSX.Element;
};

const ProtectedRoute = ({children}:ProtectedRouteProps) => {
    const location = useLocation();

    return (
        isAuthenticated() ? children : <Navigate to="/" replace state={{previousLocation: location}}/>
    );
}
export default ProtectedRoute;