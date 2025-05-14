import { Arrangement } from "./Arrangement";

export interface Contract extends Arrangement{
    readonly salary: number,
    readonly signData: number,
    readonly expireDate: number;
}