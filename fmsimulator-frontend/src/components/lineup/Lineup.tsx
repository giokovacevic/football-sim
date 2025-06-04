import { useState } from 'react';
import type { ITeam } from '../../types/models/team/Team';
import { getRatingColor } from '../../utils/ColorUtils';
import styles from './Lineup.module.css';
import LineupMember from './LineupMember';

type LineupProps = {
    team: ITeam;
};

const Lineup = ({team}: LineupProps) => {
    const [currentKits, setCurrentKits] = useState<string>(team.preferredJersey);

    const switchKits = () => {
        setCurrentKits(currentKits !== 'dark' ? 'dark' : 'light');
    }

    return (
        <div className={styles.root}>
            <div className={styles.header}>
                <div className={styles.header_formation}>
                    <div className={styles.header_formation_content}>{team.roster.lineup.formation.id}</div>
                </div>
                <div className={styles.header_ratings}>
                    <div className={styles.header_ratings_defense}>
                        <div className={styles.header_ratings_content_icon}>
                            <img src='/assets/defense_icon.png'></img>
                        </div>
                        <div className={styles.header_ratings_content_text} style={{color: `${getRatingColor(team.roster.lineup.defense)}`}}>{team.roster.lineup.defense}</div>
                    </div>
                    <div className={styles.header_ratings_offense}>
                        <div className={styles.header_ratings_content_icon}>
                            <img src='/assets/offense_icon.png'></img>
                        </div>
                        <div className={styles.header_ratings_content_text} style={{color: `${getRatingColor(team.roster.lineup.offense)}`}}>{team.roster.lineup.offense}</div>
                    </div>
                </div>
                <div className={styles.header_kits}>
                    <button className={styles.header_kits_content} onClick={switchKits}>Switch kits</button>
                </div>
            </div>
            <div className={styles.pitch}>
                {team.roster.lineup.starters.map((starter) => (
                    <LineupMember key={starter.requiredRole.stringValue} starter={starter} teamId={team.id} kit={currentKits}></LineupMember>
                ))}
            </div>
        </div>
    );
}
export default Lineup;