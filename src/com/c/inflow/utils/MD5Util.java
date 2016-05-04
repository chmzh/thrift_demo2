package com.c.inflow.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * MD5工具类
 * 
 *
 */
public class MD5Util {

	/**
	 * 计算出MD5串
	 * @param data
	 * @return
	 */
	public final static String getMD5(String data) {
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * 计算出MD5串
	 * @param data
	 * @return
	 */
	public final static String getMD5(byte[] data) {
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * 获取16位的MD5串
	 * @param data
	 * @return
	 */
	public final static String getMD5Short(String data) {
		String md5 = DigestUtils.md5Hex(data);
		return StringUtils.substring(md5, 8, 24);
	}
	
}
