// src/app.ts
import { Component } from '@angular/core';
import { LoginRegisterService } from './login-register';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {
  title = 'books-app';
  constructor(public authService: LoginRegisterService) {}
}