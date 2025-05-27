import type { Arrangement } from "./Arrangement";

export interface Contract extends Arrangement{
    readonly salary: number,
    readonly signDate: number,
    readonly expireDate: number;
}