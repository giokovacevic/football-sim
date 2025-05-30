import type { LoginResponse } from "../types/responses/Responses";
import { API_URL } from "../utils/Config";

export const login = async (username:string, password:string) => {
    const response = await fetch(`${API_URL}login`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username, password})
    });
    if(!response.ok) {
        throw new Error('Invalid Credentials');
    }

    const data:LoginResponse = await response.json();
    return data;
}