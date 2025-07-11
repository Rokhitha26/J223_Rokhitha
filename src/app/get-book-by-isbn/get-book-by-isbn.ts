import { Component, OnInit } from '@angular/core';
import { BookService } from '../bookservice';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-get-book-by-isbn',
  imports:[CommonModule,FormsModule,RouterModule],
  templateUrl: './get-book-by-isbn.html',
    styleUrls: ['./get-book-by-isbn.css']
})
export class GetBookByIsbn {
  isbnInput: number = 0; // For input
  book: any = null;

  constructor(private route: ActivatedRoute, private bookService: BookService) {
    const param = this.route.snapshot.paramMap.get('isbn');
    if (param) {
      const isbn = Number(param);
      this.fetchBook(isbn);
    }
  }

  fetchBook(isbn: number) {
    this.bookService.getBookByIsbn(isbn).subscribe({
      next: (data) => this.book = data,
      error: (err) => {
        console.error('Error fetching book:', err);
        this.book = null;
      }
    });
  }

  onSearch() {
    if (this.isbnInput) {
      this.fetchBook(this.isbnInput);
    }
  }
}
