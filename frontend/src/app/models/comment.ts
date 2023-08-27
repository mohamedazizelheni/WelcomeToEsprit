import { Reaction } from "./reaction";
import { User } from "./user";

export class Comment {
    id!: number;
    text!: string;
    date!: string;
	totReaction!: number;
    comment_id!: number;
    post_id!: number;
    user!: User;
    reactions!:Reaction[];


}