import { Position } from "./Position";

export interface PreferredPositions{
    readonly positionDTOs: Position[],
    readonly positions: string[];
}