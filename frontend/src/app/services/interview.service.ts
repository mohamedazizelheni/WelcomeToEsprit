import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Interview } from '../models/interview';

@Injectable({
  providedIn: 'root'
})
export class InterviewService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/interview";
  getDays(k:number){
    return this.http.get<string[]>(this.url+`/getDays?k=${k}`);
  }
  getHours(date:string, k:number){
    return this.http.get<string[]>(this.url+`/getHours?date=${date}&k=${k}`);
  }
  getInterviewByOfferCandidacyId(id:number){
    return this.http.get<Interview>(this.url+`/getInterviewByOfferCandidacyId/${id}`);
  }
  getInterviewByAdmissionCandidacyId(id:number){
    return this.http.get<Interview>(this.url+`/getInterviewByAdmissionCandidacyId/${id}`);
  }
  getInterviewById(id:number){
    return this.http.get<Interview>(this.url+`/getInterviewById/${id}`);
  }
  getInterviewsByUserId(id:number){
    return this.http.get<Interview[]>(this.url+`/getInterviewsByUserId/${id}`);
  }

}
