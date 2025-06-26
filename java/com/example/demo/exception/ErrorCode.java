package com.example.demo.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorCode {

	private int statuscode;
	private Date timestamp;
	private String messsage;
	private String description;
}
