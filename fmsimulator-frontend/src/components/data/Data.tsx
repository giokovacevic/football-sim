import {useEffect, useState} from 'react';
import type { League } from '../../model/competition/League';
import styles from './Data.module.css';
import DropdownMenu from '../dropdown_menu/DropdownMenu';
import LeagueTable from '../league_table/LeagueTable';
import Roster from '../roster/Roster';
import Pitch from '../pitch/Pitch';
import type { Team } from '../../model/team/Team';

const Data = () => {
    const [leagues, setLeagues] = useState<League[]>([]);
    const [currentLeague, setCurrentLeague] = useState<League | undefined>(undefined);
    const [currentTeam, setCurrentTeam] = useState<Team | undefined>(undefined);
    const tempTeam = 3;

    useEffect(() => {
        fetchAllLeagues();
    }, []);

    const fetchAllLeagues = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/football/leagues/all');
            const data = await response.json();
            setLeagues(data);
            console.log(data);
        } catch (error) {
            console.log('Error in fetchLeagues: ' + error);
        }
    }

    const handleDropdownMenuOnSelect = (item:League) => {
        setCurrentLeague(item);
    }

    const handleLeagueTableOnClick = (team:Team) => {
        setCurrentTeam(team);
    }

    return (
        <div className={styles.root}>
            <div className={styles.column_section_left}>
                <div className={styles.row_section_leagues}><DropdownMenu items={leagues} onSelect={handleDropdownMenuOnSelect} valueProperty='name' imageProperty={undefined} imagePath='' placeholder='Select a League'></DropdownMenu></div>
                <div className={styles.row_section_leaguetable}>
                    <div className={styles.leaguetable_wrapper}>
                    {currentLeague 
                    ? 
                    <LeagueTable key={currentLeague.id} league={currentLeague} onCompetitorClick={handleLeagueTableOnClick}></LeagueTable>
                    :
                    null
                    }
                    </div>
                </div>
            </div>
            <div className={styles.column_section_center}>
                <div className={styles.row_section_roster}>
                    <div className={styles.roster_wrapper}>
                    {currentTeam 
                    ? 
                    <Roster key={currentTeam.id} team={currentTeam}></Roster>
                    :
                    null
                    }
                    </div>
                </div>
            </div>
            <div className={styles.column_section_right}>
                <div className={styles.row_section_pitch}>
                    {currentTeam
                    ? 
                    <Pitch key={currentTeam.id} team={currentTeam} kits={currentTeam.preferredJersey}></Pitch>
                    :
                    null
                    }
                </div>
                <div></div>
            </div>
        </div>
    );
}
export default Data;