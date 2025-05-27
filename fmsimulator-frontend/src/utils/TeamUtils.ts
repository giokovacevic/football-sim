import type { Player } from "../types/models/player/Player";
import type { Role } from "../types/models/team/lineup/Role";
import type { Starter } from "../types/team/lineup/Starter";
import type { Team } from "../types/models/team/Team"

export const extractFullSquad = (team:Team) => {
    let starters:Player[] = [];
    team.roster.lineup.starters.forEach(starter => {
        if(starter.player != null) starters.push(starter.player);
    });
    return [...team.roster.bench, ...starters];
}
export const extractStarterByRole = (starters:Starter[], role:Role) => {
    for(const starter of starters) {
        if(starter.player) {
            if(starter.player.contract.role.stringValue === role.stringValue) return starter;
        }
    }
    return null;
}