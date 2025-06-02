import type { IFormation } from "./Formation";
import type { IStarter } from "./Starter";

export interface ILineup{
    readonly formation: IFormation,
    readonly viability: boolean,
    readonly defense: number,
    readonly offense: number,
    readonly starters: IStarter[];
}