/**  
 * @Title: LoginUser.java
 * @Package com.isf.api
 * @date 2012-11-15 下午01:17:59
 */
package com.ass.api;


/**
 * SSO Client人员登录信息
 * @author Mr.liuyong
 */
public class LoginUser {
	
	private static ThreadLocal<LoginUser> user = new ThreadLocal<LoginUser>();

	private String personUUID;
	
	public static void set(LoginUser _user){
		user.set(_user);
	}
	
	public static LoginUser getLoginUser(){
		LoginUser lUser=user.get();
		if(lUser==null){lUser=new LoginUser();}
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
