import { Interview } from "./interview";
import { Test } from "./test";
import { User } from "./user";

export class AdmissionCandidacy {
    id!: number;
    creationDate!: Date;
    bac!: boolean;
    docBacDiploma!: string;
    bacMoy!: number;
    docBacReleve!: string;
    bacYear!: string;
    bacEstablishment!: string;
    bacGovernorate!: string;
    moy1!: number;
    docReleve1!: string;
    moy2!: number;
    docReleve2!: string;
    moy3!: number;
    docReleve3!: string;
    moy4!: number;
    docReleve4!: string;
    diploma!: boolean;
    docDiploma!: string;
    level!: string;
    speciality!: string;
    testScore!: number;
    interviewScore!: number;
    status!: string;
    tests!: Test[];
    interview!: Interview;
    user!: User;
}
