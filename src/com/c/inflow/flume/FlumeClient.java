package com.c.inflow.flume;

import java.nio.charset.Charset;
import java.security.cert.CollectionCertStoreParameters;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.api.ThriftRpcClient;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.thrift.ThriftFlumeEvent;
import org.apache.flume.thrift.ThriftSourceProtocol;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.c.inflow.utils.MD5Util;
import com.c.inflow.utils.PropertiesUtil;
import com.c.inflow.utils.PropertyListener;

/**
 * FLUEM客户端工具
 * 
 * @author mingzhou.chen 28458942@qq.com 2015年12月29日 下午4:50:38
 */
public class FlumeClient implements PropertyListener {

	public final static Logger LOGGER = Logger.getLogger(FlumeClient.class);
	//当前活动主机
	private String curHost = "";
	//失败的主机
	private Collection<String> failedHost = new ArrayList<String>();
	//所有主机
	private Collection<String> allHost = new ArrayList<String>();
	//配置
	private Map<String, String> conf = null;
	
	private static FlumeClient instance = new FlumeClient();

	private RpcClient rpcClient = null;

	private ConcurrentMap<String, Integer> msgs = new ConcurrentHashMap<String, Integer>();
	
	//重试次数
	private int tryTimes = 3;
	
	public static FlumeClient getInstance() {
		return instance;
	}

	private FlumeClient() {
		PropertiesUtil.setListener(this);
	}

	private void send(Map<String, String> headers, String body,String sign){
		if(allHost.size()==0){
			reload();
		}
		try {
			if (null == rpcClient || rpcClient.isActive() == false) {
				Collection<String> cList = new ArrayList<String>();
				cList.addAll(allHost);
				cList.removeAll(failedHost);
				String[] hp;
				if(cList.size()>0){
					curHost = (String) cList.toArray()[0];					
				}else{
					curHost = (String) failedHost.toArray()[0];
					failedHost.remove(curHost);
				}
				hp = conf.get(curHost).split(":");
				rpcClient = RpcClientFactory.getThriftInstance(hp[0],Integer.valueOf(hp[1]));
			}
			
			Event event = EventBuilder.withBody(body,Charset.forName("UTF-8"));
			rpcClient.append(event);
		}catch (Throwable e) {
			
			rpcClient.close();
			//该主机失败
			failedHost.add(curHost);
			int times = msgs.get(sign)==null?0:msgs.get(sign);
			times = times+1;
			if(times>=tryTimes){
				LOGGER.error("发送数据到 主机"+conf.get(curHost)+"失败");
				return;
			}
			msgs.put(sign, times);
			//发送失败重试
			for(int i=0;i<tryTimes;i++){
				send(headers, body,sign);
			}			
			//TODO 预警  记录到文件
			
			
		}
		
		finally {
			//close();
		}
	}
	
	/**
	 * 发送数据到flume
	 * 
	 * @param headers
	 * @param body
	 * @throws TException
	 */
	public void send(Map<String, String> headers, String body) {
		send(headers, body,MD5Util.getMD5(body));
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		if (rpcClient != null && rpcClient.isActive()) {
			rpcClient.close();
		}
	}

	@Override
	public boolean reload() {
		 conf = PropertiesUtil.getConf("flume");
		 allHost = conf.keySet();
		return false;
	}
}
