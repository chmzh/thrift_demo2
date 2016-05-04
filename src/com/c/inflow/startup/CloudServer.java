package com.c.inflow.startup;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.c.inflow.handler.RequestHandler;
import com.c.inflow.services.RequestService;
import com.c.inflow.utils.PropertiesUtil;


/**
 * 
 * @author mingzhou.chen
 * 28458942@qq.com
 * 2015年12月25日 下午5:19:44
 */
public class CloudServer {
	static Logger logger = Logger.getLogger(CloudServer.class);
	private static int port = 9911;
	
	public static void main(String[] args) {
		DOMConfigurator.configure(System.getProperty("user.dir") + "/conf/log4j.xml");
		PropertiesUtil.loadAllConf();
		
		try {
			TServerSocket serverTransport = new TServerSocket(port);
			Factory proFactory = new TBinaryProtocol.Factory();

			TProcessor processor = new RequestService.Processor<RequestHandler>(new RequestHandler());
			TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
			tArgs.processor(processor);
			tArgs.protocolFactory(proFactory);

			TServer server = new TThreadPoolServer(tArgs);
			System.out.println("Starting server on port "+port+"....");
			server.serve();
			System.out.println("Started server on port "+port+"!");
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
