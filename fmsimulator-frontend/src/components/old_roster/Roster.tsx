import { useState } from "react";
import "./roster_style.css";
import RosterMember from "./RosterMember";
import type { Player } from "../../model/player/Player";
import type { Team } from "../../model/team/Team";
import type { Starter } from "../../model/team/Lineup";
import type { Role } from "../../model/player/Role";

function Roster({team}:{team: Team}) {
    
    return (
        <div className="roster">
            <div className="header" style={{height: '28px'}}>
                <div className="header-text" style={{lineHeight: '28px'}}>Roster</div>
            </div>
            <div className="bench">
                {team.roster.bench.map((player:Player, index:number) => (
                    <RosterMember key={player.id} player={player}></RosterMember>
                ))}
            </div>
            <div className="starters">
                {team.roster.lineup.formationDTO.requiredRoleDTOs.map((role:Role, index:number) => {
                    const starter:Starter = team.roster.lineup.starters[role.stringValue];
                    const player:Player|undefined = starter ? starter.player : undefined;
                    if(player) {
                        return (
                            <RosterMember key={player.id} player={player}></RosterMember>
                        );
                    } 
                })}
                
            </div>
        </div>
    );
}//
export default Roster;