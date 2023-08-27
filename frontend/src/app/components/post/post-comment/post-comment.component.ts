import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { PostService } from 'src/app/services/postservice/post.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit{
  post: Post = new Post();
  comments: Comment[] = [];

  constructor(private postService: PostService, private route: ActivatedRoute) { }
  
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.postService.getPostById(+id).subscribe(post => {
        this.post = post;
        this.postService.getCommentsByPostId(post.id).subscribe(comments => {
          this.comments = comments;
        });
      });
    }
    throw new Error('Method not implemented.');
  }

}
