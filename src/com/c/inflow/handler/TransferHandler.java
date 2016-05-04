package com.c.inflow.handler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;

import com.c.inflow.BaseDB;
import com.c.inflow.HiveDB;
import com.c.inflow.ImpalaDB;
import com.c.inflow.domain.DvData;
import com.c.inflow.domain.DvSchema;
import com.c.inflow.domain.TSQLException;
import com.c.inflow.hdfs.CndwHdfsProcess;
import com.c.inflow.services.TransferService.Iface;
import com.c.inflow.startup.AvroServer;
import com.c.inflow.utils.FileUtil;

public class TransferHandler implements Iface {
	public final static Log LOG = LogFactory.getLog(TransferHandler.class); 
	private static String rootPath = "/opt/data/tables";
	private static String succCode = "1";
	
	@Override
	public String sendSchema(DvSchema dvSchema) throws TSQLException, TException {
		BaseDB db = null;
		String tblName = dvSchema.tbName;
		String dbName = dvSchema.dbName;
		String tblSchema = dvSchema.tbSchema;
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
			throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
		} catch (NullPointerException e) {
			LOG.error(e.getMessage());
			throw new TException(e.getCause());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
			throw new TException(e.getCause());
		}finally{
			try {
				db.releaseConnection();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
			}
		}
    	return succCode;
	}

	@Override
	public String sendData(DvData dvData) throws TSQLException, TException {
		File dir = new File(rootPath);
		if(!dir.exists()){
			if(dir.mkdirs()){
				return "create dir:"+rootPath+" fail";
			}
		}
		try {				
			//写到本地文件
			FileUtil fileUtil = new FileUtil();
			String fileName = dvData.getDbName()+"_"+dvData.getTbName()+dvData.getTbParCol().toString().replace("=", "").replaceAll("'", "")+".log";

			if(dvData.getProgress() == 0){  //第一次写
				fileUtil.write(rootPath+"/"+fileName, dvData.getTbData(),1);
				return succCode;
			}else if(dvData.getProgress() == 1){ //追写
				fileUtil.write(rootPath+"/"+fileName, dvData.getTbData(),2);
				return succCode;
			}else if(dvData.getProgress() == 2){  //最后一次
				fileUtil.write(rootPath+"/"+fileName, dvData.getTbData(),2);
			}
			
			//上传到hdfs
			CndwHdfsProcess.uploadToHdfs(rootPath+"/"+fileName);
			String parColStr = dvData.getTbParCol().toString();
			String tableDropPar = "";
			String tableAddPar = "";
			String loadData = "load data inpath '"+CndwHdfsProcess.hdfsRoot+"/"+fileName+"' overwrite into table "+dvData.getTbName();
			if(!StringUtils.isEmpty(parColStr)){
				loadData = loadData +" PARTITION("+parColStr+")";
				tableDropPar = "alter table "+dvData.getTbName().toString()+" drop "+"PARTITION("+parColStr+")";
				tableAddPar = "alter table "+dvData.getTbName().toString()+" add "+"PARTITION("+parColStr+")";
			}
			ImpalaDB db = null;
			try {
				db = new ImpalaDB();
				db.useDB(dvData.getDbName());
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				try {
					db.releaseConnection();
				} catch (SQLException e1) {
					throw new TSQLException(e1.getErrorCode(),e1.getLocalizedMessage());
				}
				throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
			}
			
			
			if(!tableAddPar.equals("")){
				
//				if(CndwHdfsProcess.isExist("/"+message.getDbName()+".db/"+message.getTbName()+"/"+partitions.toString())){
//					try {
//						db.exec(tableDropPar);
//						db.exec(tableAddPar);
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				
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
				throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
			}finally{
				try {
					db.releaseConnection();
				} catch (SQLException e) {
					LOG.error(e.getMessage());
					e.printStackTrace();
					throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
				}
			}
			File file = new File(rootPath+"/"+fileName);
			if(file.exists()){
				fileUtil.move(rootPath+"/"+fileName, rootPath+"/"+fileName+".completed");
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new TException(e.getCause());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
			throw new TException(e.getCause());
		}
		
		return succCode;
	}

	@Override
	public int queryDataCount(String dbName,String sql) throws TSQLException, TException {
		ImpalaDB db = null;
		int count;
		try {
			db = new ImpalaDB();
			db.useDB(dbName);
			count = db.queryDataCount(sql.toString());
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
			throw new TException(e.getCause());
		}finally{
			try {
				db.releaseConnection();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
			}
		}
		return count;
	}

	@Override
	public String queryData(String dbName,String sql, int curPage, int rows) throws TSQLException, TException {
		ImpalaDB db = null;
		String result = "";
		try {
			db = new ImpalaDB();
			db.useDB(dbName);
			result = db.queryData(sql, curPage, rows);
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			//LOG.error(e.getLocalizedMessage());
			throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
			throw new TException(e.getCause());
		}finally{
			try {
				db.releaseConnection();
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
				throw new TSQLException(e.getErrorCode(),e.getLocalizedMessage());
			}
		}
		return result;
	}



}
