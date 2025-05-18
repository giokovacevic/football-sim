import type { Player } from "./Player";

export const compareByRating = (a: Player, b:Player) => {
    return (a.rating - b.rating);
}
export const compareByLastname = (a: Player, b:Player) => {
    return a.lastname.localeCompare(b.lastname);
}
export const compareByAge = (a: Player, b:Player) => {
    return (a.currentAge - b.currentAge);
}
export const compareByCountry = (a: Player, b:Player) => {
    return a.country.name.localeCompare(b.country.name);
}
export const compareByPosition = (a: Player, b:Player, orientation:string) => {
    switch (orientation) {
        case 'gk':
            return (a.preferredPositions.goalkeeper && !b.preferredPositions.goalkeeper) ? -1 : 1;
        case 'df':
            return (a.preferredPositions.defender && !b.preferredPositions.defender) ? -1 : 1;  
        case 'mf':
            return (a.preferredPositions.midfielder && !b.preferredPositions.midfielder) ? -1 : 1;
        case 'fw':
            return (a.preferredPositions.forward && !b.preferredPositions.forward) ? -1 : 1;  
        default:
            return 0;
    }
}

export const compare = (a: Player, b: Player, property:(keyof Player), orientation?:string) => {
    switch (property) {
        case 'rating':
            return compareByRating(a, b);
        case 'lastname':
            return compareByLastname(a, b);
        case 'currentAge':
            return compareByAge(a, b);
        case 'preferredPositions':
            return orientation ? compareByPosition(a, b, orientation) : 0;
        case 'country':
            return compareByCountry(a, b);
        default:
            return 0;
    }
}