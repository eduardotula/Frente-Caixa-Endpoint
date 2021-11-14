package com.app.host.resources.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javassist.tools.rmi.ObjectNotFoundException;


public class ResourceExceptionHandler {

	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e){
		e.printStackTrace();
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	public ResponseEntity<StandardError> standardError(Exception e){
		e.printStackTrace();
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
