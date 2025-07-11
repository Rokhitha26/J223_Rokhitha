// src/app/add-book/add-book.ts
import { Component } from '@angular/core';
import { BookService, Book } from '../bookservice';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-book',
  imports:[FormsModule,CommonModule,RouterModule],
  templateUrl: './add-book.html',
  styleUrls: ['./add-book.css']
})
export class AddBook {
  book: Book = {
    isbn: 0,
    title: '',
    author: '',
    // Add other fields as needed
  };

  constructor(private bookService: BookService, private router: Router) {}

  onSubmit(): void {
    this.bookService.addBook(this.book).subscribe({
      next: () => {
        alert('Book added successfully');
        this.router.navigate(['/all-books']);
      },
      error: err => {
        console.error('Error adding book:', err);
        alert('Failed to add book');
      }
    });
  }
}
