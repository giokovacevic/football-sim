import Navbar from '../../components/navbar/Navbar';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';

const Clubs = () => {
    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                Clubs
            </div>
        </div>
    );
}
export default Clubs;