import type { Club } from "../types/models/team/Club";
import { API_URL } from "../utils/Config";

export const getAllClubs = async ():Promise<Club[]> => {
    try {
        const response = await fetch(`${API_URL}clubs/all`);
        if(!response.ok) {
            throw new Error("Error fetching in ClubService: getAllClubs | " + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error('Error in ClubService: getAllClubs | ' + error);
    }
}

export const getClubById = async (id:string):Promise<Club> => {
    try {
        const response = await fetch(`${API_URL}clubs/${id}`);
        if(!response.ok) {
            throw new Error(`Error fetching in ClubService: getAllClubs (${id}) | ` + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error(`Error in ClubService: getAllClubs (${id}) | ` + error);
    }
}