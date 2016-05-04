package com.c.inflow;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.c.inflow.utils.MD5Util;
import com.c.inflow.utils.PropertiesUtil;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	
    	Map<String, String> map = PropertiesUtil.getConf("comm");
    	System.out.println(map);
    	System.out.println(MD5Util.getMD5("adfer123"));
    	
    	String logInfo = "reg/123/text.log";
    	int index = logInfo.indexOf("/");
		String logType = logInfo.substring(0, index);
		
		String  fileName= logInfo.substring(index+1);
    	System.out.println(logType+","+fileName);
    	/*
    	// String url = http://182.254.171.49:8080/mobilehome/uwan/pay    //弹珠三国
    	// String url = http://pay.xia.uwan.com/UWanCharge/recharge    //仙魔界
    	String url = "http://114.119.36.140:9090/pay.do";  //囧工坊
    	String uid = "2";
    	//String gameId = "2002";    //弹珠三国
    	//String gameId = "2003";      //仙魔界
    	String gameId = "2005";      //囧工坊 
    	String serverId = "s19";
    	String orderid = "ZQALIWPAY201602021406345119";
    	int money = 10;
    	String role="南宫若颜";
    	long timestamp = System.currentTimeMillis()/1000;
    	//String KEY = "KSGSDGH1222#$GFG@#GGFG"; //弹珠三国
    	//String KEY = "5dcd73d39568e5s3ee9618d4e5s8916ea2e";   //仙魔界
    	String KEY = "3265478sk35eg091";   //囧工坊
    	String sing1 = MD5Util.getMD5(uid+gameId+serverId+orderid+money+role+timestamp+KEY);
    	System.out.println(uid+gameId+serverId+orderid+money+role+timestamp+KEY);
    	System.out.println(url+"?gameId="+gameId+"&serverId="+serverId+"&uid="+uid+"&orderid="+orderid+"&money="+money+"&role="+URLEncoder.encode(role, "UTF-8")+"&timestamp="+timestamp+"&sign="+sing1+"&attchData=20499@1@202079");
		*/
    	
    	
    	
/*    	
    	try {
    		HiveDB db = new HiveDB();
			db.exec("use datacenter");
			String schema = db.queryData("select * from cn_gameyl.buy_log limit 100",0,0);
			System.out.println(schema);
//			String string = db.queryData("select * from datacenter.player_all order by playerid",1,5);
//			System.out.println(string);
//			int count = db.queryDataCount("select count(*) from datacenter.player_all");
//			System.out.println(count);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/    	
    	
		//db.exec("use cmz2 create external table logs (field1 string, field2 string, field3 string)partitioned by (year string, month string, day string, host string)row format delimited fields terminated by ','");

    }
}
