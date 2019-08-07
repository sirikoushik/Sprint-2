package com.capgemini.bank.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.dao.AccountDao;
import com.capgemini.bank.dao.AccountDaoImpl;
import com.capgemini.bank.exceptions.AccountNotFoundException;
import com.capgemini.bank.exceptions.InsuffecientBalanceException;
import com.capgemini.bank.exceptions.SameAccountException;
public class AccountServiceImpl extends Validator implements AccountService{
	AccountDao dao=new AccountDaoImpl();
	Validator v=new Validator();
	int row;
	@Override
	public String createAccountDao(Account user) throws Exception{
		// TODO Auto-generated method stub
		String accountNumber=null;
		try 
		{
			v.validator(user);
			Random rand=new Random();
			int num=rand.nextInt(9000000)+1000000;
			accountNumber=String.valueOf(num);
			dao.createAccountDao(accountNumber, user);
			return accountNumber;
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}


	@Override
	public Account viewAccount(String accountNumber) throws Exception{

		try
		{
			return dao.viewAccount(accountNumber);
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Override
	public void addMoney(String accountNumber, int amount) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			dao.addMoney(accountNumber, amount);
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}

	@Override
	public void transfer(String accountNumber1, String accountNumber2, int amount) throws Exception {
		// TODO Auto-generated method stub
		try {
			dao.transfer(accountNumber1, accountNumber2, amount);
			}
		catch(Exception e)
		{
			throw e;
		}
		
	}


	@Override
	public ResultSet getAllAccounts() throws Exception {
		// TODO Auto-generated method stub
		try {
			return dao.getAllAccounts();
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
