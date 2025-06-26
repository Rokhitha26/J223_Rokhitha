package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BookData;
import com.example.demo.service.CentralService;

@RestController
public class CentralController {

	@Autowired
	 private CentralService CService;
	
	@GetMapping(value="/retrieveAllBook")
	public ResponseEntity<List<BookData>>RetrieveAllBooks(){
		List<BookData>result=CService.RetrieveBookData();
		 return ResponseEntity.ok(result);
	}
	@GetMapping(value="/retrieveByIsbn/{isbn}")
	public ResponseEntity<BookData>retrieveByIsbn(@PathVariable int isbn){
		return ResponseEntity.ok(CService.RetrieveByIsbn(isbn));
	}
	@PostMapping("/add")
    public ResponseEntity<BookData> addBook(@RequestBody BookData book) {
        return ResponseEntity.ok(CService.addBook(book));
    }
	@PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody BookData book) {
        BookData updated = CService.updateBook(book);
        if (updated != null)
            return ResponseEntity.ok("Updated Successfully");
        else
            return ResponseEntity.notFound().build();
    }
	 @DeleteMapping("/delete/{isbn}")
	    public ResponseEntity<String> deleteBook(@PathVariable int isbn) {
	        CService.deleteBookByIsbn(isbn);
	        return ResponseEntity.ok("Deleted successfully");
	    }
}
