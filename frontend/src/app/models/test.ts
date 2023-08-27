import { AdmissionCandidacy } from "./admission-candidacy";
import { Question } from "./question";

export class Test {
id!: number;
title!: string;
type!: string;
questions: Question[]=[];
admissionCandidacies: AdmissionCandidacy[]=[];
}
