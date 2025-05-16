import type { Position } from "./Position";

export interface Role{
    readonly positionDTO:Position,
    readonly stringValue: string,
    readonly x:number,
    readonly y:number;
}