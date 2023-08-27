import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})

export class AdsService {

  constructor(private http: HttpClient) { }

  getAds(){
    return this.http.get<any>(`${environment.apiUrl}ads/allads`); 
  }
  addVue(id:any){
    return this.http.put<any>(`${environment.apiUrl}ads/NBvu/`+id , {}); 
  }

  getAdById(id: any){
    return this.http.get<any>(`${environment.apiUrl}ads/getads/${id}`); 
  }

  createAd(ad:any){
    return this.http.post(`${environment.apiUrl}ads/add/`, ad); 
  }

  updateAd(id: any, ad: any){
    return this.http.put(`${environment.apiUrl}ads/update/${id}`, ad); 
  }

  deleteAd(id: any){
    return this.http.delete(`${environment.apiUrl}ads/${id}`); 
  }

  getPDF(name:any){
    return this.http.get<any>(`${environment.apiUrl}faq/print/${name}`); 
  }

}