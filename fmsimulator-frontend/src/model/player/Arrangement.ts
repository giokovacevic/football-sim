import { Role } from "./Role";

export interface Arrangement{
    readonly jerseyNumber: number,
    readonly roleString: string,
    readonly roleDTO: Role,
    readonly role: string,
    readonly starting: boolean,
    readonly teamId: string;
}