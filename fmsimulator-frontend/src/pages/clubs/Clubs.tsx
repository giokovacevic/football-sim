import { useEffect, useState } from 'react';
import Navbar from '../../components/navbar/Navbar';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';
import styles from './Clubs.module.css';
import { getAllClubs } from '../../services/ClubService';
import type { IClub } from '../../types/models/team/Club';
import { Link } from 'react-router-dom';

const Clubs = () => {
    const [clubs, setClubs] = useState<IClub[]>([]);

    useEffect(() => {
        loadClubs();
    }, []);

    const loadClubs = async () => {
        try {
            const data = await getAllClubs();
            setClubs(data);
        } catch (error) {
            setClubs([]);
        }
    }

    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                <div className={styles.club_list}>
                    {clubs.map((club) => (
                        <Link className={styles.test_link} to={`/clubs/${club.id}`} key={club.id}>
                            <img src={`./club_logo_icons/${club.id}.png`}></img>{club.name}
                        </Link>
                    ))}
                </div>
            </div>
        </div>
    );
}
export default Clubs;