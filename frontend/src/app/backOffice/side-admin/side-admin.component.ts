import { Component } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-side-admin',
  templateUrl: './side-admin.component.html',
  styleUrls: ['./side-admin.component.css']
})
export class SideAdminComponent {
  constructor(private authService:AuthService,private userService:UserService){}
  mail=this.authService.getSubject();
  user!:User;
  ngOnInit() {  
    if (this.mail)
    this.userService.getUserbyMail(this.mail)
    .subscribe((user) => {
      this.user = user;
    });
    };
}
