import { Component } from '@angular/core';
import { Question } from 'src/app/models/question';
import { QuestionService } from 'src/app/services//question.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent {
  question!:Question;
  constructor(private questionService:QuestionService, private router: Router){}
  createQuestion(F:NgForm){
  this.question = new Question();
  this.question.question= F.controls['question'].value;
  this.question.type= F.controls['type'].value;   

  this.question.true1= F.controls['true1'].value; 
  this.question.false1= F.controls['false1'].value;   
  this.question.false2= F.controls['false2'].value; 
  this.question.false3= F.controls['false3'].value;
  this.questionService.createNewQuestion(this.question).subscribe( (Response :Question) =>{
    window.location.reload();
  });
}
}