package com.capgemini.bank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.exceptions.*;

public interface AccountDao {
	public void createAccountDao(String accountNumber, Account user) throws SQLException;
	public Account viewAccount(String accountNumber) throws AccountNotFoundException, Exception;
	public void addMoney(String accountNumber, int amount) throws AccountNotFoundException, SQLException, Exception;
	public void transfer(String accountNumber1,String accountNumber2, int amount) throws InsuffecientBalanceException, AccountNotFoundException, SameAccountException, SQLException, Exception;
	public void validateAccount(String accountNumber) throws AccountNotFoundException, Exception;
	public void checkSameAccount(String accountNumber1,String accountNumber2)throws SameAccountException ;
	public void checkSuffecientBalance(String accountNumber1, int amount) throws InsuffecientBalanceException, Exception;
	public ResultSet getAllAccounts() throws Exception;
	

}
