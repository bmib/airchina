/**  
 * @Title: CookieUtil.java
 * @Package com.mybase.util
 * @date 2012-3-1 下午04:13:27
 */ 
package com.mybase.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie操纵类 
 * @author Mr.liuyong
 */
public class CookieUtil {
	/**
	 * 增加信息
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
	    Cookie cookie;
		try {
			cookie = new Cookie(name,value==null?"":URLEncoder.encode(value,"UTF-8"));
			cookie.setPath("/");
		    if(maxAge>0){
		    	cookie.setMaxAge(maxAge);
		    }
		    response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {}
	}
	
	/**
	 * 获取Cookie信息
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = readCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }  
	}
	
	/**
	 * 获取Cookie中值的信息
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValueByName(HttpServletRequest request,String name){
		Cookie cookie=getCookieByName(request,name);
		if(cookie!=null){
			try {
				return "".equals(cookie.getValue())?"":URLDecoder.decode(cookie.getValue(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}else{
			return "";
		}
	}
	
	/**
	 * 将Cookie中的信息转换为Map
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> readCookieMap(HttpServletRequest request){ 
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
