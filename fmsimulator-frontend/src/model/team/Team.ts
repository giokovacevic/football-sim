import { Country } from "../country/Country";
import { Roster } from "./Roster";

export interface Team{
    readonly id: string,
    readonly name: string,
    readonly fullname: string,
    readonly country: Country;
    readonly roster: Roster,
    readonly type: string;
    readonly preferredJersey: string,
}