import { useEffect, useState } from 'react';
import Navbar from '../../components/navbar/Navbar';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';
import { getClubById } from '../../services/ClubService';
import type { Club as IClub} from '../../types/models/team/Club';

const Club = ({clubId}: {clubId: string}) => {
    const [club, setClub] = useState<IClub | null>(null);

    useEffect(() => {
        loadClub();
    }, []);

    const loadClub = async () => {
        try {
            const data = await getClubById(clubId);
            setClub(data);
        } catch (error) {
            setClub(null);
        }
    }

    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                <div style={{fontSize: '30px', color: 'white'}}>{club?.name}</div>
            </div>
        </div>
    );
}
export default Club;