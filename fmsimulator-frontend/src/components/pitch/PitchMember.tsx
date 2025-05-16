import type { Role } from "../../model/player/Role";
import type { Starter } from "../../model/team/Lineup";
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
            <img src={(role.stringValue === 'GK' ? jersey + "gk_" + kits + ".png": jersey + kits + ".png")} style={starter.empty ? {opacity: '0.1'} : {opacity: '1.0'}} alt={teamId} onError={(e) => {
                const target = e.target as HTMLImageElement;
                target.src = defaultJersey;
            }}></img>
        </div>
        <div className="player-card-number">{!starter.empty ? player?.contract.jerseyNumber : ""}</div>
        <div className="player-card-flag">
            <img src={"./country_flag_icons/" + (!starter.empty ? player?.country.id + ".png" : "noflag.png")} alt="error"></img>
        </div>
            <div className="player-card-rating" style={{color: `${(!starter.empty ? starter.ratingColor : 'black')}`}}>{!starter.empty ? starter.currentRating : ""}</div>
            <div className="player-card-position">{starter ? (starter.outOfPosition ? "OFF" : "") : ""}</div>
            <div className="player-card-name" style={starter.empty ? {color: `${role.positionDTO.color}`} : {color: '#e0e0e0'}}>{!starter.empty ? player?.lastname : role.stringValue}</div>
        </div>
        );
    
}
export default PitchMember;