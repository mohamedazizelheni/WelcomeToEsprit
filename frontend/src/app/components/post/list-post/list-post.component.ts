import {  Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { PostService } from 'src/app/services/postservice/post.service';
import * as moment from 'moment';
import { Observable, interval, startWith, switchMap } from 'rxjs';
import { Rating } from 'src/app/models/rating';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import * as AOS from 'aos';
import { Reaction } from 'src/app/models/reaction';
import { TypeReaction } from 'src/app/models/typeReaction';
import { trigger, transition, style, animate, state } from '@angular/animations';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.css','./post.scss'],
  })
export class ListPostComponent implements OnInit {
  posts?: Post[];
  comment: Comment = new Comment;

 post: Post = new Post;
  newCommentText = "";
  newRating!: number;
value!:number;
commentId!: number;
reactionType!: TypeReaction;
reactions!: Reaction[];

  searchForm = new FormGroup({
    search: new FormControl(''),
    filter: new FormControl('date')
  });
  results$!: Observable<Post[]>;
  constructor(private postService: PostService, private route: ActivatedRoute) {
    
   }
  ngOnInit(): void {
    AOS.init();
    this.postService.getPosts().subscribe(posts => {
      this.posts = posts;
      for (const post of this.posts) {
        this.postService.getCommentsByPostId(post.id).subscribe(comments => {
          post.comments = comments;
        });
        post.rating = post.rating;
      }
      
    });
    
   

  }

  getPostscom(): void {
    this.postService.getPosts().subscribe(posts => {
      this.posts = posts;
      for (let post of this.posts) {
        this.postService.getCommentsByPostId(post.id).subscribe(comments => {
          post.comments = comments;
        });
      }
    });
  }
  formatDate(date: string): string {
    return moment(date).fromNow();
  }


  showCommentBox(post: Post) {
    post.showCommentForm = true;
  }
  submitComment(post: Post,commentForm: NgForm,newComment:string) {
    if (!this.newCommentText || this.newCommentText.trim() === '') {
      alert('Le commentaire ne peut pas être vide.');
      return;
    }
    

    
    this.postService.addComment(post.id, newComment).subscribe((content: Comment) => {
      post.comments.push(content);
      post.commentCount++;
      
      post.newCommentText = '';
      commentForm.resetForm();
      // Récupérer tous les commentaires pour le post donné
    this.postService.getCommentsByPostId(post.id).subscribe((comments: Comment[]) => {
      post.comments = comments;
    });
    });
  }

  addRating( post:Post , newRating: number):void {
  
   
    this.postService.addRating(post.id, newRating).subscribe((response: Rating) => {
      // Update the post rating and rating count
      post.ratings.push(response);
    });
    this.postService.getRating(post.id).subscribe((ratings: Rating[]) => {
      post.ratings = ratings;
    });
  }
  showReactionss = false;
  like=TypeReaction.like;
  dislike=TypeReaction.dislike;
  heart=TypeReaction.heart;
  addReaction(comment : Comment,type: TypeReaction) {
    this.reactionType = type;
    this.postService.likeComment(comment.id, type)
      .subscribe((reaction: Reaction) => {
        comment.reactions.push(reaction);
        this.comment.totReaction++;
        this.reactions = this.comment.reactions;
      });
  }
  
  



 

  onSubmit() {
    const content = this.searchForm.get('search')?.value;
    const filter = this.searchForm.get('filter')?.value;
    if (content !== null && content !== undefined) {
      const searchString = content.toString();
      if (filter !== null && filter !== undefined) {
        const filterString = filter.toString();
        this.postService.recherchePost(searchString, filterString).subscribe(posts => {
          this.posts = posts;
        });
      } else {
        this.postService.recherchePost(searchString, 'date').subscribe(posts => {
          this.posts = posts;
        });
      }
    }
  }




  
  
}


