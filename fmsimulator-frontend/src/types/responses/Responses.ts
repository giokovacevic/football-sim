import type { Player } from "../models/player/Player";

export interface PlayersPageResponse{
    readonly players: Player[],
    readonly totalPlayers: number,
    readonly page: number,
    readonly totalPages: number;
}
export interface LoginResponse{
    readonly message: string,
    readonly token: string | null;
}