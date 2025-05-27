import type { Role } from "../../types/models/team/lineup/Role";
import type { Starter } from "../../types/team/lineup/Starter";
import { getPositionColor, getRatingColor } from "../../utils/ColorUtils";
import "./pitch_style.css";

type PitchMemberProps = {
    _role: Role,
    starter: Starter,
    teamId: string,
    kits: string;
};

function PitchMember({_role, starter, teamId, kits}:PitchMemberProps) {
    const jersey = "./team_assets/" + teamId + "/" + teamId + "_";
    const defaultJersey = "./team_assets/default/default_light.png";
    const role = _role;
    const player = starter ? starter.player : null;
    
    return (
        <div className="player-card" style={{top: `${role.y}px`, left: `${role.x}px`}}>
        <div className="player-card-jersey">
            <img src={(role.stringValue === 'GK' ? jersey + "gk_" + kits + ".png": jersey + kits + ".png")} style={!starter.player ? {opacity: '0.1'} : {opacity: '1.0', filter: 'drop-shadow(0 0 1px rgb(191, 191, 191, 1))'}} alt={teamId} onError={(e) => {
                const target = e.target as HTMLImageElement;
                target.src = defaultJersey;
            }}></img>
        </div>
        <div className="player-card-number">{starter.player ? player?.contract.jerseyNumber : ""}</div>
        <div className="player-card-flag">
            <img src={"./country_flag_icons/" + (starter.player ? player?.country.id + ".png" : "noflag.png")} alt="error"></img>
        </div>
            <div className="player-card-rating" style={{color: `${(starter.player ? getRatingColor(starter.player.rating) : 'black')}`}}>{starter.player ? starter.currentRating : ""}</div>
            <div className="player-card-position">{starter ? (starter.wrongPosition ? "OFF" : "") : ""}</div>
            <div className="player-card-name" style={!starter.player ? {color: `${getPositionColor(role.position)}`} : {color: '#e0e0e0'}}>{starter.player ? player?.lastname : role.stringValue}</div>
        </div>
        );
    
}
export default PitchMember;