import type { ICountry } from "./../country/Country"
import type { IPosition } from "../position/Position";
import type { IArrangement } from "./Arrangement";
import type { IContract } from "./Contract";

export interface IPlayer{
    readonly id: number,
    readonly name: string,
    readonly lastname: string,
    readonly rating: number,
    readonly potential: number,
    readonly projectedRating: number,
    readonly country: ICountry;
    readonly positions: IPosition[];
    readonly birthYear: number,
    readonly currentAge: number,
    readonly height: number,
    readonly stamina: number,
    readonly contract: IContract;
    readonly nationalArrangement?: IArrangement;
}