import type { Team } from "../team/Team";
import type { Competitor } from "./Competitor";
import type { Placement } from "./Placement";
import type { Schedule } from "./Schedule";

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
    readonly schedule: Schedule; //TODO: fix Schedule
    readonly type: string,
    readonly teams: Team[];
}