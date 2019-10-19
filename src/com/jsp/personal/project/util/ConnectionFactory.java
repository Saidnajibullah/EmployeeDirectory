package com.jsp.personal.project.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static String url;
	private static String username;
	private static String password;
	private static String driver;
    private static final String CRIDENTIALS = "C:\\Users\\Najib\\Desktop\\Jsp-Servlet\\EmployeeDirectory\\WebContent\\WEB-INF\\credentials.properties";
	private static ConnectionFactory cf;
	
	public synchronized static Connection getConnection() {
		if(cf == null) {
			cf = new ConnectionFactory();
		}
		return cf.createConnection();
	}
	
	private ConnectionFactory() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(CRIDENTIALS));
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			driver = properties.getProperty("driver");
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	private Connection createConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			System.out.println(e);
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}
	
}
