import { Comment } from "./comment";
import { TypeReaction } from "./typeReaction";
import { User } from "./user";

export class Reaction {
    id!: number;
    type!: TypeReaction; 
    date!: string;
    comment!: Comment;
    user!: User ;
  
}