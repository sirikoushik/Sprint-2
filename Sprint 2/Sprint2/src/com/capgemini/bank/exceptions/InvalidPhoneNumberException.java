package com.capgemini.bank.exceptions;
@SuppressWarnings("serial")
public class InvalidPhoneNumberException extends Exception{
	public InvalidPhoneNumberException()
	{
		super("The Phone number is in invalid Format");
	}

}
