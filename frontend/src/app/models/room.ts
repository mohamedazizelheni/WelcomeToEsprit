import { Interview } from "./interview";

export class Room {
    id!: number;
    name?: string;
    block?: string;
    map?: any;
    interviews?: Interview[];
}
