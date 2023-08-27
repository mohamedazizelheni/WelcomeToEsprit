import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Event } from 'src/app/models/event';


@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/event";
  getEvents(){
    return this.http.get<Event[]>(this.url+'/getEvents');
  }

  addEvent(name:string,space:string, date:string,planning:File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('name', name);
    formData.append('space', space);
    formData.append('date', date);
    formData.append('planning', planning);

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    return this.http.post(this.url + '/ajouterEvent', formData, { headers: headers });  }

  updateEvent(event:Event){
    return this.http.put<Event>(this.url+'/Update',event);
  }
  getEventById(id:number){
    return this.http.get<Event>(this.url+`/getEventById/${id}`);

  }
  deleteEvents(id:number){
    return this.http.delete(this.url+`/Delete?event_id=${id}`);
  }
}
