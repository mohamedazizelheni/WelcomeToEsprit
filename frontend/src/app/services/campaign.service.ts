import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})

export class CampaignService {

  constructor(private http: HttpClient) { }

  getCampaigns(){
    return this.http.get<any>(`${environment.apiUrl}socity/getall`); 
  }

  getCampaignById(id: any){
    return this.http.get<any>(`${environment.apiUrl}socity/getsocitybyid/${id}`); 
  }

  createCampaign( faq:any){
    return this.http.post(`${environment.apiUrl}socity/add/`, faq); 
  }

  updateCampaign(id: any, faq: any){
    return this.http.put(`${environment.apiUrl}socity/update/${id}`, faq); 
  }

  deleteCampaign(id: any){
    return this.http.delete(`${environment.apiUrl}socity/delete/${id}`); 
  }
}