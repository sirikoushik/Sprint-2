package com.capgemini.bank.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.exceptions.*;

public interface AccountService {
	public String createAccountDao(Account user) throws InvalidMailException, InvalidPhoneNumberException, SQLException, Exception;
	public Account viewAccount(String accountNumber) throws AccountNotFoundException, Exception;
	public void addMoney(String accountNumber, int amount) throws AccountNotFoundException, SQLException, Exception;
	public void transfer(String accountNumber1,String accountNumber2, int amount) throws InsuffecientBalanceException, AccountNotFoundException, SameAccountException, Exception;
	public ResultSet getAllAccounts() throws Exception;
}
