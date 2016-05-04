package com.c.inflow;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

public class HiveConnection {
	private Connection connection;
	private AtomicBoolean used = new AtomicBoolean(false);
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public AtomicBoolean getUsed() {
		return used;
	}
	public boolean setUsed(boolean expect,boolean update) {
		return used.compareAndSet(expect, update);
	}
}
