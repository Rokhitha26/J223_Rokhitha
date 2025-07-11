// src/login-register.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {
  private apiUrl = 'http://localhost:8080/auth'; // Update with your backend URL
  private tokenSubject = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient, private router: Router) {
    // Initialize token from localStorage if available
    const storedToken = localStorage.getItem('authToken');
    if (storedToken) {
      this.tokenSubject.next(storedToken);
    }
  }

  get token(): string | null {
    return this.tokenSubject.value;
  }

  register(user: { username: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user).pipe(
      tap(() => {
        // After successful registration, automatically log the user in
        this.login(user).subscribe();
      })
    );
  }

  login(credentials: { username: string, password: string }): Observable<string> {
    return this.http.post(`${this.apiUrl}/login`, credentials, { responseType: 'text' }).pipe(
      tap(token => {
        this.storeToken(token);
      })
    );
  }

  logout(): void {
    this.clearToken();
    this.router.navigate(['/login']);
  }

  private storeToken(token: string): void {
    localStorage.setItem('authToken', token);
    this.tokenSubject.next(token);
  }

  private clearToken(): void {
    localStorage.removeItem('authToken');
    this.tokenSubject.next(null);
  }

  isAuthenticated(): boolean {
    return this.token !== null;
  }

  // This will be used later to add token to requests
  getAuthHeader(): { [header: string]: string } {
    return { 'Authorization': `Bearer ${this.token}` };
  }
}