package com.c.inflow;

import java.sql.Connection;
import java.sql.SQLException;

public class HiveDB extends BaseDB {
	
	protected Connection getHiveConnection() throws NullPointerException, ClassNotFoundException, SQLException{
		Connection connection = HiveConnectionPool.getInstance().getConn();
		hiveConnection = connection;
		return connection;
	}
	
	public HiveDB() throws NullPointerException, ClassNotFoundException, SQLException{
		super();
	}
}
