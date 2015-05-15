/**
 * @title ShareDataUtil.java
 * @package com.mybase.util
 * @date 2013-4-1 下午08:40:23
 */
package com.mybase.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过ThreadLocal共享Session范围数据
 * 通过将数据存放在ThreadLocal中，方便在系统任意地方调用，而不需要拘泥于HttpServletRequest
 * 步骤一、在需要共享的数据产生时，通过putDataIntoSessionAndThreadLocal方法将数据存放到session范围，同时放入ThreadLocal
 * 步骤二、在过滤器中，先通过getDataFromSession从session范围获取共享数据，再将数据set到ThreadLocal范围
 * 步骤三、经过步骤一、二，你就可以再任意地方get共享数据了
 * @author Mr.liuyong
 */
public class ShareDataUtil {
	
	private static ThreadLocal<Map<String,Object>> threadLocal   = new ThreadLocal<Map<String,Object>>();
	
	/**
	 * 将数据存储到Session范围
	 * @param request
	 * @param key
	 * @param o
	 */
	public static void putDataIntoSession(HttpServletRequest request,String key,Object o){
		request.getSession().setAttribute(key, o);
	}
	
	/**
	 * 将数据存储到Session范围,同时放入ThreadLocal中
	 * @param request
	 * @param key
	 * @param o
	 */
	public static void putDataIntoSessionAndThreadLocal(HttpServletRequest request,String key,Object o){
		request.getSession().setAttribute(key, o);
		set(key,o);
	}
	
	/**
	 * 从Session范围获取key值对应的数据
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getDataFromSession(HttpServletRequest request,String key){
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 存值，将需要存放在ThreadLocal中共享的数据通过该方法存入
	 * 举例：若要共享用户登录数据，需在过滤器中从session范围获取该数据，再通过set方法设置到ThreadLocal
	 * @param key
	 * @param o
	 */
	public static void set(String key,Object o){
		Map<String,Object> map=threadLocal.get();
		if(map==null){
			threadLocal.set(new HashMap<String,Object>());
		}
		threadLocal.get().put(key,o);
	}
	
	/**
	 * 取值，在需要使用的任何地方调用
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		Map<String,Object> map=threadLocal.get();
		if(map==null){
			return null;
		}else{
			return threadLocal.get().get(key);
		}
	}
}
