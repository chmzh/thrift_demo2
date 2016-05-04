package com.c.inflow;

import java.sql.Connection;
import java.util.Map;

import com.c.inflow.utils.PropertiesUtil;

public class ImpalaConnectionPool extends BaseDataSource {
	private static ImpalaConnectionPool instance = new ImpalaConnectionPool();
	private static final String CONNECTION_URL = "jdbc:hive2://${host}/;auth=noSasl";
	public static ImpalaConnectionPool getInstance(){
		if(DS == null){
			Map<String, String> conf = PropertiesUtil.getConf("comm");
    		String connectURI = CONNECTION_URL.replace("${host}", conf.get("impala"));
    		String username = "";
    		String pswd = "";
    		//String pswd = "";
    		String driverClass = "org.apache.hive.jdbc.HiveDriver";
    		int initialSize = 20;
    		int maxtotal = 30;
    		int maxIdle = 30;
    		int maxWaitMillis = 1000;
    		int minIdle = 10;
    		initDS(connectURI, username, pswd, driverClass, initialSize, maxtotal, maxIdle, maxWaitMillis, minIdle);
    	}
    	return instance;
	}
	
	private ImpalaConnectionPool(){
		
	}
	

	public static void main(String[] args) {
		ImpalaConnectionPool poll = ImpalaConnectionPool.getInstance();
		Connection connection = poll.getConn();
		System.out.println(connection);
	}
}
