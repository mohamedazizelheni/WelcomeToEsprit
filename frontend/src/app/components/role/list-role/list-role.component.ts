import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from 'src/app/models/role';
import { AuthService } from 'src/app/services/auth.service';
import { RoleService } from 'src/app/services/role.service';

@Component({
  selector: 'app-list-role',
  templateUrl: './list-role.component.html',
  styleUrls: ['./list-role.component.css']
})
export class ListRoleComponent {
  roleList!:Role[];
  role!:string;
  constructor(private router:Router,private roleSerssvice:RoleService,private authService: AuthService){}
  ngOnInit(): void {
    this.role=this.authService.getRole();
    this.getRoles();
  }
  getRoles(){
    this.roleSerssvice.getAllRoles().subscribe(
      data =>  this.roleList=data 
      );
  }
}
