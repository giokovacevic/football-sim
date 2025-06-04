import { useEffect, useState } from 'react';
import Navbar from '../../components/navbar/Navbar';
import { mainNavbarItems } from '../../constants/NavbarItems';
import pageStyles from './../Page.module.css';
import { getClubById } from '../../services/ClubService';
import type { IClub } from '../../types/models/team/Club';
import styles from './Club.module.css';
import Pitch from '../../components/pitch_old/Pitch';
import PlayerList from '../../components/player_list/PlayerList';
import { extractFullSquad } from '../../utils/TeamUtils';
import Lineup from '../../components/lineup/Lineup';

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

    const handleJerseyNotFound = (e: React.SyntheticEvent<HTMLImageElement, Event>, club: IClub, kits: string) => {
        const element = e.currentTarget;
        element.onerror = null;
        element.src = `/team_assets/default/default_${kits}.png`;
    }

    return (
        <div className={pageStyles.root}>
            <div className={pageStyles.navbar}><Navbar items={mainNavbarItems}></Navbar></div>
            <div className={pageStyles.content}>
                {club ? (
                    <>
                        <section className={styles.section_left}>
                            <div className={styles.kits}>
                                <img src={`/team_assets/${club.id}/${club.id}_home.png`} onError={(e) => handleJerseyNotFound(e, club, 'home')}></img>
                                <img src={`/team_assets/${club.id}/${club.id}_away.png`} onError={(e) => handleJerseyNotFound(e, club, 'away')}></img>
                            </div>
                            <div className={styles.lineup}><Lineup team={club}></Lineup></div>
                        </section>
                        <section className={styles.section_right}>
                            <div className={styles.squad}><PlayerList players={extractFullSquad(club)} currentYear={2025}></PlayerList></div>
                        </section>
                    </>
                ) : (<div style={{fontSize: '20px', color: 'white'}}>Club loading......</div>)}
            </div>
        </div>
    );
}
export default Club;