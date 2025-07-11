// src/register/register.ts
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginRegisterService } from '../login-register';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
    standalone: true,
  imports:[FormsModule,CommonModule,RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  user = {
    username: '',
    password: ''
  };
  errorMessage = '';
  successMessage = '';

  constructor(
    private authService: LoginRegisterService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.errorMessage = '';
    this.successMessage = '';
    
    this.authService.register(this.user).subscribe({
      next: () => {
        this.successMessage = 'Registration successful! You are now logged in.';
        setTimeout(() => {
          this.router.navigate(['/']); // Redirect to home after successful registration
        }, 1500);
      },
      error: (err) => {
        this.errorMessage = 'Registration failed. Username might be taken.';
        console.error('Registration error:', err);
      }
    });
  }
}