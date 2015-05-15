/**
 * @title UrlUtil.java
 * @package com.mybase.util
 * @date 2014-10-9 上午09:03:10
 */
package com.mybase.util;
/**
 * URL地址工具类
 * @author Mr.liuyong
 */
public class UrlUtil {
	
	/**
	 * 获取资源地址的标识符
	 * @param url
	 * @return
	 */
	public static String getUrlResourceIdentifier(String url){
		if(url==null || "".equals(url)){
			return "";
		}
		String[] tem=url.split("\\?")[0].split("\\/");
		return String.valueOf(tem[tem.length-1]);
	}

}
