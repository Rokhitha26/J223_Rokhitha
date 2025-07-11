package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Retrieve all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Retrieve a single book by ISBN
    public Book getBookByIsbn(int isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
    }

    // Add a new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Update an existing book
    public Book updateBook(int isbn, Book updatedBook) {
        Book existingBook = bookRepository.findById(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Book not found with ISBN: " + isbn));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());

        return bookRepository.save(existingBook);
    }

    // Delete a book by ISBN
    public void deleteBookByIsbn(int isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new ResourceNotFoundException("Cannot delete. Book not found with ISBN: " + isbn);
        }
        bookRepository.deleteById(isbn);
    }
}
