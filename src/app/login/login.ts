// src/login/login.ts
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginRegisterService } from '../login-register';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
    standalone: true,
  imports:[FormsModule,CommonModule,RouterModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  credentials = {
    username: '',
    password: ''
  };
  errorMessage = '';

  constructor(
    private authService: LoginRegisterService,
    private router: Router
  ) {}

// src/app/login/login.ts
onSubmit(): void {
  this.errorMessage = '';
  this.authService.login(this.credentials).subscribe({
    next: () => {
      this.router.navigate(['/']); // Redirects to home with default child
    },
    error: (err) => {
      this.errorMessage = 'Login failed. Please check your credentials.';
    }
  });
}
}