import type { Player } from '../../model/player/Player';
import Flag from '../flag/Flag';
import Positions from '../position/Positions';
import type { PlayerListVariant } from './PlayerList';
import styles from './PlayerList.module.css';

type PlayerListMemberProps = {
    player: Player,
    onPlayerClicked?: (player:Player) => void,
    variant: PlayerListVariant;
};

const PlayerListMember = ({player, variant}: PlayerListMemberProps) => {
    
    return (
        <div className={styles.playerlistmember_root}>
            <div className={styles.playerlistmember_flag}>
                <Flag key={player.country.id} country={player.country}></Flag>
            </div>
            <div className={styles.playerlistmember_name}>{player.lastname}&nbsp;<span>{player.name === "-" ? "" : player.name}</span></div>
            <div className={styles.playerlistmember_positions}><Positions key={player.id} positions={player.preferredPositions.positionDTOs}></Positions></div>
            <div className={styles.playerlistmember_age}>{player.currentAge}</div>
            {(variant === 'lineup' || variant === 'squad') && (
                <div className={styles.playerlistmember_role}>
                    <div className={styles.playerlistmember_role_content} style={(player.contract.roleDTO.stringValue === "sub") ? {backgroundColor: 'transparent'} : {backgroundColor: `${player.contract.roleDTO.positionDTO.color}`}}>
                        {player.contract.roleDTO.stringValue === "sub" ? "" : player.contract.roleDTO.stringValue}
                    </div>
                </div>
            )}
            <div className={styles.playerlistmember_rating} style={{color: `${player.ratingColor}`}}>{player.rating}</div>
        </div>
    );
}

export default PlayerListMember;