import type { Team } from "./Team";

export interface Club extends Team{
    readonly rating: number,
    readonly budgetMoney: number,
    readonly transferMoney: number,
}