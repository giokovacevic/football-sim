import type { ICompetition } from "./Competition";
import type { ICompetitor } from "./Competitor";
import type { IMatch } from "./Match";

export interface ILeague extends ICompetition{
    readonly round: number,
    readonly cicle: number,
    readonly relegation: number;
    readonly parentLeagueId: string,
    readonly competitors: ICompetitor[],
    readonly schedule?: IMatch[][];
}