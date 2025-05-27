import type { Player } from "../../player/Player";
import type { Role } from "./Role";

export interface Starter{
    readonly player?: Player,
    readonly currentRating: number,
    readonly wrongPosition: boolean,
    readonly requiredRole:Role;
}