// src/app/book.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { LoginRegisterService } from './login-register';

export interface Book {
  isbn: number;  // Changed from string to number to match backend
  title: string;
  author: string;
  publicationYear?: number;  // Optional to match both getAll and getByIsbn responses
}

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:8080/books';

  constructor(
    private http: HttpClient,
    private loginRegisterService: LoginRegisterService
  ) {}

  private getAuthHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.loginRegisterService.token}`,
      'Content-Type': 'application/json'
    });
  }

  // Get all books
  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/all`, { 
      headers: this.getAuthHeaders() 
    });
  }

  // Get single book by ISBN
  getBookByIsbn(isbn: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${isbn}`, { 
      headers: this.getAuthHeaders() 
    }).pipe(
      catchError(error => {
        return throwError(() => new Error('Book not found'));
      })
    );
  }

  // Add new book
  addBook(book: Omit<Book, 'isbn'>): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, book, { 
      headers: this.getAuthHeaders() 
    });
  }

updateBook(isbn: number, bookData: Partial<Book>): Observable<Book> {
  return this.http.put<Book>(`${this.apiUrl}/${isbn}`, bookData, {
    headers: this.getAuthHeaders()
  });
}


  // Delete book
  deleteBook(isbn: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${isbn}`, { 
      headers: this.getAuthHeaders() 
    });
  }
}