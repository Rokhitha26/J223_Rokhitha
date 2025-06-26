package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookId;
	private int isbn;
	private String author;
	private String title;
	private String year;
	
}
