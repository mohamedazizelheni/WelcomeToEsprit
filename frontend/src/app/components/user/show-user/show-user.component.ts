import { Component, Input } from '@angular/core';
import { EmailValidator, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Role } from 'src/app/models/role';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RoleService } from 'src/app/services/role.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-show-user',
  templateUrl: './show-user.component.html',
  styleUrls: ['./show-user.component.css']
})
export class ShowUserComponent {
  user!: User;
  id!: number;

  imageSrc!: string;
  role!:string;
  roleNames!: string[] ;
  role1!:string;
  constructor(private userService: UserService, private route: ActivatedRoute,private authService: AuthService, private roleService:RoleService) {}

  ngOnInit() {
   
      // Get user by id from route parameter
      this.route.paramMap.subscribe(params => {
        const id = Number(params.get('id'));
        this.userService.getUserById(id).subscribe(user => {
          this.user = user;
          this.imageSrc = `data:image/jpeg;base64,${user.image}`;
        });
      });
  
  this.role=this.authService.getRole();
  if(this.role=='ADMIN')
  this.roleService.getAllRoles().subscribe(
    (roles: Role[]) => {
      this.roleNames = roles.map(role => role.role);
    }
  );  

}
updateUserRole(F:NgForm){
  if(F.controls['role2'])
  this.role1 = F.controls['role2'].value;
  this.userService.updateUserRole(this.user.id, this.role1).subscribe(() => {
    window.location.href = `http://localhost:4200/admin/user/show/${this.user.id}`;
  });
}
}
