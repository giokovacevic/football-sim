import type { Competition } from "./Competition";
import type { Competitor } from "./Competitor";
import type { Match } from "./Match";

export interface League extends Competition{
    readonly round: number,
    readonly cicle: number,
    readonly relegation: number;
    readonly parentLeagueId: string,
    readonly competitors: Competitor[],
    readonly schedule?: Match[][];
}