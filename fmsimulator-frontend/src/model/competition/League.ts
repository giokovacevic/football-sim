import { Team } from "../team/Team";
import { Competitor } from "./Competitor";
import { Placement } from "./Placement";

export interface League{
    readonly capacity: number,
    readonly cicle: number,
    readonly cicleOver: boolean;
    readonly competitors: Competitor[],
    readonly id: string,
    readonly lastRound: number,
    readonly name: string,
    readonly parentLeagueId: string,
    readonly placements: Placement[]
    readonly relegation: number,
    readonly round: number,
    readonly schedule: null; //TODO: fix Schedule
    readonly type: string,
    readonly teams: Team[];
}