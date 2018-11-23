package com.cairu.service.exception;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException (String args) {
		super(args);
	}

	public DataIntegrityException (String msg, Throwable causa) {
		super(msg,causa);
	}
}
