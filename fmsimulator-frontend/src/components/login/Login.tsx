import { useRef, useState } from 'react';
import styles from './Login.module.css';
import type { LoginResponse } from '../../types/responses/Responses';
import { login } from '../../services/LoginService';
import { authenticate, getToken, isAuthenticated } from '../../services/AuthService';
import { useAuth } from '../../contexts/AuthContext';
import { useLocation, useNavigate } from 'react-router-dom';

const Login = () => {
    const {handleLogin} = useAuth();
    const usernameRef = useRef<HTMLInputElement | null>(null);
    const passwordRef = useRef<HTMLInputElement | null>(null);
    const [feedback, setFeedback] = useState<string>(isAuthenticated() ? "Logged in" : "Logged out");
    const navigate = useNavigate();
    const location = useLocation();
    const previousPathname = (location.state as {previousLocation: Location})?.previousLocation?.pathname || "/";

    const handleSubmitClicked = async (e:React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        
        const username = usernameRef.current?.value || "";
        const password = passwordRef.current?.value || "";

        try {
            const data:LoginResponse = await login(username, password);
            if(data.token && authenticate(data.token)){
                handleLogin(data.token);
                setFeedback(data.message);
                navigate(previousPathname, {replace: true});
            }else{
                setFeedback(data.message);
            }
        
        } catch (error) {
            setFeedback('Invalid Credentials.');
        }
    }


    return (
        <div className={styles.root}>
            <form className={styles.form} onSubmit={handleSubmitClicked}>
                <input className={styles.input} type='text' placeholder='Username' ref={usernameRef}></input>
                <input className={styles.input} type='password' placeholder='Password' ref={passwordRef}></input>
                <button className={styles.button} type='submit'>Login</button>
                <label className={styles.information}>{feedback}</label>
            </form> 
        </div>
    );
}
export default Login;