import Login from '../../components/login/Login';
import Navbar from '../../components/navbar/Navbar';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';
import styles from './Home.module.css';

const Home = () => {
    return (
       <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                <div className={styles.login_section}><Login></Login></div>
            </div>
        </div>
    );
}
export default Home;