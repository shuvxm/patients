package com.example.patients.responsestructure;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	
	private int status; // httpStatus
	private String message;
	private T body;
	
	

}
