import DropdownMenu from "../dropdown_menu/DropdownMenu";
import Pitch from "../pitch/Pitch";
import "./lineup_style.css";

function Lineup({myClub, formations, currentFormation, changeFormation, dragData, setDragData, substitute, save}:any) {
    
    return (
        <div className="lineup">
            <div className="header">
                <div className="header-formation-menu">
                <DropdownMenu items={formations} selectedItem={currentFormation} onSelect={changeFormation} textKey="id" iconKey="_default" iconPath="./assets/formation_default_icon.png"></DropdownMenu>
                </div>
                <div className="header-ratings">
                    <div className="defense">
                        <div className="defense-icon">
                            <img src="assets/defense_icon.png"></img>
                        </div>
                        <div className="defense-text" style={{color: `${myClub.roster.lineup.defenseRatingColor}`}}>{myClub.roster.lineup.defense}</div>
                    </div>
                    <div className="offense">
                        <div className="offense-icon">
                            <img src="assets/offense_icon.png"></img>
                        </div>
                        <div className="offense-text" style={{color: `${myClub.roster.lineup.offenseRatingColor}`}}>{myClub.roster.lineup.offense}</div>
                    </div>
                </div>
                <div className="header-save">
                    <button className="save-button" onClick={save}>Save Lineup</button>
                </div>
            </div>
            
            <Pitch editable={true} club={myClub} kits={"dark"} setDragData={setDragData} dragData={dragData} substitute={substitute}/*{myClub.preferredJersey} */></Pitch>
        </div> //myClub.preferredJersey
    );
}
export default Lineup;