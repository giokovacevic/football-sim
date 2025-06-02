import type { IPlayer } from "../player/Player";
import type { ILineup } from "./lineup/Lineup";

export interface IRoster{
    readonly capacity: number,
    readonly currentSize: number,
    readonly currentSalaries: number,
    readonly bench: IPlayer[],
    readonly lineup: ILineup;
}