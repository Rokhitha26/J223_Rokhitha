package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public ResponseEntity<String> login(String email, String password) {
	    Optional<Users> userOpt = userRepo.findByEmailAndPassword(email, password);

	    if (userOpt.isPresent()) {
	        return ResponseEntity.ok("Login successful. Welcome, " + userOpt.get().getFirstName() + "!");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	               .body("Invalid credentials. Please try again.");
	    }
	}

}
