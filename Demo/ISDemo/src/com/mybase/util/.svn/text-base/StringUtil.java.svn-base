/**  
 * @title StringUtil.java
 * @package com.mybase.util
 * @date 2013-3-20 下午10:41:11
 */ 
package com.mybase.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import oracle.sql.BLOB;
import oracle.sql.CLOB;


/**
 * 字符串处理工具类
 * @author Mr.liuyong
 */
public class StringUtil {

	private StringUtil(){}
	
	/**
	 * CLOB转String ORACLE
	 * @param clob
	 * @return String
	 */
	public static String OracleClobToString(CLOB clob){
		StringBuffer sb = new StringBuffer();
		try{
			Reader is = clob.getCharacterStream();
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			while (s != null) {
				sb.append(s);
				s = br.readLine();
			}
			return sb.toString();
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Object转String,其实Object实际也是CLOB ORACLE
	 * @param clob
	 * @return String
	 */
	public static String OracleClobToString(Object clob){
		oracle.sql.CLOB c=(oracle.sql.CLOB)clob;
		return StringUtil.OracleClobToString(c);
	}
	
	/**
	 * BLOB转String ORACLE
	 * @param blob
	 * @param encoding
	 * @return
	 */
	public static String OracleBlobToString(BLOB blob,String encoding){
		try {
			return new String(blob.getBytes((long) 1,(int) blob.length()), encoding==null?"GBK":encoding);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * BLOB转String,其实Object实际也是BLOB ORACLE ORACLE
	 * @param blob
	 * @param encoding
	 * @return
	 */
	public static String OracleBlobToString(Object blob,String encoding){
		oracle.sql.BLOB b=(oracle.sql.BLOB)blob;
		return StringUtil.OracleBlobToString(b,encoding);
	}
	
	/**
	 * byte数组转String
	 * @param byteArray
	 * @param encoding
	 * @return
	 */
	public static String byteArrayToString(byte[] byteArray,String encoding){
		try {
			return new String(byteArray, encoding==null?"GBK":encoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * byte数组转String
	 * @param byteArray
	 * @param encoding
	 * @return
	 */
	public static String byteArrayToString(Object byteArray,String encoding){
		byte[] b=(byte[])byteArray;
		return StringUtil.byteArrayToString(b,encoding);
	}
	
	/**
	 * 字符串为 null 或者为 "" 时返回 true
	 * @param str
	 * @return
	 */
    public static boolean isBlank(String str) {
    	return str == null || "".equals(str.trim()) ? true : false;
    }
}
