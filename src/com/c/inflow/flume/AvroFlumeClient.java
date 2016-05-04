package com.c.inflow.flume;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

public class AvroFlumeClient {
    private RpcClient client;
    private String hostname;
    private int port;

    public void init(String hostname, int port) {

        this.hostname = hostname;
        this.port = port;
        this.client = RpcClientFactory.getDefaultInstance(hostname,port);

    }

    public void sendData(String data){

        Event event = EventBuilder.withBody(data,Charset.forName("UTF-8"));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("gameId", "tzsg");
        headers.put("logType", "pay");
        headers.put("timestamp", "1452878007834");
        event.setHeaders(headers);
        try{
            client.append(event);
        }catch(EventDeliveryException e) {
            client.close();
            client = null;
            client = RpcClientFactory.getDefaultInstance(hostname,port);
        }finally{
        	client.close();
            client = null;
        }
    }

    public void cleanUp(){

        client.close();
    }
}
