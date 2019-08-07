package com.capgemini.bank.exceptions;

@SuppressWarnings("serial")
public class AccountNotFoundException extends Exception{
	public AccountNotFoundException()
	{
		super("The Account Number is not found");
	}

}
