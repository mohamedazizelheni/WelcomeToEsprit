import { Comment } from "../models/comment";
import { Rating } from "./rating";
import { User } from "./user";

export class Post {
    id!: number;
    commentCount!: number;
    date!: string;
    rating!: number;
    text!: string;
    sentiment!:string;
    showCommentForm?: boolean;
    comments!: Comment[];
    ratings !: Rating[];
    user!: User;
    newCommentText!: string;
    ratingValue!:number;

}
