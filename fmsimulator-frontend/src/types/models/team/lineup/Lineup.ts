import type { Formation } from "./Formation";
import type { Starter } from "./Starter";

export interface Lineup{
    readonly formation: Formation,
    readonly viability: boolean,
    readonly defense: number,
    readonly offense: number,
    readonly starters: Starter[];
}