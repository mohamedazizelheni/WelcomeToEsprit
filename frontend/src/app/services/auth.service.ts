import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  getUserId() {
    throw new Error('Method not implemented.');
  }
  constructor(private http: HttpClient, private cookieService: CookieService) { }

  login(mail: string, password: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = JSON.stringify({ mail: mail, password: password });
    return this.http.post('http://localhost:1111/welcometoesprit/api/user/login', body, { headers, observe: 'response' });
  }

  setToken(token: string): void {
    this.cookieService.set('token', token);
  }

  getToken(): string {
    return this.cookieService.get('token');
  }

  isLoggedIn(): boolean {
    return this.getToken() !== '';
  }

  logout(): Observable<any> {
    return this.http.post('http://localhost:1111/welcometoesprit/api/user/logout', {});
  }

  getSubject(): string {
    const token = this.getToken();
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.sub;
    } else {
      return '';
    }
  }
  
  getRole(): string {
    const token = this.getToken();
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.role;
    } else {
      return '';
    }
  }
  
}
