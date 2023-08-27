import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})

export class FAQService {

  constructor(private http: HttpClient) { }

  getFAQs(){
    return this.http.get<any>(`${environment.apiUrl}faq/allfaq`); 
  }

  getFAQById(id: any){
    return this.http.get<any>(`${environment.apiUrl}faq/getfaq/${id}`); 
  }

  createFAQ( faq:any){
    return this.http.post(`${environment.apiUrl}faq/add/`, faq); 
  }

  updateFAQ(id: any, faq: any){
    return this.http.put(`${environment.apiUrl}faq/update/${id}`, faq); 
  }

  deleteFAQ(id: any){
    return this.http.delete(`${environment.apiUrl}faq/delete/${id}`); 
  }

  up(faqid:any){
    return this.http.post(`${environment.apiUrl}faq/upfaq/${faqid}`, null); 
  }

  down(faqid:any){
    return this.http.post(`${environment.apiUrl}faq/Downfaq/${faqid}`, null); 
  }

  getups(faqid:any){
    return this.http.get<any>(`${environment.apiUrl}faq/getupfaq/${faqid}`); 
  }

  getSubjects(){
    return this.http.get<any>(`${environment.apiUrl}faq/getallsubject/`); 
  }
  
}