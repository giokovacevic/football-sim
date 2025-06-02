import type { IRole } from "../team/lineup/Role";

export interface IArrangement{
    readonly teamId: string,
    readonly teamName: string,
    readonly jerseyNumber: number,
    readonly role: IRole;
}