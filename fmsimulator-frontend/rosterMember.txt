import Positions from "../position/Positions";

function RosterMember({player, setDragData, substitute}:any) {

    function onRightClickHandler(e:any){
        e.preventDefault();
        if((player.contract.roleDTO.stringValue) !== "sub" && (player.contract.roleDTO.stringValue !== "undefined")) {
            console.log(player.contract.roleDTO.stringValue);
            substitute("kick", null, player.contract.roleDTO.stringValue);
        }
        
    }

    function onDragStartHandler(e:React.DragEvent, data:number) { 
        if(player.contract.roleDTO.stringValue === "sub") {
            setDragData({mode: "sub", data: data});
        }
    }

    return (
        <div className="roster-member" draggable={player.contract.roleDTO.stringValue === "sub"} onDragStart={(e) => onDragStartHandler(e, player.id)} onContextMenu={(e) => onRightClickHandler(e)}>
            <div className="roster-member-flag">
                <img src={"./country_flag_icons/" + player.country.id + ".png"} onDragStart={(e) => e.preventDefault()}></img>
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