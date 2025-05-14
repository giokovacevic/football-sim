import { useState } from "react";
import "./roster_style.css";
import RosterMember from "./RosterMember";

function Roster({club, setDragData, substitute}:any) {
    
    return (
        <div className="roster">
            <div className="header" style={{height: '28px'}}>
                <div className="header-text" style={{lineHeight: '28px'}}>Bench</div>
            </div>
            <div className="bench">
                {club.roster.bench.map((player:any, index:number) => (
                    <RosterMember key={index} player={player} setDragData={setDragData} substitute={null}></RosterMember>
                ))}
            </div>
            <div className="header" style={{marginTop: '2px', height: '22px'}}>
                <div className="header-text">Starters</div>
            </div>
            <div className="starters">
                {club.roster.lineup.formationDTO.requiredRoleDTOs.map((role:any, index:number) => {
                    const starter:any = club.roster.lineup.starters[role.stringValue];
                    const player:any = starter ? starter.player : null;
                    if(player) {
                        return (
                            <RosterMember key={index} player={player} setDragData={setDragData} substitute={substitute}></RosterMember>
                        );
                    } 
                })}
                
            </div>
        </div>
    );
}//
export default Roster;