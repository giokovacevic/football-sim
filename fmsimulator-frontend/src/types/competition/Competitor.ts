import type { Team } from "../team/Team";

export interface Competitor{
    readonly team: Team,
    readonly wins: number,
    readonly draws: number,
    readonly losses: number,
    readonly goalsScored: number,
    readonly goalsConceded: number,
    readonly points: number;
}