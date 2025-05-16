import type { Team } from "../team/Team";

export interface Match{
    readonly host: Team,
    readonly guest: Team,
    readonly neutral: boolean,
    readonly score: number[],
    readonly set: boolean;
}