import { Link, useLocation, useNavigate } from 'react-router-dom';
import styles from './Navbar.module.css';
import { useAuth } from '../../contexts/AuthContext';
import { useState } from 'react';
import { isAuthenticated, unauthenticate } from '../../services/AuthService';
import Logo from '../logo/Logo';

type NavbarProps = {
    items: {name: string, path: string}[],
};

const Navbar = ({items}: NavbarProps) => {
    const {token, user, handleLogout} = useAuth();
    const location = useLocation();
    const currentPath = location.pathname;
    const navigate = useNavigate();

    const handleLoginButtonClicked = () => {
        if(isAuthenticated()) {
            handleLogout();
            //navigate('/');
        }else{
            navigate('/');
        }
    }
    
    return (
        <nav className={styles.root}>
            <div className={styles.items_section}>
                {items.map(item => (
                    <Link key={item.path} className={item.path === currentPath ? styles.item_active : styles.item} to={item.path}>{item.name}</Link>
                ))}
            </div>
            <div className={styles.user_section}>
                {(token && user) ? 
                <div className={styles.logo}>
                    <Logo url={`/assets/${user.role.url}`} text={user.role.name}></Logo>
                </div>
                : null}
                <span className={styles.username}>{token ? user?.username : ""}</span>
                <button className={styles.login_button} onClick={handleLoginButtonClicked}>{token ? "Logout" : "Login"}</button>
            </div>
        </nav>
    );
}
export default Navbar;