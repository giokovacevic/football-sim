import type { Player } from '../../types/models/player/Player';
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
                    <div className={styles.playerlistheader_positions_option_gk} onClick={() => onHeaderClicked('positions', 'gk')}>GK</div>
                    <div className={styles.playerlistheader_positions_option_df} onClick={() => onHeaderClicked('positions', 'df')}>DF</div>
                    <div className={styles.playerlistheader_positions_option_mf} onClick={() => onHeaderClicked('positions', 'mf')}>MF</div>
                    <div className={styles.playerlistheader_positions_option_fw} onClick={() => onHeaderClicked('positions', 'fw')}>FW</div>
                </div>
            </div>
            <div className={styles.playerlistheader_age} onClick={() => onHeaderClicked('currentAge')}>Age</div>
            {(variant === 'lineup' || variant === 'squad') && (
                <div className={styles.playerlistheader_role}>Role</div>
            )}
            {variant === 'lineup' && (
                <div className={styles.playerlistheader_stamina}>Stamina</div>
            )}
            {variant === 'preview' && (
                <div className={styles.playerlistheader_club} onClick={() => onHeaderClicked('contract', 'teamId')}>Club</div>
            )}
            {(variant === 'squad' || variant === 'preview') && (
                <div className={styles.playerlistheader_salary} onClick={() => onHeaderClicked('contract', 'salary')}>Salary</div>
            )}
            {(variant === 'squad' || variant === 'preview') && (
                <div className={styles.playerlistheader_contract} onClick={() => onHeaderClicked('contract', 'expireDate')}>Expiring</div>
            )}
            <div className={styles.playerlistheader_rating} onClick={() => onHeaderClicked('rating')}>Rtg.</div>
        </div>
    );
}

export default PlayerListHeader;