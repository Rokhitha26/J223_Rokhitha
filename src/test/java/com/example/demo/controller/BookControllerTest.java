package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.BookService;
import com.example.demo.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class,
    excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = new Book(1, "Sample Title", "Sample Author", 2020);
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<Book> books = List.of(
            new Book(1, "Sample Title", "Sample Author", 2020),
            new Book(2, "Another Book", "Another Author", 2021)
        );

        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].isbn").value(1))
            .andExpect(jsonPath("$[1].title").value("Another Book"));
    }

    @Test
    void testGetBookByIsbn() throws Exception {
        Mockito.when(bookService.getBookByIsbn(1)).thenReturn(sampleBook);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Title"))
                .andExpect(jsonPath("$.isbn").value(1));
    }

    @Test
    void testAddBook() throws Exception {
        Book inputBook = new Book();
        inputBook.setTitle("Sample Title");
        inputBook.setAuthor("Sample Author");
        inputBook.setPublicationYear(2020);

        Mockito.when(bookService.addBook(any(Book.class))).thenReturn(sampleBook);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(1));
    }

    @Test
    void testUpdateBook() throws Exception {
        Book updatedBook = new Book(1, "Updated Title", "Updated Author", 2021);
        Mockito.when(bookService.updateBook(eq(1), any(Book.class)))
                .thenReturn(updatedBook);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.isbn").value(1));
    }

    @Test
    void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteBookByIsbn(1);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book with ISBN 1 deleted successfully."));
    }
}
