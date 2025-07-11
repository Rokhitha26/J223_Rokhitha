// src/app/get-all-books/get-all-books.ts
import { Component, OnInit } from '@angular/core';
import { BookService } from '../bookservice';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-all-books',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './get-all-books.html',
  styleUrls: ['./get-all-books.css']
})
export class GetAllBooks implements OnInit {
  books: any[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.isLoading = true;
    this.error = null;
    
    this.bookService.getAllBooks().subscribe({
      next: (books) => {
        this.books = books;
        this.isLoading = false;
      },
      error: (err) => {
        this.error = 'Failed to load books. Please try again later.';
        this.isLoading = false;
        console.error('Error loading books:', err);
      }
    });
  }
}