import { Injectable } from '@angular/core';
import { Room } from '../models/room';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient ) {}
  url="http://localhost:1111/welcometoesprit/api/room";

  getRoomById(id:number){
    return this.http.get<Room>(this.url+`/getRoomById?id=${id}`);
  }
  
  addRoom(name:string,
    block:string,
    image:File): Observable<any> {
      const formData: FormData = new FormData();

    formData.append('name', name);
    formData.append('block', block);
    formData.append('file', image);
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    return this.http.post<Room>(this.url + '/createNewRoom', formData, { headers: headers });
  }
}
