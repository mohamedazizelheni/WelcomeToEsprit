import { User } from "./user";

export class Role {
    id!:number;
    role!:string;
    users?: User[];

}
