import { Injectable } from '@angular/core';
import {AdmissionCandidacy } from '../models/admission-candidacy';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AdmissioncandidacyService {
  
  url="http://localhost:1111/welcometoesprit/api/admission";

  constructor(private http: HttpClient) { }
  addNewAdmissionCandidacy(
    bac: boolean,
    docBacDiploma: File,
    bacMoy: number,
    docBacReleve: File,
    bacYear: string,
    bacEstablishment: string,
    bacGovernorate: string,
    moy1: number,
    docReleve1: File,
    moy2: number,
    docReleve2: File,
    moy3: number,
    docReleve3: File,
    moy4: number,
    docReleve4: File,
    diploma: boolean,
    docDiploma: File,
    level: string,
    speciality:string,
    day:string,
    hour:string

    ): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('bac', bac.toString());
    if (docBacDiploma) formData.append('docBacDiploma', docBacDiploma);
    if (bacMoy) formData.append('bacMoy', bacMoy.toString());
    if (docBacReleve) formData.append('docBacReleve', docBacReleve);
    formData.append('bacYear', bacYear);
    formData.append('bacEstablishment', bacEstablishment);
    formData.append('bacGovernorate', bacGovernorate);
    if (moy1)  formData.append('moy1', moy1.toString());
    if (docReleve1) formData.append('docReleve1', docReleve1);
    if (moy2) formData.append('moy2', moy2.toString());
    if (docReleve2) formData.append('docReleve2', docReleve2);
    if (moy3)  formData.append('moy3', moy3.toString());
    if (docReleve3) formData.append('docReleve3', docReleve3);
    if (moy4) formData.append('moy4', moy4.toString());
    if (docReleve4) formData.append('docReleve4', docReleve4);
    if (diploma) formData.append('diploma', diploma.toString());
    if (docDiploma) formData.append('docDiploma', docDiploma);
    formData.append('level', level);
    formData.append('speciality', speciality);
    formData.append('day', day);
    formData.append('hour', hour);

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    return this.http.post(this.url + '/createNewAdmissionCandidacy', formData, { headers: headers });
  }
  getAdmissionCandidacyById(id: number) {
    return this.http.get<AdmissionCandidacy>(this.url+`/getAdmissionCandidacyById/${id}`);
  }

  getAdmissionCandidacy(){
    return this.http.get<AdmissionCandidacy[]>(this.url+'/getAdmissionCandidacy');
  }
 

  getSpecialityTestAverages(): Observable<Map<string, number>> {
    return this.http.get<Map<string, number>>(`${this.url}/speciality-test-average`);
  }

  deleteAdmissionCandidacy(id:number){
    return this.http.delete(this.url+`/delete-candidature/${id}`);

  }

  getAdmissionCandidacyByIdCandidate(id:number){

    return this.http.get<AdmissionCandidacy>(this.url+`/getAdmissionCandidacyByIdCandidate/${id}`);
  
  }  


  updateAdmissionCandidacyStatus(id: number, status: string) {
    const url = this.url + `/updateAdmissionCandidacyStatus?id=${id}&status=${status}`;
    return this.http.put(url, null);
  }
  
  updateAdmissionCandidacyScore(id: number, score: number) {
    const url = this.url + `/updateAdmissionCandidacyScore?id=${id}&score=${score}`;
    return this.http.put(url, null);
  }
}
