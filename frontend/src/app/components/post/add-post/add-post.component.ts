import { Component, OnInit, ViewChild, ElementRef  } from '@angular/core';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/postservice/post.service';
import { ToastrService } from 'ngx-toastr';
import { Message } from 'primeng/api';
import { User } from 'src/app/models/user';
declare var webkitSpeechRecognition: any;
@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit{
  
  newPost: Post = new Post();
  user!:User;
  messages!: Message[];
  recognition!: any;

  constructor(private postService: PostService,private toastr: ToastrService) { }

  ngOnInit() {
  }
  
  onSubmit() {
    console.log('Post created successfully', this.newPost);
    this.postService.addPost(this.newPost.text).subscribe((createdPost) => {
    
     

      this.newPost.text = ''; // réinitialiser le champ de texte
      this.messages = [{ severity: 'success', summary: 'Success', detail: 'Publication ajoutée avec succès' }];
      setTimeout(() => {
        location.reload();
      }, 700); 
      this.toastr.success('Publication ajoutée avec succès');
    },
    (error) => {
      console.error('Failed to create post', error);
    }
  );
    this.newPost = new Post();
  }

  
  recognizing: boolean = false;

  
  startRecognition() {
    this.recognizing  = true;

    const recognition = new webkitSpeechRecognition();
    recognition.lang = 'fr-FR';
    recognition.interimResults = false;
    recognition.maxAlternatives = 1;
    recognition.start();
    recognition.onresult = (event:any) => {
      const result = event.results[0][0].transcript;
      this.newPost.text = result;
      this.recognizing = false;
      console.log(result)
    };
  
  }

}
