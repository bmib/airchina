/**
 * @title OnlineUserUtil.java
 * @package com.ass.framework.common.util
 * @date 2013-9-30 上午10:09:23
 */
package com.ass.framework.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ass.framework.common.config.Constants;
import com.ass.framework.common.entity.LoginUser;
import com.mybase.util.EhcacheUtil;

/**
 * 登录在线用户工具类
 * @author Mr.liuyong
 */
public class OnlineUserUtil {
	
	/**
	 * 增加一个在线用户
	 * @param personUUID
	 */
	public static void addUser(LoginUser loginUser){
		Map<String,Object> onlineUserMap=OnlineUserUtil.getOnlineUserMap();
		onlineUserMap.put(loginUser.getSessionID(), loginUser.getPersonUUID());
		EhcacheUtil.getInstance().put(Constants.ASS_DATACACHE_FLAG, Constants.ASS_ONLINE_USER, onlineUserMap);
	}
	
	/**
	 * 移除一个在线用户
	 * @param personUUID
	 */
	public static void removeUser(LoginUser loginUser){
		Map<String,Object> onlineUserMap=OnlineUserUtil.getOnlineUserMap();
		onlineUserMap.remove(loginUser.getSessionID());
		EhcacheUtil.getInstance().put(Constants.ASS_DATACACHE_FLAG, Constants.ASS_ONLINE_USER, onlineUserMap);
	}
	
	/**
	 * 根据sessionID获取用户personUUID
	 * @param personUUID
	 */
	public static String getUser(String sessionID){
		Map<String,Object> onlineUserMap=OnlineUserUtil.getOnlineUserMap();
		return onlineUserMap.get(sessionID)==null?null:onlineUserMap.get(sessionID).toString();
	}
	
	/**
	 * 获取所有用户personUUID s
	 * @param personUUID
	 */
	public static String getUsers(){
		String returnStr=null;
		Map<String,Object> onlineUserMap=OnlineUserUtil.getOnlineUserMap();
		Iterator<String> it=onlineUserMap.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			if(returnStr==null){
				returnStr=onlineUserMap.get(key).toString();
			}else{
				returnStr=returnStr+","+onlineUserMap.get(key).toString();
			}
		}
		return returnStr;
	}
	
	/**
	 * 获取在线用户Map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getOnlineUserMap(){
		Map<String,Object> onlineUserMap=(Map<String,Object>)EhcacheUtil.getInstance().get(Constants.ASS_DATACACHE_FLAG, Constants.ASS_ONLINE_USER);
		if(onlineUserMap==null){
			onlineUserMap=new HashMap<String,Object>();
		}
		return onlineUserMap;
	}
	
}
