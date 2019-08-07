package com.capgemini.bank.exceptions;
@SuppressWarnings("serial")
public class SameAccountException extends Exception {
	public SameAccountException()
	{
		super("the account numbers entered are same.");
	}

}
