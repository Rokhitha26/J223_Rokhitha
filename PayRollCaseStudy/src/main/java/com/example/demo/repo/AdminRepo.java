package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;
import com.example.demo.model.employeeProfile;

@Repository
public interface AdminRepo extends JpaRepository<employeeProfile,Integer>{

	List<employeeProfile> findAllByFirstNameIgnoreCase(String firstName);


}
