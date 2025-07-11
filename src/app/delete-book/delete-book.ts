// src/app/delete-book/delete-book.ts
import { Component } from '@angular/core';
import { BookService } from '../bookservice';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-delete-book',
  imports:[FormsModule,RouterModule,CommonModule],
  templateUrl: './delete-book.html',
    styleUrls: ['./delete-book.css']
})
export class DeleteBook {
  isbn: number | null = null;

  constructor(private bookService: BookService, private router: Router) {}

  onDelete(): void {
    if (!this.isbn) {
      alert("Please enter a valid ISBN.");
      return;
    }

    this.bookService.deleteBook(this.isbn).subscribe({
      next: () => {
        alert(`Book with ISBN ${this.isbn} deleted.`);
        this.router.navigate(['/all-books']);
      },
      error: err => {
        console.error('Error deleting book:', err);
        alert(err?.error?.message || 'Failed to delete book.');
      }
    });
  }
}
