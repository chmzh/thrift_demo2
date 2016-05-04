package com.c.inflow.client;

import com.c.inflow.flume.AvroFlumeClient;

public class AvroClient {
	public static void main(String[] args) {
		AvroFlumeClient client = new AvroFlumeClient();
		client.init("192.168.23.91", 9922);
		long start = System.currentTimeMillis();
		//client.sendData("[{\"headers\":{\"cmd\":\"10001\",\"gameId\":\"tzsg\",\"logType\":\"login\",\"timestamp\":1452878007834,\"__isset_bitfield\":1},\"body\":\"1000,abc\"}]");
		client.sendData("abc,cde");
		long end = System.currentTimeMillis();
		System.out.println((end-start)+"毫秒");
		
	}
}
