const loadApiUrl = () => {
    const apiUrl = import.meta.env.VITE_API_URL;
    if(!apiUrl) throw new Error('Failed to load VITE_API_URL from the .env file');
    return apiUrl;
}
export const API_URL = loadApiUrl();