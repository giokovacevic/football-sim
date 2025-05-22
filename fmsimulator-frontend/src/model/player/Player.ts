import type { Country } from "../../types/country/Country";
import type { Arrangement } from "./Arrangement";
import type { Contract } from "./Contract";
import type { PreferredPositions } from "./PreferredPositions";

export interface Player{
    readonly id: number,
    readonly name: string,
    readonly lastname: string,
    readonly rating: number,
    readonly potential: number,
    readonly projectedPotential: number,
    readonly ratingColor: string,
    readonly country: Country,
    readonly preferredPositions: PreferredPositions,
    readonly contract: Contract,
    readonly nationalArrangement: Arrangement,
    readonly birthYear: number,
    readonly currentAge: number,
    readonly height: number,
    readonly heightStringInFeet: string,
    readonly heightStringInMeters: string;
    readonly stamina: number;
    readonly statusDuration: number;
}