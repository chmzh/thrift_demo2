package com.c.inflow.hdfs;

import java.io.IOException;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.sink.hdfs.HDFSWriter;
import org.apache.flume.sink.hdfs.HDFSWriterFactory;

public class CndwHdfsWriter {
	private HDFSWriter writer;
	private HDFSWriterFactory factory = new HDFSWriterFactory();
	public CndwHdfsWriter(){
		try {
			writer = factory.getWriter("log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String content){
		Event event = new SimpleEvent();
		event.setBody("abc".getBytes());
		try {
			Context context = new Context();
			writer.configure(context);
			writer.append(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
