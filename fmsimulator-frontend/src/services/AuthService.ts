import type { IUser } from "../types/models/user/User";

export const authenticate = (token: string | null, user: IUser | null):boolean => {
    if(token){
        localStorage.setItem('token', token);
        localStorage.setItem('user', JSON.stringify(user));
        return true;
    }
    return false;
}
export const isAuthenticated = ():boolean => {
    return !!localStorage.getItem('token');
}
export const unauthenticate = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
}
export const getToken = () => {
    return localStorage.getItem('token');
}
export const getUser = (): IUser|null => {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
}