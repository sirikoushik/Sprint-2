package com.capgemini.bank.exceptions;
@SuppressWarnings("serial")
public class InvalidMailException extends Exception {
	public InvalidMailException()
	{
		super("The Email Format submitted is Invalid");
	}

}
