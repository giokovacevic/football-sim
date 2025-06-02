import type { IPlayer } from "../../player/Player";
import type { IRole } from "./Role";

export interface IStarter{
    readonly player?: IPlayer,
    readonly currentRating: number,
    readonly wrongPosition: boolean,
    readonly requiredRole: IRole;
}