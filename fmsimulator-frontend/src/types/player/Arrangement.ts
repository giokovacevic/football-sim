import type { Role } from "../team/lineup/Role";

export interface Arrangement{
    readonly teamId: string,
    readonly teamName: string,
    readonly jerseyNumber: number,
    readonly role: Role;
}