package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BookData;
import com.example.demo.repo.BookDataRepo;

@Service
public class CentralService {

	@Autowired
	BookDataRepo BRepo;
	
	public List<BookData>RetrieveBookData(){
		
		List<BookData>result=BRepo.findAll();
	 if(result.isEmpty()) {
		 throw new ResourceNotFoundException("No Data Found");
	 }
		return result;
	}
	
	public BookData RetrieveByIsbn(int isbn) {
	    Optional<BookData> result = BRepo.findByIsbn(isbn);
	    
	    return result.orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
	}
	 public BookData addBook(BookData book) {
	        return BRepo.save(book);
	    }

	 public BookData updateBook(BookData book) {
	        Optional<BookData> existing = BRepo.findByIsbn(book.getIsbn());
	        if (existing != null) {
	            book.setBookId(existing.get().getBookId()); 
	            return BRepo.save(book);
	        }
	        return null; 
	    }
	 public void deleteBookByIsbn(int isbn) {
	        BRepo.deleteByIsbn(isbn);
	    }
}
