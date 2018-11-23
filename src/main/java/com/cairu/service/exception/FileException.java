package com.cairu.service.exception;

public class FileException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FileException (String args) {
		super(args);
	}

	public FileException (String msg, Throwable causa) {
		super(msg,causa);
	}
}
