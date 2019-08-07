package com.capgemini.bank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.dbutil.DbUtil;
import com.capgemini.bank.exceptions.AccountNotFoundException;
import com.capgemini.bank.exceptions.InsuffecientBalanceException;
import com.capgemini.bank.exceptions.SameAccountException;

public class AccountDaoImpl implements AccountDao{
	Map<String, Account> userList=new HashMap<String, Account>();
	String string=null;
	Connection connect=null;
	PreparedStatement ps=null;
	int row;
	@Override
	public void createAccountDao(String accountNumber, Account user) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connect=DbUtil.getConnection();
			string="insert into wallets values(?,?,?,?,?)";
			ps=connect.prepareStatement(string);
			ps.setString(1,accountNumber);
			ps.setString(2,user.getName());
			ps.setLong(3,user.getPhoneNumber());
			ps.setString(4,user.getEmailid());
			ps.setInt(5,user.getBalance());
			ps.executeUpdate();
			connect.close();
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}

	@Override
	public Account viewAccount(String accountNumber) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Account user=new Account();
		try {
			validateAccount(accountNumber);
			connect=DbUtil.getConnection();
			Statement s=connect.createStatement();
			rs=s.executeQuery("select * from wallets where accnumber="+accountNumber);
			while(rs.next())
			{
				user.setName(rs.getString(2));
				user.setPhoneNumber(rs.getLong(3));
				user.setEmailid(rs.getString(4));
				user.setBalance(rs.getInt(5));
			}
			connect.close();
			return user;
		}
		catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public void addMoney(String accountNumber, int amount) throws Exception{
		// TODO Auto-generated method stub
		int temp=0;
		try {
			validateAccount(accountNumber);
			temp=getBalance(accountNumber);
			temp=temp+amount;
			connect=DbUtil.getConnection();
			Statement s=connect.createStatement();
			s.executeUpdate("update wallets set balance="+temp+" where accnumber="+accountNumber);
			connect.close();
			
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}

	@Override
	public void transfer(String accountNumber1, String accountNumber2, int amount) throws Exception {
		// TODO Auto-generated method stub
		int temp1=0,temp2=0;
			try {
				checkSameAccount(accountNumber1, accountNumber2);
				temp1=getBalance(accountNumber1);
				temp2=getBalance(accountNumber2);
				checkSuffecientBalance(accountNumber1, amount);
				temp1=temp1-amount;
				temp2=temp2+amount;
				connect=DbUtil.getConnection();
				Statement s=connect.createStatement();
				s.executeUpdate("update wallets set balance="+temp1+" where accnumber="+accountNumber1);
				s.executeUpdate("update wallets set balance="+temp2+" where accnumber="+accountNumber2);
				connect.close();
			}
			catch(Exception e)
			{
				throw e;
			}
		
	}
	public int getBalance(String accountNumber) throws SQLException {
		ResultSet rs=null;
		int balance = 0;
		try {
			connect=DbUtil.getConnection();
			Statement s=connect.createStatement();
			rs=s.executeQuery("select * from wallets where accnumber="+accountNumber);
			while(rs.next())
			{
				balance=rs.getInt(5);
			}
			connect.close();
		}
		catch(SQLException e)
		{
			throw e;
		}
		return balance;
		
	}
	public void checkSameAccount(String accountNumber1, String accountNumber2) throws SameAccountException{
		// TODO Auto-generated method stub
		try {
			if(accountNumber1.equals(accountNumber2))
			{
				throw new SameAccountException();
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}

	@Override
	public void checkSuffecientBalance(String accountNumber1, int amount) throws Exception {
		// TODO Auto-generated method stub
		try {
			int temp=getBalance(accountNumber1);
			if(temp<amount)
			{
				throw new InsuffecientBalanceException();
			}
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Override
	public void validateAccount(String accountNumber) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		try
		{
			connect=DbUtil.getConnection();
			Statement s=connect.createStatement();
			rs=s.executeQuery("select * from wallets where accnumber="+accountNumber);
			if(!rs.next())
			{
				throw new AccountNotFoundException();
			}
			connect.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}

	@Override
	public ResultSet getAllAccounts() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		try
		{
			connect=DbUtil.getConnection();
			Statement s=connect.createStatement();
			rs=s.executeQuery("select * from wallets");
		}
		catch(Exception e)
		{
			throw e;
		}
		return rs;
	}

}
