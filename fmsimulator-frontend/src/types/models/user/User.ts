import type { IUserRole } from "./UserRole";

export interface IUser{
    username: string,
    role: IUserRole;
}