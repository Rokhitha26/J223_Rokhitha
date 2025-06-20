package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	private int uId;
	private String FirstName;
	private String LastName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

	  @OneToOne
	    @JoinColumn(name = "uId", referencedColumnName = "empId", insertable = false, updatable = false)
	    private employeeProfile profile;
	
}
