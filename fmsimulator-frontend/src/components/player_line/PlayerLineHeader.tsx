import type { IPlayer } from '../../types/models/player/Player';
import styles from './PlayerLine.module.css';

type PlayerLineHeaderProps = {
    onHeaderClicked: (sortingKey: keyof IPlayer, sortingOrientation?:string) => void;
};

const PlayerLineHeader = ({onHeaderClicked}: PlayerLineHeaderProps) => {
    return (
        <div className={styles.header_root}>
            <div className={styles.header_flag} onClick={() => onHeaderClicked('country')}>Flag</div>
            <div className={styles.header_name} onClick={() => onHeaderClicked('lastname')}>Name</div>
            <div className={styles.header_positions}>
                <div className={styles.header_positions_title}>Positions</div>
                <div className={styles.header_positions_options}>
                    <div className={styles.header_positions_option_gk} onClick={() => onHeaderClicked('positions', 'gk')}>GK</div>
                    <div className={styles.header_positions_option_df} onClick={() => onHeaderClicked('positions', 'df')}>DF</div>
                    <div className={styles.header_positions_option_mf} onClick={() => onHeaderClicked('positions', 'mf')}>MF</div>
                    <div className={styles.header_positions_option_fw} onClick={() => onHeaderClicked('positions', 'fw')}>FW</div>
                </div>
            </div>
            <div className={styles.header_age} onClick={() => onHeaderClicked('currentAge')}>Age</div>
            <div className={styles.header_role}>Role</div>
            <div className={styles.header_stamina} onClick={() => onHeaderClicked('stamina')}>Stamina</div>
            <div className={styles.header_club} onClick={() => onHeaderClicked('contract', 'teamName')}>Club</div>
            <div className={styles.header_salary} onClick={() => onHeaderClicked('contract', 'salary')}>Salary</div>
            <div className={styles.header_contract} onClick={() => onHeaderClicked('contract', 'expireDate')}>Expiring</div>
            <div className={styles.header_rating} onClick={() => onHeaderClicked('rating')}>Rtg.</div>
        </div>
    );
}
export default PlayerLineHeader;