import Navbar from '../../components/navbar/Navbar';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';

const Leagues = () => {
    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                <div style={{color: 'white'}}>Leagues, not implemented</div>
            </div>
        </div>
    );
}
export default Leagues;