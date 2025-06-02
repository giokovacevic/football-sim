import type { ICountry } from "../country/Country";
import type { IRoster } from "./Roster";

export interface ITeam{
    readonly type: string,
    readonly id: string,
    readonly name: string,
    readonly fullname: string,
    readonly preferredJersey: string,
    readonly country: ICountry;
    readonly roster: IRoster;
}