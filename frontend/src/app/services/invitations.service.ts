import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { Invitation } from '../models/invitation';



@Injectable({
  providedIn: 'root'
})
export class InvitationsService {
  private apiKey = 'your-jotform-api-key';
  private apiUrl = 'https://api.jotform.com';
  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/invitation";
  getSpeakers(){
    return this.http.get<User[]>(this.url+'/getUserSpeakers');
  }
  getStudents(){
    return this.http.get<User[]>(this.url+'/getUsersStudents');
  }
  inviterSpeakers(evenId: number, userId: number): Observable<Invitation> {
    const params = new HttpParams()
      .set('even_id', evenId.toString())
      .set('user_id', userId.toString());

    return this.http.post<Invitation>(this.url + '/inviterSpeaker', params);
  }
  inviterStudents(evenId: number, niveau:string): Observable<Invitation[]> {
    const params = new HttpParams()
      .set('event_id', evenId.toString())
      .set('niveau',niveau);

    return this.http.get<Invitation[]>(this.url + '/inviterStudents',{params} );
  }
  
  onPresenceChanged(id: number,present:boolean): Observable<any> {
    const url = `${this.url}/presence?id=${id}&present=${present}`;
    return this.http.put(url, null);
    }

    getInvitationsByEvent(name:string): Observable<Invitation[]> {
      const url = `${this.url}/getInvitations?name=${name}`;
      return this.http.get<Invitation[]>(url);
    }
    getForms() {
      const url = `${this.apiUrl}/user/forms?apiKey=${this.apiKey}`;
      return this.http.get(url);
    }
  }

