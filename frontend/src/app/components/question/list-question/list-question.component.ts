import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/models/question';
import { AuthService } from 'src/app/services/auth.service';
import { QuestionService } from 'src/app/services/question.service';
import { Observable } from 'rxjs'

@Component({
  selector: 'app-list-question',
  templateUrl: './list-question.component.html',
  styleUrls: ['./list-question.component.css']
})
export class ListQuestionComponent {
  quetionList!:Question[];
  role=this.authService.getRole();
  @ViewChild('myModal', {static: false}) myModal!: ElementRef;
  constructor(private router:Router,private questionService:QuestionService,private authService: AuthService){}
  
  ngOnInit(): void {
    this.getQuestions();
  

  }
  getQuestions(){
    this.questionService.getQuetions().subscribe(
      data =>  this.quetionList=data 
      );
  }
  deleteQuestions(id:number){
    this.questionService.deleteQuestions(id).subscribe(
      ()=> {
        this.getQuestions();
        alert('Deleted!');
      }
    );
  }
  

}
