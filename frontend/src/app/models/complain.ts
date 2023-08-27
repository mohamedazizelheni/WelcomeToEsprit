import { Interview } from "./interview";
import { User } from "./user";

export class Complain {
    id!: number;
    title?: string;
    description?: string;
    date?: Date;
    interview?: Interview;
    user!: User;
}
