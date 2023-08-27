import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/services/postservice/post.service';

@Component({
  selector: 'app-post-rating',
  templateUrl: './post-rating.component.html',
  styleUrls: ['./post-rating.component.css']
})
export class PostRatingComponent implements OnInit {
  currentRating!: number;

  constructor(private postService: PostService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  
}
