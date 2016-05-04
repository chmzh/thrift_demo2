package com.c.inflow.handler;

import org.apache.thrift.TException;

import com.c.inflow.domain.DvHeader;
import com.c.inflow.domain.RequestCmd;
import com.c.inflow.domain.UserRegInfo;
import com.c.inflow.services.RequestService.Iface;


public class RequestHandler implements Iface {

	@Override
	public void sendUserRegInfo(RequestCmd cmd, UserRegInfo log)
			throws TException {

		
	}
	
	/**
	 * 转换成头部
	 * @param cmd
	 * @param logType
	 * @return
	 */
	private DvHeader convert2Header(RequestCmd cmd,String logType){
		DvHeader header = new DvHeader();
		
		
		return header;
	}
	
	/**
	 * 将RequestCmd 中的数据转换成body 公共body部分
	 * @param cmd
	 * @param logType
	 * @return
	 */
	private String convert2Body(RequestCmd cmd){
		StringBuilder builder = new StringBuilder();		
		
		return builder.toString();
	}
	
/*
	@Override
	public void sendLogin(RequestCmd cmd, LoginLog log) throws TException {
		System.out.println(log.toString());
		HttpFlumeClient client = new HttpFlumeClient(Type.TO_HDFS);
		StringBuilder body = new StringBuilder();
		body.append(log.getId());
		body.append(",");
		body.append(log.getUsername());
		cmd.setLogType("login");
		client.post(cmd, body.toString());
		
	}

	@Override
	public void sendPayment(RequestCmd cmd, PayLog log) throws TException {
		System.out.println(log.toString());
		
		HttpFlumeClient client = new HttpFlumeClient(Type.TO_KAFKA);
		StringBuilder body = new StringBuilder();
		
		body.append("type=pay&gameId="+cmd.getGameId()+",");
		body.append(log.getId());
		body.append(",");
		body.append(log.getUsername());
		cmd.setLogType("pay");
		client.post(cmd, body.toString());
	}

	@Override
	public void sendException(RequestCmd cmd, String msg) throws TException {
		System.out.println(msg);
	}
*/

}
