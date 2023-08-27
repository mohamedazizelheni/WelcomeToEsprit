import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role } from '../models/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }
  url="http://localhost:1111/welcometoesprit/api/role";
  getAllRoles(){
    return this.http.get<Role[]>(this.url+'/getallRoles');
  }
  addRole(role: string){
    return this.http.post<Role>(this.url+'/add', role);
  }
  
}
