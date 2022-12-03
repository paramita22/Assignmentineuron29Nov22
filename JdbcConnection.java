package com.param;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	public static Connection getJdbcConnection() throws ClassNotFoundException {
		Connection conn=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ineuron", "root", "pp10");
			if(conn!=null) {
				System.out.println("Connection success");
			}
			else {
				System.out.println("Connection failure");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
