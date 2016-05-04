package com.c.inflow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.c.inflow.domain.DvTableSchema;
import com.google.gson.Gson;

public abstract class BaseDB {
	
	//Hive Driver
	//static String JDBCDriver = "com.cloudera.hive.jdbc41.HS2Driver";
	//private static final String CONNECTION_URL = "jdbc:hive2://192.168.21.34:10000/";
	
	//Impala Driver
	boolean bol;
	protected Connection hiveConnection;
	
	protected abstract Connection getHiveConnection() throws NullPointerException, ClassNotFoundException, SQLException;
	
	public BaseDB() throws NullPointerException, ClassNotFoundException, SQLException{
		getHiveConnection();
	}
	
	public void useDB(String dbName) throws SQLException, NullPointerException, ClassNotFoundException{
		exec("use "+dbName);
	}
	
	public void exec(String query) throws SQLException, NullPointerException, ClassNotFoundException{
		Statement stmt = hiveConnection.createStatement();
		bol = stmt.execute(query);		
	}
	
	public String viewSchema(String sql) throws SQLException, NullPointerException, ClassNotFoundException{
		List<DvTableSchema> schemas = null;
		Gson gson = new Gson();
		Statement stmt = hiveConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs != null){
			schemas = new ArrayList<DvTableSchema>();
			while (rs.next()) {
				DvTableSchema schema = new DvTableSchema();
				schema.setName(rs.getString("name"));
				schema.setType(rs.getString("type"));
				schema.setDes(rs.getString("comment"));
				schemas.add(schema);
			}
		}
		if(null!=schemas){
			return gson.toJson(schemas);
		}
		return "";
		
	}
	
	public int queryDataCount(String sql) throws SQLException, NullPointerException, ClassNotFoundException{
		int count = 0;
		Statement stmt = hiveConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(null!=rs){
			while (rs.next()) {
				count = rs.getInt(1);
				
			}
		}
		return count;
	}
	
	
	public String queryData(CharSequence sql, int curPage, int rows) throws SQLException, NullPointerException, ClassNotFoundException{
		String sql1 = sql.toString();
		if(curPage!=0 && rows!=0){
			sql1 = sql + " limit "+rows+" offset "+(curPage-1)*rows;
		}
		
		StringBuilder tmp_builder = new StringBuilder();
		StringBuilder builder = new StringBuilder();
		Statement stmt = hiveConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);
		if(null!=rs){
			ResultSetMetaData metaData = rs.getMetaData();
			tmp_builder.append("[");
			while(rs.next()){
				tmp_builder.append("{");
				int colCount = metaData.getColumnCount();
				for(int i=1;i<=colCount;i++){
					int colType = metaData.getColumnType(i);
					String colLabel = metaData.getColumnLabel(i);
					Object colValue = rs.getObject(colLabel);
					tmp_builder.append("\""+colLabel+"\":");
					if(colType == Types.VARBINARY 
							|| colType == Types.LONGVARCHAR 
							|| colType == Types.CHAR 
							|| colType == Types.LONGNVARCHAR
							|| colType == Types.LONGVARCHAR
							|| colType == Types.NCHAR
							|| colType == Types.NVARCHAR
							|| colType == Types.VARBINARY
							|| colType == Types.VARCHAR
							|| colType == Types.DATE
							|| colType == Types.TIME
							|| colType == Types.TIMESTAMP
							){
						tmp_builder.append("\""+colValue+"\"");
					}else{
						tmp_builder.append(colValue);
					}
					if(i!=colCount){
						tmp_builder.append(",");
					}
					
				}
				tmp_builder.append("},");
			}
			String str = tmp_builder.toString();
			if(str.lastIndexOf(",")>-1){
				str = tmp_builder.substring(0, str.length()-1);
			}
			builder.append(str);
			builder.append("]");
		}
		return builder.toString();
	}
	
	public void releaseConnection() throws SQLException{
		hiveConnection.close();
	}
}
