package com.c.inflow.startup;

import java.net.InetSocketAddress;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.c.inflow.handler.TransferHandler;
import com.c.inflow.services.TransferService;
import com.c.inflow.utils.PropertiesUtil;


/**
 * 
 * @author mingzhou.chen
 * 28458942@qq.com
 * 2015年12月25日 下午5:19:44
 */
public class ThriftServer {
	static Logger logger = Logger.getLogger(ThriftServer.class);
	public static void main(String[] args) {
		DOMConfigurator.configure(System.getProperty("user.dir") + "/conf/log4j.xml");
		PropertiesUtil.loadAllConf();
		//logger.info("log4j");
		
		try {
			Map<String, String> conf = PropertiesUtil.getConf("comm");
			String ip = conf.get("thrift_ip");
			int port = Integer.valueOf(conf.get("thrift_port"));
			InetSocketAddress address = new InetSocketAddress(ip, port);
			//InetSocketAddress address = new InetSocketAddress("127.0.0.1", 19090);
			TServerSocket serverTransport = new TServerSocket(address);
			Factory proFactory = new TBinaryProtocol.Factory();

			TProcessor processor = new TransferService.Processor<TransferHandler>(new TransferHandler());
			TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(proFactory);

			TServer server = new TThreadPoolServer(tArgs);
			System.out.println("Start server on port "+port+"....");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		
	/*	
		try {  
            TNonblockingServerSocket socket = new TNonblockingServerSocket(7911);  
            final RequestService.Processor processor = new RequestService.Processor(new RequestHandler());  
            THsHaServer.Args arg = new THsHaServer.Args(socket);  
            // 高效率的、密集的二进制编码格式进行数据传输  
            // 使用非阻塞方式，按块的大小进行传输，类似于 Java 中的 NIO  
            arg.protocolFactory(new TCompactProtocol.Factory());  
            arg.transportFactory(new TFramedTransport.Factory());  
            arg.processorFactory(new TProcessorFactory(processor));  
            TServer server = new THsHaServer(arg);  
            server.serve();  
            System.out.println("#服务启动-使用:非阻塞&高效二进制编码");  
        } catch (TTransportException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        */
	}
	
	
}
