package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.employeeProfile;
import com.example.demo.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping(value="/addEmployeeToMain")
	public String addEmployeeToMain(@RequestBody employeeProfile e) {
		return adminService.addEmployeeToMain(e);
	}
	@PutMapping(value="/createEmployeeCredentials/{uId}")
	public String createEmployeeCredentials(
	        @PathVariable int uId,
	        @RequestParam String email,
	        @RequestParam String password,
	        @RequestParam Role role) {
	    
	    return adminService.CreateEmployeeCredentials(uId, email, password, role);
	}

	@GetMapping(value="/searchByEmployeeId/{empId}")
	public employeeProfile searchByEmployeeId(@PathVariable int empId) {
		return adminService.searchByEmployeeId(empId);
	}
	@GetMapping(value="/searchByFirstName/{empName}")
	public List<employeeProfile> searchByFirstName(@PathVariable String empName) {
		return adminService.searchByFirstName(empName);
	}
	 @DeleteMapping("/deleteEmployee/{empId}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable int empId) {
	        String message = adminService.deleteEmployee(empId);
	        return ResponseEntity.ok(message);
	    }


}
