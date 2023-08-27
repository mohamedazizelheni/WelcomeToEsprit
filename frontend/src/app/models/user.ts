import { Role } from "./role";

export class User {
    id!:number;
    cin!:string;
    firstName!:string;
    lastName!:string;
    gender!:string;
    image!:string;
    image0!:string;
    address!:string;
    birthDate!:Date;
    phone!:string;
    mail!:string;
    password!:string;
    department!:string;
    hiringDate!:Date;
    evaluator!:boolean;
    jury!:boolean;
    speaker!:boolean;
    bacSection!:string;
    educationLevel!:string;
    speciality!:string;
    classe!:string;
    creationDate!:Date;
    role!:Role;

}
