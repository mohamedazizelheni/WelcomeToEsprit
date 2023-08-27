import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Question } from 'src/app/models/question';
import { QuestionService } from 'src/app/services/question.service';


@Component({
  selector: 'app-show-question',
  templateUrl: './show-question.component.html',
  styleUrls: ['./show-question.component.css']
})
export class ShowQuestionComponent {
  constructor(private questionService:QuestionService,private route: ActivatedRoute){}
  question=new Question();
  ngOnInit() {  
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.getQuestionById(id);
    });
  }
  

  getQuestionById(id: number) {
    this.questionService.getQuestionById(id).subscribe(data => {this.question = data;});
  }

}
