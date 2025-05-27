import type { Role } from "./Role";

export interface Formation{
    readonly id: string,
    readonly requiredRoles: Role[],
    readonly defensiveBonus: number,
    readonly offensiveBonus: number;
}