import type { Player } from "../types/player/Player";
import type { Position } from "../types/position/Position";
import { filterByPositionType } from "./FilteringUtils";

export const comparePlayerByRating = (a: Player, b:Player) => {
    return (a.rating - b.rating);
}
export const comparePlayerByLastname = (a: Player, b:Player) => {
    return a.lastname.localeCompare(b.lastname);
}
export const comparePlayerByAge = (a: Player, b:Player) => {
    return (a.currentAge - b.currentAge);
}
export const comparePlayerByCountry = (a: Player, b:Player) => {
    return a.country.name.localeCompare(b.country.name);
}
export const comparePlayerByPosition = (a: Player, b:Player, orientation:string) => {
    return (filterByPositionType(a.positions, orientation) && !filterByPositionType(b.positions, orientation)) ? -1 : 1;
}
export const comparePlayerByContract = (a: Player, b:Player, orientation:string) => {
    if(a.contract === null && b.contract === null) return 0;
    if(a.contract !== null && b.contract === null) return 1;
    if(a.contract === null && b.contract !== null) return -1;
    if(orientation === 'salary') {
        return a.contract[orientation] - b.contract[orientation];
    }else if(orientation === 'expireDate') {
        return a.contract[orientation] - b.contract[orientation];
    }else if(orientation === 'teamId') {
        return a.contract[orientation].localeCompare(b.contract[orientation]);
    }else{
        return 0;
    }
}

export const comparePlayer = (a: Player, b: Player, property:(keyof Player), orientation?:string) => {
    switch (property) {
        case 'rating':
            return comparePlayerByRating(a, b);
        case 'lastname':
            return comparePlayerByLastname(a, b);
        case 'currentAge':
            return comparePlayerByAge(a, b);
        case 'positions':
            return orientation ? comparePlayerByPosition(a, b, orientation) : 0;
        case 'country':
            return comparePlayerByCountry(a, b);
        case 'contract':
            return orientation ? comparePlayerByContract(a, b, orientation) : 0;
        default:
            return 0;
    }
}

export const getCurrentStaminaWidth = (stamina:number, staminaMaxWidth: number) => {
    return ((stamina / 100) * staminaMaxWidth);
}