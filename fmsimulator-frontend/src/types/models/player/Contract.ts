import type { IArrangement } from "./Arrangement";

export interface IContract extends IArrangement{
    readonly salary: number,
    readonly signDate: number,
    readonly expireDate: number;
}