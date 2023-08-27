import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/user";

  getUserbyMail(mail:string){
    return this.http.get<User>(this.url+`/getUserbyMail/${mail}`);
  }
  getUserById(id:number){
    return this.http.get<User>(this.url+`/getUserById/${id}`);
  }
  getUsers(){
    return this.http.get<User[]>(this.url+`/getAllUsers/admin`);
  }
  
  addUser(cin:string,
    firstName:string,
    lastName:string,
    gender:string,
    image:File,
    image0:string,
    birthDate:string,
    address:string,
    phone:string,
    mail:string,
    password:string,
    bacSection:string,
    educationLevel:string,
    speciality:string): Observable<any> {

    const formData: FormData = new FormData();

    formData.append('cin', cin);
    formData.append('firstName', firstName);
    formData.append('lastName', lastName);
    formData.append('gender', gender);
    formData.append('image', image);
    formData.append('image0', image0);


    const dateObj1 = new Date(birthDate);
    const formattedBirthDate = `${dateObj1.getFullYear()}/${(dateObj1.getMonth()+1).toString().padStart(2, '0')}/${dateObj1.getDate().toString().padStart(2, '0')}`;
    formData.append('birthDate', formattedBirthDate);

    formData.append('address', address);
    formData.append('phone', phone);
    formData.append('mail', mail);
    formData.append('password', password);
    formData.append('bacSection', bacSection);
    formData.append('educationLevel', educationLevel);
    formData.append('speciality', speciality);

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    return this.http.post(this.url + '/addUser', formData, { headers: headers });
}

updateUser(
  id: number,
  cin: string,
  firstName: string,
  lastName: string,
  gender: string,
  image: File,
  image0:string,
  birthDate:string,
  address: string,
  phone: string,
  mail: string,
  Department: string,
  HiringDate: string,
  evaluator: boolean,
  jury: boolean,
  speaker: boolean,
  bacSection: string,
  educationLevel: string,
  speciality: string,
  classe: string,
  role: string): Observable<any> {

const formData: FormData = new FormData();
formData.append('id', id.toString());
if (cin) formData.append('cin', cin);
if (firstName) formData.append('firstName', firstName);
if (lastName) formData.append('lastName', lastName);
if (gender) formData.append('gender', gender);
if (image) formData.append('image', image);
formData.append('image0', image0);

if (birthDate){
    const dateObj1 = new Date(birthDate);
    const formattedBirthDate = `${dateObj1.getFullYear()}/${(dateObj1.getMonth()+1).toString().padStart(2, '0')}/${dateObj1.getDate().toString().padStart(2, '0')}`;
    formData.append('birthDate', formattedBirthDate);
}
if (address) formData.append('address', address);
if (phone) formData.append('phone', phone);
if (mail) formData.append('mail', mail);
if (Department) formData.append('Department', Department);
if (HiringDate){
  const dateObj2 = new Date(HiringDate);
  const formattedHiringDate = `${dateObj2.getFullYear()}/${(dateObj2.getMonth()+1).toString().padStart(2, '0')}/${dateObj2.getDate().toString().padStart(2, '0')}`;
  formData.append('HiringDate', formattedHiringDate);
}

formData.append('evaluator', evaluator.toString());
formData.append('jury', jury.toString());
formData.append('speaker', speaker.toString());
if (bacSection) formData.append('bacSection', bacSection);
if (educationLevel) formData.append('educationLevel', educationLevel);
if (speciality) formData.append('speciality', speciality);
if (classe) formData.append('classe', classe);
if (role) formData.append('role', role);

const headers = new HttpHeaders();
headers.append('Content-Type', 'multipart/form-data');
headers.append('Accept', 'application/json');

return this.http.put(this.url + '/updateUser', formData, { headers: headers });
}

updateUserRole(id: number, role: string) {
  const url = `${this.url}/updateUserRole/${id}/admin?role=${role}`;
  return this.http.put(url,{});
}
deleteUserById(id:number){
  return this.http.delete(this.url+`/deleteUser/${id}`);
}}

