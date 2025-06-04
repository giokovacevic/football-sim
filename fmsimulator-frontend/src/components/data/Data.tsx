import {useEffect, useState} from 'react';
import type { ILeague } from '../../types/models/competition/League';
import styles from './Data.module.css';
import DropdownMenu from '../dropdown_menu/DropdownMenu';
import LeagueTable from '../league_table/LeagueTable';
import Pitch from '../pitch_old/Pitch';
import type { ITeam } from '../../types/models/team/Team';
import type { IPlayer } from '../../types/models/player/Player';
import PlayerList from '../old_player_list/PlayerList';
import { getAllLeagues } from '../../services/LeagueService';
import { extractFullSquad } from '../../utils/TeamUtils';
import Navbar from '../navbar/Navbar';

const Data = () => {
    const [leagues, setLeagues] = useState<ILeague[]>([]);
    const [currentLeague, setCurrentLeague] = useState<ILeague | undefined>(undefined);
    const [currentTeam, setCurrentTeam] = useState<ITeam | undefined>(undefined);
    const [players, setPlayers] = useState<IPlayer[]>([]);

    useEffect(() => {
        loadAllLeagues();
    }, []);

    const loadAllLeagues = async () => {
        const data = await getAllLeagues();
        setLeagues(data);
    }

    const handleDropdownMenuOnSelect = (item: ILeague) => {
        setCurrentLeague(item);
    }

    const handleLeagueTableOnClick = (team: ITeam) => {
        setCurrentTeam(team);
        if(team) setPlayers(extractFullSquad(team));
    }

    const links = [{name: 'Home', path: '/'}, {name: 'Leagues', path: '/leagues'}, {name: 'Clubs', path: '/clubs'}, {name: 'Players', path: '/players'}];

    return (
        <div className={styles.root}>
            <Navbar items={links}></Navbar>
        </div>
        
    );
}
export default Data;
/*

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
                    <PlayerList players={players} currentYear={new Date().getFullYear()} variant='squad' includeHeader={true}></PlayerList>
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

*/ 