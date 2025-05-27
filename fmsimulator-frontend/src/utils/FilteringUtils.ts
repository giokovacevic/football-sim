import type { Position } from "../types/models/position/Position";

export const filterByPositionType = (positions: Position[], type: string):boolean => {
    for(const position of positions) {
        if(position.type === type) return true;
    }
    return false;
} 