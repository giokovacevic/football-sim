import type { IPosition } from "./../../position/Position";

export interface IRole{
    readonly stringValue: string,
    readonly x: number,
    readonly y: number,
    readonly position: IPosition;
}