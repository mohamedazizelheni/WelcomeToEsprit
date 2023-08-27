import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AdmissionCandidacy } from 'src/app/models/admission-candidacy';
import { User } from 'src/app/models/user';
import { AdmissioncandidacyService } from 'src/app/services/admissioncandidacy.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})
export class HeaderUserComponent{

  constructor(private authService: AuthService, private router: Router, private cookieService: CookieService, private userService: UserService,private admissionCandidacyService:AdmissioncandidacyService) {}
  logedIn=this.authService.isLoggedIn();
  mail=this.authService.getSubject();
  user!:User;
  admissionCandidacy!:AdmissionCandidacy;
  ngOnInit() {  
    if (this.mail)
    this.userService.getUserbyMail(this.mail)
    .subscribe((user) => {
      this.user = user;
      this.getAdmissionCandidacyById(user.id)
    });
    };

  logout(){
    this.authService.logout().subscribe(() => {
      this.cookieService.delete('token');
      this.cookieService.deleteAll();
      window.location.href = 'http://localhost:4200/login';
    });
  }
  
  getAdmissionCandidacyById(id: number) {
    this.admissionCandidacyService.getAdmissionCandidacyByIdCandidate(id).subscribe(
      data => {
        this. admissionCandidacy   = data;
    });
  }
}
