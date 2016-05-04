package com.c.inflow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.zookeeper.server.ConnectionBean;

import com.c.inflow.utils.PropertiesUtil;

public class HiveConnectionPool extends BaseDataSource {

	static String JDBCDriver = "com.cloudera.hive.jdbc41.HS2Driver";
	private static HiveConnectionPool instance = new HiveConnectionPool();
	private static final String CONNECTION_URL = "jdbc:hive2://${host}/;auth=noSasl";
	public static HiveConnectionPool getInstance(){
		if(DS == null){
			Map<String, String> conf = PropertiesUtil.getConf("comm");
    		String connectURI = CONNECTION_URL.replace("${host}", conf.get("impala"));
    		String username = "";
    		String pswd = "";
    		//String pswd = "";
    		String driverClass = JDBCDriver;
    		int initialSize = 20;
    		int maxtotal = 30;
    		int maxIdle = 30;
    		int maxWaitMillis = 1000;
    		int minIdle = 10;
    		initDS(connectURI, username, pswd, driverClass, initialSize, maxtotal, maxIdle, maxWaitMillis, minIdle);
    	}
    	return instance;
	}
	
	private HiveConnectionPool(){
		
	}
	
}
