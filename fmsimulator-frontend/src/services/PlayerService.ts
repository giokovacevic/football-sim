import type { Player } from "../types/player/Player";
import { API_URL } from "../utils/Config";

export const getAllPlayers = async ():Promise<Player[]> => {
    try {
        const response = await fetch(`${API_URL}players/all`);
        if(!response.ok) {
            throw new Error("Error fetching in PlayerService: getAllPlayers | " + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error("Error in PlayerService: getAllPlayers | " + error);
    }
}