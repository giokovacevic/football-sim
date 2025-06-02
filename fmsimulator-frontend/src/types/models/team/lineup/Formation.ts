import type { IRole } from "./Role";

export interface IFormation{
    readonly id: string,
    readonly requiredRoles: IRole[],
    readonly defensiveBonus: number,
    readonly offensiveBonus: number;
}