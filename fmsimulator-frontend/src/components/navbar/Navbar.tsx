import { Link, useLocation } from 'react-router-dom';
import styles from './Navbar.module.css';

type NavbarProps = {
    items: {name: string, path: string}[],
};

const Navbar = ({items}: NavbarProps) => {
    const location = useLocation();
    const currentPath = location.pathname;
    
    return (
        <nav className={styles.root}>
            <div className={styles.items_section}>
                {items.map(item => (
                    <Link key={item.path} className={item.path === currentPath ? styles.item_active : styles.item} to={item.path}>{item.name}</Link>
                ))}
            </div>
        </nav>
    );
}
export default Navbar;