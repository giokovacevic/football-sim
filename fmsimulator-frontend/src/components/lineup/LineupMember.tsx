import type { IStarter } from '../../types/models/team/lineup/Starter';
import { getPositionColor, getRatingColor } from '../../utils/ColorUtils';
import Flag from '../flag/Flag';
import styles from './Lineup.module.css';

type LineupMemberProps = {
    starter: IStarter,
    teamId: string,
    kit: string;
};

const LineupMember = ({starter, teamId, kit}: LineupMemberProps) => {
    
    return (
        <div className={styles.member} style={{left: `${starter.requiredRole.x}px`, top: `${starter.requiredRole.y}px`}}>
            <div className={styles.member_jersey} style={{opacity: starter.player ? '1.0' : '0.1', filter: 'drop-shadow(0 0 1px rgb(191, 191, 191, 1))'}}>
                <img src={`/team_assets/${teamId}/${teamId}_${starter.requiredRole.stringValue === 'GK' ? 'gk_' + kit : kit}.png`} onError={(e) => {
                    const target = e.currentTarget;
                    if (target.src.includes('/team_assets/default/')) return;
                    target.onerror = null;
                    target.src = `/team_assets/default/default_${starter.requiredRole.stringValue === 'GK' ? 'gk_' + ((kit !== 'light' && kit !== 'dark')? "light" : kit) : ((kit !== 'light' && kit !== 'dark')? "light" : kit)}.png`;
                }}></img>
            </div>
               <div className={styles.member_name} style={{color: starter.player ? '#cacaca' : getPositionColor(starter.requiredRole.position)}}>{starter.player ? starter.player.lastname : starter.requiredRole.position.stringValue}</div>
            <div className={styles.member_flag}>
                {starter.player && (
                    <Flag country={starter.player.country}></Flag>
                )}
            </div>
            <div className={styles.member_rating} style={{color: starter.player ? getRatingColor(starter.player.rating) : 'none'}}>{starter.player?.rating}</div>
            <div className={styles.member_number}>{starter.player?.contract.jerseyNumber}</div>
            <div className={styles.member_warning}>{starter.wrongPosition ? "OFF" : ""}</div>
        </div>
    );
}
export default LineupMember;