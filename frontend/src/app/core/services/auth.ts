import { Service, inject } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Register} from '../../features/auth/register/register';

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  accessToken: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

@Service()
export class Auth {
  private http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/v1/auth';

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post<LoginResponse>(this.apiUrl + '/login', loginRequest)
      .pipe(tap(response => {
        localStorage.setItem("access_token", response.accessToken);
      }));
  }

  registerBuyer(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(this.apiUrl + '/register/buyer', registerRequest);
  }

  logout():void {
    localStorage.removeItem("access_token");
  }

  getToken(): string | null {
    return localStorage.getItem("access_token");
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
