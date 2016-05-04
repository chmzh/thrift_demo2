package com.c.inflow.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.c.inflow.domain.DvData;
import com.c.inflow.domain.DvSchema;
import com.c.inflow.domain.RequestCmd;
import com.c.inflow.domain.TSQLException;
import com.c.inflow.handler.TransferHandler;
import com.c.inflow.services.RequestService;
import com.c.inflow.services.TransferService;
   
public class Client {
    public static void main(String[] args) {  
    	
        try {  
        	//for(int i=0;i<100;i++){
        		
            TTransport transport = new TSocket("127.0.0.1", 19090);  
        	//TTransport transport = new TSocket("127.0.0.1", 19090);  
            transport.open();  
            TProtocol protocol = new TBinaryProtocol(transport);  
            TransferService.Client client = new TransferService.Client(protocol);  
            
//            DvData dvData = new DvData();
//            dvData.dbName = "abc"; 
//            dvData.tbName = "abc";
//            dvData.progress = 1;

            System.out.println(client.queryData("xunxia_log","select * from xunxia_log1.buy_log limit 3", 0, 0));
        	//}
            /*
            RequestCmd cmd = new RequestCmd();
            cmd.setCmd("10001");
            cmd.setGameId("tzsg");
            cmd.setTimestamp(1452872007834L);
            LoginLog log = new LoginLog();
            log.setId(1000);
            log.setUsername("abc");
            PayLog payLog = new PayLog();
            payLog.setId(10010);
            payLog.setOrderid("abcdefg");
            client.sendPayment(cmd, payLog);
            client.sendLogin(cmd, log);
            transport.close();  
            */
        } catch (TTransportException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (TSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }  
}
