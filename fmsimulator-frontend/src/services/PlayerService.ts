import type { IPlayer } from "../types/models/player/Player";
import type { PlayersPageResponse } from "../types/responses/Responses";
import { API_URL } from "../utils/Config";

export const getAllPlayers = async ():Promise<IPlayer[]> => {
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

export const getAllPlayersByPage = async (pageNumber: number, limit: number, sortingKey?: keyof IPlayer, sortingOrder?: "asc" | "desc", sortingOrientation?: string):Promise<PlayersPageResponse> => {
    try {
        let query: string = `?pageNumber=${pageNumber}&limit=${limit}`;
        if(sortingKey) query+=`&sortingKey=${sortingKey}`;
        if(sortingOrder) query+=`&sortingOrder=${sortingOrder}`;
        if(sortingOrientation) query+=`&sortingOrientation=${sortingOrientation}`;
        const response = await fetch(`${API_URL}players/all/paginated${query}`);
        if(!response.ok) {
            throw new Error(`Error fetching in PlayerService: getAllPlayersByPage (${pageNumber}, ${limit}) | ` + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error(`Error in PlayerService: getAllPlayersByPage (${pageNumber}, ${limit}) | ` + error);
    }
}

export const getPlayerById = async (id: number):Promise<IPlayer> => {
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