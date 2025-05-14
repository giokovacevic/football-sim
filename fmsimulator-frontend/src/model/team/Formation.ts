import { Role } from "../player/Role";

export interface Formation{
    readonly id: string,
    readonly defensiveBonus: number,
    readonly offensiveBonus: number,
    readonly requiredRoleDTOs: Role[];
}