import type { Position } from "./../../position/Position";

export interface Role{
    readonly stringValue: string,
    readonly x: number,
    readonly y: number,
    readonly position: Position;
}