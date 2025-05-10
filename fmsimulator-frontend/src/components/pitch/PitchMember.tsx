import "./pitch_style.css";

function PitchMember({editable, _role, starter, clubId, kits, dragData, setDragData, substitute}:any) {
    const jersey = "./team_assets/" + clubId + "/" + clubId + "_";
    const defaultJersey = "./team_assets/default/default_light.png";
    const role = _role;
    const player = starter ? starter.player : null;

    function onRightClickHandler(e:any){
        e.preventDefault();
        substitute("kick", null, role.stringValue);
    }

    function onDropHandler(e:React.DragEvent) {
        console.log(dragData.mode + " " + dragData.data + " OK");
        const mode:string = dragData.mode;
        if(mode === "sub") {
            substitute(mode, dragData.data, role.stringValue);
        }else if(mode === "swap") {
            const dataIn = dragData.data;
            const dataOut = role.stringValue
            if(dataIn!==dataOut) {
                substitute(mode, dataIn, dataOut);
            }
            
        }else{
            console.log(" else" + role.stringValue);
        }
        setDragData({mode: "", data: ""});
    }

    function onDragStartHandler(e:React.DragEvent) {
        if(player != null) {
            setDragData({mode: "swap", data: role.stringValue});
        }
    }

    if(editable) {
        return (
            <div className="player-card" style={{top: `${role.y}px`, left: `${role.x}px`}} draggable={player!==null} onContextMenu={(e) => onRightClickHandler(e)} onDragOver={(e) => e.preventDefault()} onDrop={(e) => onDropHandler(e)} onDragStart={(e) => onDragStartHandler(e)}>
                <div className="player-card-jersey">
                    <img src={((role.positionDTO.stringValue === "GK") ? jersey + "gk_" + kits + ".png": jersey + kits + ".png")} style={starter.empty ? {opacity: '0.1'} : {opacity: '1.0'}} alt={clubId} onError={(e) => {
                        const target = e.target as HTMLImageElement;
                        target.src = defaultJersey;
                    }} draggable="false"></img>
                </div>
                <div className="player-card-number">{!starter.empty ? player.contract.jerseyNumber : ""}</div>
                <div className="player-card-flag">
                    <img src={"./country_flag_icons/" + (!starter.empty ? player.country.id + ".png" : "noflag.png")} alt="error" draggable="false"></img>
                </div>
                <div className="player-card-rating" style={{color: `${(!starter.empty ? starter.ratingColor : 'black')}`}}>{!starter.empty ? starter.currentRating : ""}</div>
                <div className="player-card-position">{starter ? (starter.outOfPosition ? "OFF" : "") : ""}</div>
                <div className="player-card-name" style={starter.empty ? {color: `${role.positionDTO.color}`} : {color: '#e0e0e0'}}>{!starter.empty ? player.lastname : role.stringValue}</div>
            </div>
        );
    }else{
        return (
            <div className="player-card" style={{top: `${role.y}px`, left: `${role.x}px`}}>
            <div className="player-card-jersey">
                <img src={(role.positionValue.goalkeeper ? jersey + "gk_" + kits + ".png": jersey + kits + ".png")} style={starter.empty ? {opacity: '0.1'} : {opacity: '1.0'}} alt={clubId} onError={(e) => {
                    const target = e.target as HTMLImageElement;
                    target.src = defaultJersey;
                }}></img>
            </div>
            <div className="player-card-number">{!starter.empty ? player.contract.jerseyNumber : ""}</div>
            <div className="player-card-flag">
                <img src={"./country_flag_icons/" + (!starter.empty ? player.country.id + ".png" : "noflag.png")} alt="error"></img>
            </div>
            <div className="player-card-rating" style={{color: `${(!starter.empty ? starter.ratingColor : 'black')}`}}>{!starter.empty ? starter.currentRating : ""}</div>
            <div className="player-card-position">{starter ? (starter.outOfPosition ? "OFF" : "") : ""}</div>
            <div className="player-card-name" style={starter.empty ? {color: `${role.positionValue.color}`} : {color: '#e0e0e0'}}>{!starter.empty ? player.lastname : role.stringValue}</div>
        </div>
        );
    }
}
export default PitchMember;