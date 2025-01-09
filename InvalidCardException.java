package com.nit.pc.bank.exception;

public class InvalidCardException extends Exception 
{
	private static final long serialVersionUID= 1L;
	public InvalidCardException() {
		super();
	}
	public InvalidCardException(String msg) {
		super(msg);
	}
}
