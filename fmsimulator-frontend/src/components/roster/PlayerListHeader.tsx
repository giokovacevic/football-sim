import type { Player } from '../../model/player/Player';
import type { PlayerListVariant } from './PlayerList';
import styles from './PlayerList.module.css';

type PlayerListHeaderProps = {
    onHeaderClicked: (sortingKey: keyof Player, orientation?:string) => void;
    variant: PlayerListVariant;
};

const PlayerListHeader = ({variant, onHeaderClicked}:PlayerListHeaderProps) => {
    
    return (
        <div className={styles.playerlistheader_root}>
            <div className={styles.playerlistheader_flag} onClick={() => onHeaderClicked('country')}>Flag</div>
            <div className={styles.playerlistheader_name} onClick={() => onHeaderClicked('lastname')}>Name</div>
            <div className={styles.playerlistheader_positions}>
                <div className={styles.playerlistheader_positions_title}>Positions</div>
                <div className={styles.playerlistheader_positions_options}>
                    <div className={styles.playerlistheader_positions_option_gk} onClick={() => onHeaderClicked('preferredPositions', 'gk')}>GK</div>
                    <div className={styles.playerlistheader_positions_option_df} onClick={() => onHeaderClicked('preferredPositions', 'df')}>DF</div>
                    <div className={styles.playerlistheader_positions_option_mf} onClick={() => onHeaderClicked('preferredPositions', 'mf')}>MF</div>
                    <div className={styles.playerlistheader_positions_option_fw} onClick={() => onHeaderClicked('preferredPositions', 'fw')}>FW</div>
                </div>
            </div>
            <div className={styles.playerlistheader_age} onClick={() => onHeaderClicked('currentAge')}>Age</div>
            {(variant === 'lineup' || variant === 'squad') && (
                <div className={styles.playerlistheader_role}>Role</div>
            )}
            <div className={styles.playerlistheader_rating} onClick={() => onHeaderClicked('rating')}>Rtg.</div>
        </div>
    );
}

export default PlayerListHeader;