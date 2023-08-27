import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OfferCandidacy } from '../models/offer-candidacy';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class OfferCandidacyService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/offerCandidacy";
  getOfferCandidaciesByOfferId(id:number){
    return this.http.get<OfferCandidacy[]>(this.url+`/getOfferCandidaciesByOfferId/${id}`);
  }
  getOfferCandidacyById(id:number){

    return this.http.get<OfferCandidacy>(this.url+`/getOfferCandidacyById/${id}`);

  }
  addOfferCandidacy(offerId: number, establishment: string, docDiploma: File, docCV: File, docLetter: File, day: string, hour: string): Observable<any> {

      const formData: FormData = new FormData();
      formData.append('offer_id', offerId.toString());
      formData.append('establishment', establishment);
      formData.append('docDiploma', docDiploma);
      formData.append('docCV', docCV);
      formData.append('docLetter', docLetter);
      formData.append('day', day);
      formData.append('hour', hour);
  
      const headers = new HttpHeaders();
      headers.append('Content-Type', 'multipart/form-data');
      headers.append('Accept', 'application/json');
  
      return this.http.post(this.url + '/createNewOfferCandidacy', formData, { headers: headers });
  }

  updateOfferCandidacy(id:number,offerCandidacy: OfferCandidacy){
    return this.http.put<OfferCandidacy>(this.url+`/updateOfferCandidacy/${id}`, offerCandidacy);
  }
  deleteOfferCandidacyById(id:number){
    return this.http.delete(this.url+'/deleteOfferCandidacyById'+`/${id}`);
  }
  updateOfferCandidacyStatus(id: number, status: string) {
    const url = this.url + `/updateOfferCandidacyStatus?id=${id}&status=${status}`;
    return this.http.put(url, null);
  }
  
  updateOfferCandidacyScore(id: number, score: number) {
    const url = this.url + `/updateOfferCandidacyScore?id=${id}&score=${score}`;
    return this.http.put(url, null);
  }
  
  getOfferCandidacyStatisticsByDate(id: number): Observable<Map<Date, number>> {
    const url = this.url + `/getOfferCandidacyStatisticsByDate/admin?id=${id}`;
    return this.http.get<Map<Date, number>>(url);
}

getOfferCandidaciesByIdCandidate(id:number){
  return this.http.get<OfferCandidacy[]>(this.url+`/getOfferCandidaciesByIdCandidate/${id}`);
}

getOfferCandidaciesByOfferIdAndUserId(offerId:number,userId:number){

  return this.http.get<OfferCandidacy>(this.url+`/getOfferCandidaciesByOfferIdAndUserId/${offerId}/${userId}`);

}

}
