package com.cairu.service.exception;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException (String args) {
		super(args);
	}

	public AuthorizationException (String msg, Throwable causa) {
		super(msg,causa);
	}
}
