import { useState } from 'react';
import type { Player } from '../../types/player/Player';
import { getContractColor, getPositionColor, getRatingColor, getStaminaColor } from '../../utils/ColorUtils';
import { getCurrentStaminaWidth } from '../../utils/PlayerUtils';
import Flag from '../flag/Flag';
import Positions from '../position/Positions';
import Tooltip from '../tooltip/Tooltip';
import type { PlayerListVariant } from './PlayerList';
import styles from './PlayerList.module.css';

type PlayerListMemberProps = {
    player: Player,
    currentYear: number,
    onPlayerClicked?: (player:Player) => void,
    variant: PlayerListVariant;
};

const PlayerListMember = ({player, currentYear, variant}: PlayerListMemberProps) => {
    const [hovered, setHovered] = useState<boolean>(false);
    const staminaMaxWidth = 50;

    return ( 
        <div className={styles.playerlistmember_root}>
            <div className={styles.playerlistmember_flag}>
                <Flag key={player.country.id} country={player.country}></Flag>
            </div>
            <div className={styles.playerlistmember_name}>{player.lastname}&nbsp;<span>{player.name === "-" ? "" : player.name}</span></div>
            <div className={styles.playerlistmember_positions}><Positions key={player.id} positions={player.positions}></Positions></div>
            <div className={styles.playerlistmember_age}>{player.currentAge}</div>
            {(variant === 'lineup' || variant === 'squad') && (
                <div className={styles.playerlistmember_role}>
                    <div className={styles.playerlistmember_role_content} style={(player.contract.role.stringValue === "sub") ? {backgroundColor: 'transparent'} : {backgroundColor: `${getPositionColor(player.contract.role.position)}`}}>
                        {player.contract.role.stringValue === "sub" ? "" : player.contract.role.stringValue}
                    </div>
                </div>
            )}
            {variant === 'lineup' && (
                <div className={styles.playerlistmember_stamina}>
                    <div className={styles.playerlistmember_stamina_wrapper} style={{width: `${staminaMaxWidth+4}px`}}>
                         <div className={styles.playerlistmember_stamina_content} style={{backgroundColor: `${getStaminaColor(player.stamina)}`, width: `${getCurrentStaminaWidth(player.stamina, staminaMaxWidth)}px`}}></div>
                    </div>
                </div>
            )}
            {variant === 'preview' && (
                <div className={styles.playerlistmember_club}>
                    <div className={styles.playerlistmember_club_logo} onMouseEnter={() => setHovered(true)} onMouseLeave={() => setHovered(false)}>
                        <img src={`./club_logo_icons/${player.contract.teamId}.png`}></img>
                        <Tooltip key={player.id} text={player.contract.teamName} visible={hovered}></Tooltip> 
                    </div>
                </div>
            )}
            {(variant === 'squad' || variant === 'preview') && (
                <div className={styles.playerlistmember_salary}>{player.contract.salary}</div>
            )}
            {(variant === 'squad' || variant === 'preview') && (
                <div className={styles.playerlistmember_contract}>
                    <div className={styles.playerlistmember_contract_content} style={{backgroundColor: `${getContractColor(player.contract.expireDate, currentYear)}`}}>{(player.contract.expireDate === currentYear) ?  "-" : player.contract.expireDate}</div>
                </div>
            )}
            <div className={styles.playerlistmember_rating} style={{color: `${getRatingColor(player.rating)}`}}>{player.rating}</div>
        </div>
    );
}

export default PlayerListMember;