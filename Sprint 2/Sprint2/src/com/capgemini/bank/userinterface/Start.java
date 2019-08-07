package com.capgemini.bank.userinterface;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.service.AccountServiceImpl;



public class Start {
	static AccountServiceImpl service=new AccountServiceImpl();
	public static void showMenu() {
		System.out.println("Menu:");
		System.out.println("01. Create an Account");
		System.out.println("02. Add Money to Account");
		System.out.println("03. Show Details");
		System.out.println("04. Transfer Money");
		System.out.println("05. Show All Accounts");
		System.out.println("06. Exit");
		System.out.print("Enter Your Choice : ");
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int choice;
		while(true)
		{
			showMenu();
			choice = scanner.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Account Creation");
				try
				{
					Account user=new Account();
					System.out.println("Enter your name:");
					user.setName(scanner.next());
					System.out.println("Enter your phone number:");
					user.setPhoneNumber(scanner.nextLong());
					System.out.println("Enter your email:");
					user.setEmailid(scanner.next());
					user.setBalance(0);
					String AccountNumber=service.createAccountDao(user);
					System.out.println("Your Account Number is : "+AccountNumber);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				break;
			case 2:
				System.out.println("Adding Money");
				try {
					System.out.println("Enter the account number to add money:");
					String AccountNumber=scanner.next();
					System.out.println("Enter the amount to add in to the account "+AccountNumber);
					int Amount=scanner.nextInt();
					service.addMoney(AccountNumber, Amount);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				break;
			case 3:
				System.out.println("View Account Details");
				try
				{
					System.out.println("Enter the Account Number");
					String AccountNumber=scanner.next();
					Account user=service.viewAccount(AccountNumber);
					System.out.println("Name="+user.getName()+"\nPhone="+user.getPhoneNumber()+"\nEmail="+user.getEmailid()+"\nBalance="+user.getBalance());
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				break;
			case 4:
				System.out.println("Money Transfer");
				try
				{
					System.out.println("Enter the sender Account Number");
					String SenderAccountNumber=scanner.next();
					System.out.println("Enter the Reciever Account Number");
					String RecieverAccountNumber=scanner.next();
					System.out.println("Enter the amount to be transferred");
					int TransferAmount=scanner.nextInt();
					service.transfer(SenderAccountNumber, RecieverAccountNumber, TransferAmount);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				break;
			case 5:
				try
				{
					ResultSet rs=service.getAllAccounts();
					while(rs.next())
					{
						System.out.println("Account Number: "+rs.getString(1)+"\nName: "+rs.getString(2));
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				break;
			case 6:
				System.exit(0);
			default:
				break;
			}
		}
	}

}
