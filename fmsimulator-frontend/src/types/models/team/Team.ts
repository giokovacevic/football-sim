import type { Country } from "../country/Country";
import type { Roster } from "./Roster";

export interface Team{
    readonly type: string,
    readonly id: string,
    readonly name: string,
    readonly fullname: string,
    readonly preferredJersey: string,
    readonly country: Country;
    readonly roster: Roster;
}