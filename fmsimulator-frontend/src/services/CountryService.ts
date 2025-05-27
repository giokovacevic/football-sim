import type { Country } from "../types/models/country/Country";
import { API_URL } from "../utils/Config";

export const getAllCountries = async ():Promise<Country[]> => {
    try {
        const response = await fetch(`${API_URL}countries/all`);
        if(!response.ok) {
            throw new Error('Error fetching in CountryService: getAllCountries | ' + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error('Error in CountryService: getAllCountries | ' + error);
    }
}

export const getCountryById = async (id:string):Promise<Country> => {
    try {
        const response = await fetch(`${API_URL}countries/${id}`);
        if(!response.ok) {
            throw new Error(`Error fetching in CountryService: getCountryById (${id}) | ` + response.statusText);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw new Error(`Error in CountryService: getCountryById (${id}) | ` + error);
    }
}