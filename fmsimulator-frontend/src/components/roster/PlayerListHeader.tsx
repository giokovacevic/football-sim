import type { PlayerListVariant } from './PlayerList';
import styles from './PlayerList.module.css';

const PlayerListHeader = ({variant}:{variant:PlayerListVariant}) => {
    
    return (
        <div className={styles.playerlistheader_root}>
            <div className={styles.playerlistheader_flag}>flag</div>
            <div className={styles.playerlistheader_name}>name</div>
            <div className={styles.playerlistheader_positions}>positions</div>
            <div className={styles.playerlistheader_age}>age</div>
            {(variant === 'lineup' || variant === 'squad') && (
                <div className={styles.playerlistheader_role}>role</div>
            )}
            <div className={styles.playerlistheader_rating}>rtg.</div>
        </div>
    );
}

export default PlayerListHeader;