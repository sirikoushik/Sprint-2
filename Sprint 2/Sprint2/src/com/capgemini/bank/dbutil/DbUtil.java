package com.capgemini.bank.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static Connection connect;
	public static Connection getConnection() throws SQLException
	{
		
		try {
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		}
		catch(SQLException e)
		{
			throw e;
		}
		return connect;
		
	}

}
