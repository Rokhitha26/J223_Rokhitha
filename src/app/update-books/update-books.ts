import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BookService, Book } from '../bookservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-book',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './update-books.html',
  styleUrls: ['./update-books.css']

})
export class UpdateBooks {
  isbnInput: number | null = null;
  isbn!: number;
  book: Book | null = null;
  notFound: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService
  ) {
    const paramIsbn = this.route.snapshot.paramMap.get('isbn');
    if (paramIsbn) {
      this.isbn = +paramIsbn;
      this.getBook();
    }
  }

  onSearch() {
    if (this.isbnInput) {
      this.router.navigate(['/update-book', this.isbnInput]).then(() => {
        this.isbn = this.isbnInput!;
        this.getBook();
      });
    }
  }

  getBook() {
    this.bookService.getBookByIsbn(this.isbn).subscribe({
      next: (data) => {
        this.book = data;
        this.notFound = false;
      },
      error: () => {
        this.book = null;
        this.notFound = true;
      }
    });
  }

  updateBook() {
    if (!this.book) return;
    this.bookService.updateBook(this.isbn, this.book).subscribe({
      next: () => {
        alert('Book updated successfully!');
        this.router.navigate(['/all-books']);
      },
      error: (err) => {
        console.error('Update failed:', err);
        alert('Failed to update book.');
      }
    });
  }
}
