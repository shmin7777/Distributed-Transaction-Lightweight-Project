package com.example.demo.exception;

public class RollbackException extends RuntimeException{
	public RollbackException(String errorMessage) {
		super(errorMessage);
	}
}
