import type { Team } from "../team/Team";

export interface Match{
    readonly hostId: string,
    readonly guestId: string,
    readonly score: number[],
    readonly neutrality: boolean;
}