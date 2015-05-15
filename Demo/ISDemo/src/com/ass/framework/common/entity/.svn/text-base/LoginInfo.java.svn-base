/**  
 * @Title: LoginUser.java
 * @Package com.ibase.common.entity
 * @date 2012-11-15 下午01:17:59
 */
package com.ass.framework.common.entity;


/**
 * SSO Client人员登录信息
 * @author Mr.liuyong
 */
public class LoginInfo {
	
	private static ThreadLocal<LoginInfo> user = new ThreadLocal<LoginInfo>();

	private String personUUID;
	
	public static void set(LoginInfo _user){
		user.set(_user);
	}
	
	public static LoginInfo getLoginUser(){
		LoginInfo lUser=user.get();
		if(lUser==null){lUser=new LoginInfo();}
		return lUser;
	}

	/**
	 * 返回变量personUUID
	 * @return the personUUID
	 */
	public String getPersonUUID() {
		return personUUID;
	}

	/**
	 * 设置变量personUUID
	 * @param personUUID the personUUID to set
	 */
	public void setPersonUUID(String personUUID) {
		this.personUUID = personUUID;
	}
}
