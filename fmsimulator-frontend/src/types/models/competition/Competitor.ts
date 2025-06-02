import type { ITeam } from "../team/Team";

export interface ICompetitor{
    readonly team: ITeam,
    readonly wins: number,
    readonly draws: number,
    readonly losses: number,
    readonly goalsScored: number,
    readonly goalsConceded: number,
    readonly points: number;
}