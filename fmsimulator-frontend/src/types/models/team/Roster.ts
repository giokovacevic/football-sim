import type { Player } from "../player/Player";
import type { Lineup } from "./lineup/Lineup";

export interface Roster{
    readonly capacity: number,
    readonly currentSize: number,
    readonly currentSalaries: number,
    readonly bench: Player[],
    readonly lineup: Lineup;
}