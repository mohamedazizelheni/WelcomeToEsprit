import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../models/question';


@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/question";

  createNewQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(this.url+'/createNewQuestion', question);
  }
  deleteQuestions(id:number){
    return this.http.delete(this.url+`/deletequestion/${id}`);
  }
  getQuetions(){
    return this.http.get<Question[]>(this.url+'/getQuestion');
  }
  getQuestionById(id:number){
    return this.http.get<Question>(this.url+`/getQuestionById/${id}`);

  }
 
  
}
