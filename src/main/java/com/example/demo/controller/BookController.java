package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Retrieve all books
  
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Retrieve a single book by ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable int isbn) {
        Book book = bookService.getBookByIsbn(isbn);  // throws ResourceNotFoundException if not found
        return ResponseEntity.ok(book);
    }

    // Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }

    // Update an existing book by ISBN
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable int isbn, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(isbn, updatedBook);
        return ResponseEntity.ok(book);
    }

    // Delete a book by ISBN
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable int isbn) {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.ok(Map.of("message", "Book with ISBN " + isbn + " deleted successfully."));

    }
}
