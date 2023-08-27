import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Achievement } from '../models/achievement';

@Injectable({
  providedIn: 'root'
})
export class AchievementService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/achievement";

  addAchievement(evenId:number,name:string,video:File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('id', evenId.toString());
    formData.append('name', name);
    formData.append('video', video);
   

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    

    return this.http.post(this.url + `/addAchievement`, formData, { headers: headers });  
  }
  getAllAchievement(){
    return this.http.get<Achievement[]>(this.url+'/getAllAchievement');
  }
  getAchievementById(id:number){
    return this.http.get<Achievement>(this.url+`/getAchievementById/${id}`);

  }
  archivedAchievement(name: string): Observable<any> {
    const url = this.url + `/archived`;
    return this.http.put(url, { name });
}

findAchievemntsByEventId(id:number): Observable<any>{
  return this.http.get<Achievement[]>(this.url+`/findAchievemntsByEventId/${id}`);
}
  
  

}
