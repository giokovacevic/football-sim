import type { Position } from "./Position";

export interface PreferredPositions{
    readonly positionDTOs: Position[],
    readonly positions: string[],
    readonly goalkeeper: boolean,
    readonly defender: boolean,
    readonly midfielder: boolean,
    readonly forward: boolean;
}