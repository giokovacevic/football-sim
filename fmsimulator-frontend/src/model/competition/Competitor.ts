import type { Team } from "../team/Team";

export interface Competitor{
    readonly wins: number,
    readonly draws: number,
    readonly losses: number,
    readonly points: number,
    readonly goalsConceded: number,
    readonly goalsScored: number,
    readonly goalDifferential: number,
    readonly team:Team;
}