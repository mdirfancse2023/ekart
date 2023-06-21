package com.ekart.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {
	private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException {
		if(connection==null) {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "system";
			String password = "root";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url,username,password);
				System.out.println("Connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
		}
		return connection;
	}
}
