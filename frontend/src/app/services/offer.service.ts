import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Offer } from '../models/offer';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/offer";
  getAlloffers(){
    return this.http.get<Offer[]>(this.url+'/getOffers');
  }
  getOfferById(id:number){
    return this.http.get<Offer>(this.url+`/getOfferById/${id}/admin`);

  }
  addOffer(offer: Offer){
    return this.http.post<Offer>(this.url+'/createNewOffer/admin', offer);
  }
  updateOffer(offer:Offer){
    return this.http.put<Offer>(this.url+'/updateOffer/admin',offer);
  }
  deleteOffer(id:number){
    return this.http.delete(this.url+'/deleteOffer'+`/${id}`+'/admin');
  }
}
