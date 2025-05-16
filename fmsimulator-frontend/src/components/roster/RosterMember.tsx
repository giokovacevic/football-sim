import type { Player } from "../../model/player/Player";
import Positions from "../position/Positions";

function RosterMember({player}:{player: Player}) {

    return (
        <div className="roster-member">
            <div className="roster-member-flag">
                <img src={"./country_flag_icons/" + player.country.id + ".png"}></img>
            </div>
            <div className="roster-member-name">{player.lastname}&nbsp;<span>{player.name === "-" ? "" : player.name}</span>
            </div>
            <div className="roster-member-positions"><Positions positions={player.preferredPositions.positionDTOs}></Positions></div>
            <div className="roster-member-age">{player.currentAge}</div>
            <div className="roster-member-role">
                <div className="roster-member-role-content" style={(player.contract.roleDTO.stringValue === "sub") ? {backgroundColor: 'transparent'} : {backgroundColor: `${player.contract.roleDTO.positionDTO.color}`}}>
                   {(player.contract.roleDTO.stringValue === "sub") ? "" : player.contract.roleDTO.stringValue}
                </div>
            </div>
            <div className="roster-member-rating" style={{color: `${player.ratingColor}`}}>{player.rating}</div>
        </div>
    );
    
}
export default RosterMember;