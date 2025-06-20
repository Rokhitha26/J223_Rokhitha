package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.model.employeeProfile;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.PayrollRepo;
import com.example.demo.repo.UserRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo Adminrepo;
	
	@Autowired
	private UserRepo UserRepo;
	
	@Autowired
	private PayrollRepo payrollRepo;
	
	public String addEmployeeToMain(employeeProfile e) {
		employeeProfile savedProfile= Adminrepo.save(e);
		
		Users user= new Users();
		user.setUId(savedProfile.getEmpId());
		user.setFirstName(savedProfile.getFirstName());
		user.setLastName(savedProfile.getLastName());
		UserRepo.save(user);
		return "Employee added to company DB";
	}
	
	
	public String CreateEmployeeCredentials(int uId, String Email,String Password, Role role) {
		 Users user = UserRepo.findById(uId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + uId));

	        user.setEmail(Email);
	        user.setPassword(Password);
	        user.setRole(role);
	        UserRepo.save(user);
	        return "Employee Credentials created";
	    }
	
	public employeeProfile searchByEmployeeId(int empId) {
		return Adminrepo.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not found with ID: "+empId));
	}
	
	
	public List<employeeProfile> searchByFirstName(String empName) {
	    List<employeeProfile> result = Adminrepo.findAllByFirstNameIgnoreCase(empName);
	    if (result.isEmpty()) {
	        throw new ResourceNotFoundException("No employee found with name: " + empName);
	    }
	    return result;
	}
	
	
	public String deleteEmployee(int empId) {
        if (!Adminrepo.existsById(empId)) {
            throw new ResourceNotFoundException("Employee not found with ID: " + empId);
        }

        payrollRepo.deleteByEmployeeId(empId); 
        Adminrepo.deleteById(empId); 

        return "Employee and their payrolls deleted successfully";
    }

}
