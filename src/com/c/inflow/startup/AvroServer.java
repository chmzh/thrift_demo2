package com.c.inflow.startup;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Responder;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.codehaus.plexus.util.FileUtils;

import com.c.inflow.BaseDB;
import com.c.inflow.HiveDB;
import com.c.inflow.ImpalaConnectionPool;
import com.c.inflow.ImpalaDB;
import com.c.inflow.hdfs.CndwHdfsProcess;
import com.c.inflow.utils.FileUtil;
import com.c.proto.DvData;
import com.c.proto.DvDataProxy;
import com.c.proto.DvSchema;
import com.google.common.io.Files;


public class AvroServer {
	public final static Log LOG = LogFactory.getLog(AvroServer.class); 
	public static class DvDataProxyImpl implements DvDataProxy{
		private static String rootPath = "/opt/data/tables";
		private static String succCode = "1";
		@Override
		public CharSequence sendSchema(DvSchema message)
				throws AvroRemoteException {
			
			return createDB(message.getDbName().toString(),message.getTbName().toString(),message.getTbSchema().toString());
		}
		private String createDB(String dbName,String tblName,String tblSchema){
			BaseDB db = null;
	    	try {
	    		if(tblName.equals("hive")){
	    			db = new HiveDB();
	    		}else{
	    			db = new ImpalaDB();
	    		}
	    		
				//创建数据库
				db.exec("CREATE DATABASE IF NOT EXISTS "+dbName);
				//进入新创建的数据库建表
				db.useDB(dbName);
				db.exec(tblSchema);
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			} catch (NullPointerException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			}finally{
				
			}
	    	return succCode;
		}
		
		@Override
		public CharSequence sendData(DvData message) throws AvroRemoteException {
			File dir = new File(rootPath);
			if(!dir.exists()){
				if(dir.mkdirs()){
					return "create dir:"+rootPath+" fail";
				}
			}
			try {				
				//写到本地文件
				//String fileName = message.getDbName()+"_"+message.getTbName()+"_"+message.getTbParColVal()+System.currentTimeMillis()+".log";
				FileUtil fileUtil = new FileUtil();
				String fileName = message.getDbName()+"_"+message.getTbName()+message.getTbParCol().toString().replace("=", "").replaceAll("'", "")+".log";
				//Files.write(message.getTbData().array(), new File(rootPath+"/"+fileName));
				long start = System.currentTimeMillis();
				if(message.getProgress() == 0){  //第一次写
					fileUtil.write(rootPath+"/"+fileName, message.getTbData().array(),1);
					LOG.info("=============================第一次写结束用时:"+(System.currentTimeMillis()-start));
					return succCode;
				}else if(message.getProgress() == 1){ //追写
					fileUtil.write(rootPath+"/"+fileName, message.getTbData().array(),2);
					LOG.info("=============================追写结束用时:"+(System.currentTimeMillis()-start));
					return succCode;
				}else if(message.getProgress() == 2){  //最后一次
					fileUtil.write(rootPath+"/"+fileName, message.getTbData().array(),2);
					LOG.info("=============================最后一次结束用时:"+(System.currentTimeMillis()-start));
				}
				
				//上传到hdfs
				CndwHdfsProcess.uploadToHdfs(rootPath+"/"+fileName);
				String parColStr = message.getTbParCol().toString();
				String tableDropPar = "";
				String tableAddPar = "";
				String loadData = "load data inpath '"+CndwHdfsProcess.hdfsRoot+"/"+fileName+"' overwrite into table "+message.getTbName().toString();
				if(!StringUtils.isEmpty(parColStr)){
					loadData = loadData +" PARTITION("+parColStr+")";
					tableDropPar = "alter table "+message.getTbName().toString()+" drop "+"PARTITION("+parColStr+")";
					tableAddPar = "alter table "+message.getTbName().toString()+" add "+"PARTITION("+parColStr+")";
				}
				LOG.info(loadData);
				ImpalaDB db = null;
				try {
					db = new ImpalaDB();
					db.useDB(message.getDbName().toString());
				} catch (SQLException e) {
					LOG.error(e.getMessage());
					return e.getLocalizedMessage();
				}
				
				
				if(!tableAddPar.equals("")){
					
//					if(CndwHdfsProcess.isExist("/"+message.getDbName()+".db/"+message.getTbName()+"/"+partitions.toString())){
//						try {
//							db.exec(tableDropPar);
//							db.exec(tableAddPar);
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
					
					//先添加分区 1、如果添加失败则说明已存在分区 2 如果分区存在则删除掉，3、重新添加分区
					try {
						//1
						db.exec(tableAddPar);
					} catch (SQLException e) {
						try {
							//2
							db.exec(tableDropPar);
							//3
							db.exec(tableAddPar);
						} catch (SQLException e1) {
							LOG.error(e.getMessage());
							e1.printStackTrace();
						}
						LOG.error(e.getMessage());
						e.printStackTrace();
					}
				}
				
				//导入数据
				try {
					db.exec(loadData);
				} catch (SQLException e) {
					LOG.error(e.getMessage());
					e.printStackTrace();
					return e.getLocalizedMessage();
				}finally{
					
				}
				File file = new File(rootPath+"/"+fileName);
				if(file.exists()){
					fileUtil.move(rootPath+"/"+fileName, rootPath+"/"+fileName+".completed");
				}
			} catch (IOException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			}
			
			return succCode;
		}

		public CharSequence viewSchema(CharSequence sql)
				throws AvroRemoteException {
			ImpalaDB db = null;
			String schema = "";
			try {
				db = new ImpalaDB();
				schema = db.viewSchema(sql.toString());
			}catch (SQLException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			} catch (NullPointerException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			}finally{
				
			}
			
			return schema;
		}
		@Override
		public CharSequence queryData(CharSequence sql, int curPage, int rows)
				throws AvroRemoteException {
			ImpalaDB db = null;
			String result = "";
			try {
				db = new ImpalaDB();
				result = db.queryData(sql, curPage, rows);
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
				return e.getLocalizedMessage();
			}finally{
				
			}
			return result;
		}
		@Override
		public int queryDataCount(CharSequence sql)
				throws AvroRemoteException {
			ImpalaDB db = null;
			int count;
			try {
				db = new ImpalaDB();
				count = db.queryDataCount(sql.toString());
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage());
				return -2;
			}finally{
				
			}
			return count;
		}
		
	}
	
	private static Server server;
	private static int port = 19090;
    private static void startServer() throws IOException {
    	Responder responder = new SpecificResponder(DvDataProxy.class, new DvDataProxyImpl()); 
        server = new NettyServer(responder, new InetSocketAddress(port));
    }
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	DOMConfigurator.configure(System.getProperty("user.dir") + "/conf/log4j.xml");
		try {
			LOG.info("Starting server on port "+port+"....");
			startServer();
			LOG.info("Started server on port "+port+"!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
