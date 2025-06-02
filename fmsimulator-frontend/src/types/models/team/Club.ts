import type { ITeam } from "./Team";

export interface IClub extends ITeam{
    readonly rating: number,
    readonly budgetMoney: number,
    readonly transferMoney: number;
}