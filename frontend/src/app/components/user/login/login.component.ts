import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  mail: string = '';
  password: string = '';

  constructor(private authService: AuthService, private cookieService: CookieService,private userService: UserService) {}
  ngOnInit(): void {  
    if(this.authService.isLoggedIn()){
      this.logout();
      this.cookieService.delete('token');
      this.cookieService.deleteAll();
    }
  }

  login() {
    this.authService.login(this.mail, this.password).subscribe(
      (response) => {
        const token = response.body.token;
        this.authService.setToken(token);
        //----------------ligne---------------
        localStorage.removeItem('tag')
        //----------------ligne---------------
        if(this.authService.getRole()=='ADMIN'){
        window.location.href = `http://localhost:4200/admin/post`;

        }
        else{
          window.location.href = `http://localhost:4200/post`;
          }

      }
    );
  }
  logout(){
    this.authService.logout().subscribe(() => {
      this.cookieService.delete('token');
      this.cookieService.deleteAll();
      window.location.href = 'http://localhost:4200/login';
    });
  }
}