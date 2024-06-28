package com.dev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dev.constants.DataBaseConstants;

public class ConnectionUtils {  // provide connection with mysql
	static Connection connection;
	
	public static Connection getConnection() {
		try {
			Class.forName(DataBaseConstants.mysqlDriverClassName); // step 1 load driver
			connection = DriverManager.getConnection(DataBaseConstants.databaseUrl, DataBaseConstants.databaseUserName, DataBaseConstants.databasePassword);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return connection;
	}

}
