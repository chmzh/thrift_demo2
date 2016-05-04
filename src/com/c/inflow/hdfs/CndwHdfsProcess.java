package com.c.inflow.hdfs;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.apache.zookeeper.common.IOUtils;

public class CndwHdfsProcess {
	public final static String tbPartitionFile = "/user/hive/warehouse";
	//public final static String hdfsRoot = "hdfs://master1:8020/user/impala/tables_tmp/";
	public final static String hdfsRoot = "/user/impala/tables_tmp/";
	
	/**
	 * 上传文件到HDFS上去
	 * String localSrc = "d://qq.txt";
		String dst = "hdfs://192.168.0.113:9000/user/zhangzk/qq.txt";
	 * @param localFile
	 * @param hdfsPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void uploadToHdfs(String localFile) throws IOException{
		
		InputStream in = new BufferedInputStream(new FileInputStream(localFile));
		Configuration conf = newConfiguration();
		FileSystem fs = newFileSystem(conf);
		
		Path src = new Path(localFile);
		Path dst = new Path(hdfsRoot);
		fs.copyFromLocalFile(src, dst);
		in.close();
		fs.close();
	}
	
	public static boolean isExist(String hdfsFile) throws IOException{
		Configuration conf = newConfiguration();
		FileSystem fs = newFileSystem(conf);
		Path filePath = new Path(tbPartitionFile+hdfsFile);
		boolean bol = fs.exists(filePath);
		fs.close();
		return bol;
	}
	
	public static void writeHdfs(String localFile,String hdfsFile) throws IOException{
		InputStream in = new BufferedInputStream(new FileInputStream(localFile));
		Configuration conf = newConfiguration();
		String dst = hdfsRoot+hdfsFile;
		FileSystem fs = newFileSystem(conf);
		FSDataOutputStream out = fs.append(new Path(dst));
		byte[] bytes = new byte[in.available()];
		in.close();
		out.write(bytes);
		out.flush();
		out.close();
		fs.close();
		
	}
	
	private static FileSystem newFileSystem(Configuration conf) throws IOException{
		//FileSystem fs = FileSystem.get(URI.create(hdfsRoot),conf);
		FileSystem fs = FileSystem.get(conf);
		return fs;
	}
	
	private static Configuration newConfiguration(){
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://cndwservice1");
		conf.set("dfs.nameservices", "cndwservice1");
		conf.set("dfs.ha.namenodes.cndwservice1", "namenode44,namenode74");
		conf.set("dfs.namenode.rpc-address.cndwservice1.namenode44", "master1:8020");
		conf.set("dfs.namenode.rpc-address.cndwservice1.namenode74", "master2:8020");
		conf.set("dfs.client.failover.proxy.provider.cndwservice1","org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
		conf.set("ha.zookeeper.quorum", "slave1:2181,slave2:2181,slave3:2181");
		conf.set("dfs.ha.automatic-failover.enabled.cndwservice1", "true");
		return conf;
	}
}
