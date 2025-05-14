import { Player } from "../player/Player";
import { Lineup } from "./Lineup";

export interface Roster{
    readonly bench: Player[],
    readonly capacity: number,
    readonly currentSalaries: number,
    readonly lineup: Lineup,
    readonly sizeCurrent: number,
}