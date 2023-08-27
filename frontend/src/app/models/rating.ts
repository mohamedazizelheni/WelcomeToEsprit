import { Post } from "./post";
import { User } from "./user";

export class Rating {
    id!: number;
    value!: number; 
    date!: string;
    post!: Post;
    user!: User ;
  
}