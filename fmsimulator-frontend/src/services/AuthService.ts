export const authenticate = (token: string | null):boolean => {
    if(token){
        localStorage.setItem('token', token);
        return true;
    }
    return false;
}
export const isAuthenticated = ():boolean => {
    return !!localStorage.getItem('token');
}
export const unauthenticate = () => {
    localStorage.removeItem('token');
}
export const getToken = () => {
    return localStorage.getItem('token');
}