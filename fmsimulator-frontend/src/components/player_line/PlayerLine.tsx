import type { IPlayer } from '../../types/models/player/Player';
import { getContractColor, getPositionColor, getRatingColor, getStaminaColor } from '../../utils/ColorUtils';
import { getCurrentStaminaWidth } from '../../utils/PlayerUtils';
import Flag from '../flag/Flag';
import Logo from '../logo/Logo';
import Positions from '../position/Positions';
import styles from './PlayerLine.module.css';

type PlayerLineProps = {
    player: IPlayer,
    currentYear: number,
    onPlayerClicked?: (player:IPlayer) => void;
};

const PlayerLine = ({player, currentYear, onPlayerClicked}:PlayerLineProps) => {
    const staminaMaxWidth = 50;
    
    return ( 
        <div className={styles.line_root}>
            <div className={styles.line_flag}>
                <Flag key={player.country.id} country={player.country}></Flag>
            </div>
            <div className={styles.line_name}>{player.lastname}&nbsp;<span>{player.name === "-" ? "" : player.name}</span></div>
            <div className={styles.line_positions}><Positions key={player.id} positions={player.positions}></Positions></div>
            <div className={styles.line_age}>{player.currentAge}</div>
            <div className={styles.line_role}>
                <div className={styles.line_role_content} style={(player.contract && (player.contract.role.stringValue !== "sub")) ? {backgroundColor: `${getPositionColor(player.contract.role.position)}`} : {backgroundColor: 'transparent'}}>
                    {player.contract && (player.contract.role.stringValue !== "sub") ? player.contract.role.stringValue : ""}
                </div>
            </div>
            <div className={styles.line_stamina}>
                <div className={styles.line_stamina_wrapper} style={{width: `${staminaMaxWidth+4}px`}}>
                        <div className={styles.line_stamina_content} style={{backgroundColor: `${getStaminaColor(player.stamina)}`, width: `${getCurrentStaminaWidth(player.stamina, staminaMaxWidth)}px`}}></div>
                </div>
            </div>
            <div className={styles.line_club}>
                <Logo url={`/club_logo_icons/${player.contract ? player.contract.teamId : "default"}.png`} text={player.contract ? player.contract.teamName : "Free Agent"}></Logo>
            </div>
            <div className={styles.line_salary}>{player.contract ? player.contract.salary : "0"}</div>
            <div className={styles.line_contract}>
                <div className={styles.line_contract_content} style={{backgroundColor: `${getContractColor(player.contract ? player.contract.expireDate : currentYear, currentYear)}`}}>{player.contract ? player.contract.expireDate : ""}</div>
            </div>
        
            <div className={styles.line_rating} style={{color: `${getRatingColor(player.rating)}`}}>{player.rating}</div>
        </div>
    );
}
export default PlayerLine;