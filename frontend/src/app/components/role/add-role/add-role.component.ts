import { Component, OnInit } from '@angular/core';
import { Role } from 'src/app/models/role';
import { User } from 'src/app/models/user';
import { RoleService } from 'src/app/services/role.service';

@Component({
  selector: 'app-add-role',
  templateUrl: './add-role.component.html',
  styleUrls: ['./add-role.component.css']
})
export class AddRoleComponent implements OnInit {

  newRole: Role = new Role;

  constructor(private roleService: RoleService) { }
  ngOnInit(): void {

  }

  onSubmit() {
    this.roleService.addRole(this.newRole.role).subscribe((createdPost) => {

      this.newRole.role = ''; // réinitialiser le champ de texte



    },
      (error) => {
        console.error('Failed to create ROLE', error);
      }
    );
    this.newRole = new Role();
  }
}