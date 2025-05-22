import type { Player } from "../types/player/Player";
import type { Team } from "../types/team/Team"

export const extractFullSquad = (team:Team) => {
    let starters:Player[] = [];
    team.roster.lineup.starters.forEach(starter => {
        if(starter.player != null) starters.push(starter.player);
    });
    return [...team.roster.bench, ...starters];
}