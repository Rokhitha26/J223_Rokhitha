package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookData;

@Repository
public interface BookDataRepo extends JpaRepository<BookData, Integer> {

	Optional<BookData> findByIsbn(int isbn);
	   void deleteByIsbn(int isbn);
}
