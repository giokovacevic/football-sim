import type { Player } from "../types/models/player/Player";
import type { PlayersPageResponse } from "../types/responses/Responses";
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

export const getAllPlayersByPage = async (page: number, limit: number):Promise<PlayersPageResponse> => {
    try {
        const response = await fetch(`${API_URL}players/all/paginated?pageNumber=${page}&limit=${limit}`);
        if(!response.ok) {
            throw new Error(`Error fetching in PlayerService: getAllPlayersByPage (${page}, ${limit}) | ` + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error(`Error in PlayerService: getAllPlayersByPage (${page}, ${limit}) | ` + error);
    }
}

export const getPlayerById = async (id: number):Promise<Player> => {
    try {
        const response = await fetch(`${API_URL}players/${id}`);
        if(!response.ok) {
            throw new Error(`Error fetching in PlayerService: getPlayerById (${id}) | ` + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error(`Error in PlayerService: getPlayerById (${id}) | ` + error);
    }
}