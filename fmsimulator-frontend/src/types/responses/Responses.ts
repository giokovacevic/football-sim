import type { Player } from "../models/player/Player";

export interface PlayersPageResponse{
    players: Player[],
    totalPlayers: number,
    page: number,
    totalPages: number;
}