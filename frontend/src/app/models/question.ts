import { Test } from "./test";

export class Question {
id!: number;
question!: string;
type?: string;
true1!: string;
false1?: string;
false2?: string;
false3?: string;
tests?: Test[];
}
