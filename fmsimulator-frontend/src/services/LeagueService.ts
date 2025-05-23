import type { League } from "../types/competition/League";
import { API_URL } from "../utils/Config";

export const getAllLeagues = async ():Promise<League[]> => {
    try {
        const response = await fetch(`${API_URL}leagues/all`);
        if(!response.ok) {
            throw new Error("Error fetching in LeagueService: getAllLeagues | " + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error("Error in LeagueService: getAllLeagues | " + error);
    }
}

export const getLeagueById = async (id:string):Promise<League> => {
    try {
        const response = await fetch(`${API_URL}leagues/${id}`);
        if(!response.ok) {
            throw new Error(`Error fetching in LeagueService: getLeagueById (${id}) | ` + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error(`Error in LeagueService: getLeagueById (${id}) | ` + error);
    }
}