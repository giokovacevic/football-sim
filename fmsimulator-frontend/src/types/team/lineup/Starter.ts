import type { Player } from "../../player/Player";

export interface Starter{
    readonly player: Player,
    readonly currentRating: number,
    readonly wrongPosition: boolean;
}