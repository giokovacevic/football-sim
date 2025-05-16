import type { Player } from "../player/Player";
import type { Formation } from "./Formation";

export type Starter = {
    readonly player: Player;
    readonly currentRating: number,
    readonly ratingColor: string,
    readonly empty: boolean,
    readonly outOfPosition: boolean;
};

export interface Lineup{
    readonly defense: number,
    readonly offense: number,
    readonly defenseRatingColor: string,
    readonly offenseRatingColor: string,
    readonly formation: string,
    readonly viable: boolean,
    readonly formationDTO: Formation,
    readonly starters: Record<string, Starter>;
}