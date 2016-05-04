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

public class ImpalaConnectionPool2 {
	private int poolSize = 20;
	private final List<HiveConnection> connections;
	static String JDBCDriver = "org.apache.hive.jdbc.HiveDriver";
	private static final String CONNECTION_URL = "jdbc:hive2://${host}/;auth=noSasl";
	private boolean inited = false;
	private static ImpalaConnectionPool2 instance = new ImpalaConnectionPool2();
	
	private final static ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	
	
	private ImpalaConnectionPool2(){
		setPoolSize(poolSize);
		connections = new ArrayList<HiveConnection>(this.poolSize);
	}
	
	public void setPoolSize(int poolSize){
		this.poolSize = poolSize;
	}
	
	public void init() throws ClassNotFoundException, SQLException{
		Map<String, String> conf = PropertiesUtil.getConf("comm");
		synchronized (connections) {
			if(!inited){
				
				Class.forName(JDBCDriver);
				for(int i=0;i<poolSize;i++){
					Connection con = DriverManager.getConnection(CONNECTION_URL.replace("${host}", conf.get("impala")));
					HiveConnection hiveConnection = new HiveConnection();
					hiveConnection.setConnection(con);
					connections.add(hiveConnection);
				}
				inited = true;
			}
		}

	}
	
	public HiveConnection getConnection(){
		HiveConnection rConnection = null;
		synchronized(connections){
			for(HiveConnection connection:connections){
				if(!connection.getUsed().get()){
					if(connection.setUsed(false, true)){
						rConnection = connection;
						break;
					}
				}
			}
		}
		return rConnection;
	}

	public static ImpalaConnectionPool2 getInstance() {
		return instance;
	}

	public boolean isInited() {
		return inited;
	}
	
	static class ConnectionFailRetry implements Runnable{
		
		private List<HiveConnection> conns = null;
		public ConnectionFailRetry(List<HiveConnection> conns){
			this.conns = conns;
		}
		@Override
		public void run() {

			synchronized (conns) {
				for(HiveConnection connection: conns){
					Connection conn = connection.getConnection();
					try {
						if(conn.isClosed()){
							conn = DriverManager.getConnection(CONNECTION_URL);
							connection.setConnection(conn);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	
	public void startTask(){
		exec.scheduleAtFixedRate(new ConnectionFailRetry(connections), 60, 30, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		ImpalaConnectionPool2 poll = ImpalaConnectionPool2.getInstance();
		try {
			poll.init();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		poll.startTask();
	}
}
