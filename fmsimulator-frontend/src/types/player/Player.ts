import type { Country } from "../country/Country";
import type { Position } from "../position/Position";
import type { Arrangement } from "./Arrangement";
import type { Contract } from "./Contract";

export interface Player{
    readonly id: number,
    readonly name: string,
    readonly lastname: string,
    readonly rating: number,
    readonly potential: number,
    readonly projectedRating: number,
    readonly country: Country;
    readonly positions:Position[];
    readonly birthYear: number,
    readonly currentAge: number,
    readonly height: number,
    readonly stamina: number,
    readonly contract: Contract;
    readonly nationalArrangement?: Arrangement;
}