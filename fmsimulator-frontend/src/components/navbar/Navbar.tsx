import { Link } from 'react-router-dom';
import styles from './Navbar.module.css';

const Navbar = () => {
    
    return (
        <nav className={styles.root}>
            <div><Link to="/" className={styles.link}>Home</Link></div>
            <div><Link to="/lineup" className={styles.link}>Lineup</Link></div>
        </nav>
    );
}
export default Navbar;