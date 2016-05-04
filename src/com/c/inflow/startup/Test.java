package com.c.inflow.startup;

import java.io.IOException;

import com.c.inflow.hdfs.CndwHdfsProcess;

public class Test {
	public static void main(String[] args) {
		try {
			CndwHdfsProcess.uploadToHdfs(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
