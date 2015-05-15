/**  
 * @Title: LoginUser.java
 * @Package com.ibase.common.entity
 * @date 2012-11-15 下午01:17:59
 */
package com.ass.framework.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 人员登录信息
 * @author Mr.liuyong
 */
public class LoginUser {
	
	private static ThreadLocal<LoginUser> user = new ThreadLocal<LoginUser>();
	//人员UUID
	private String personUUID;
	//人员编码
	private String personCode;
	//人员名称
	private String personName;
	//人员性别
	private String sex;
	//人员身份证号
	private String idNum;
	//手机号
	private String mobile;
	//电子邮件
	private String email;
	//MSN_QQ
	private String MSN_QQ;
	//所在组织UUID
	private String orgUUID;
	//所在组织代码
	private String orgCode;
	//所在组织名称
	private String orgName;
	//人员账号
	private String account;
	//SESSIONID
	private String sessionID;
	
	//一级模块
	private String levelOneJson;
	//二级模块
	private String levelTwoJson;
	//三级模块
	private String levelThreeJson;
	//四级模块
	private String levelFourJson;
	//五级模块
	private String levelFiveJson;
	
	//用户角色集合<角色代码,角色名称>
	private Map<String,String> roleMap=new HashMap<String,String>();
	//用户功能资源集合<资源代码,资源地址>
	private Map<String,String> resourceMap=new HashMap<String,String>();
	
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

	/**
	 * 返回变量personCode
	 * @return the personCode
	 */
	public String getPersonCode() {
		return personCode;
	}

	/**
	 * 设置变量personCode
	 * @param personCode the personCode to set
	 */
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	/**
	 * 返回变量personName
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * 设置变量personName
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * 返回变量sex
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置变量sex
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 返回变量idNum
	 * @return the idNum
	 */
	public String getIdNum() {
		return idNum;
	}

	/**
	 * 设置变量idNum
	 * @param idNum the idNum to set
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * 返回变量mobile
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置变量mobile
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 返回变量email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置变量email
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 返回变量mSN_QQ
	 * @return the mSN_QQ
	 */
	public String getMSN_QQ() {
		return MSN_QQ;
	}

	/**
	 * 设置变量mSNQQ
	 * @param mSNQQ the mSN_QQ to set
	 */
	public void setMSN_QQ(String mSNQQ) {
		MSN_QQ = mSNQQ;
	}

	/**
	 * 返回变量orgUUID
	 * @return the orgUUID
	 */
	public String getOrgUUID() {
		return orgUUID;
	}

	/**
	 * 设置变量orgUUID
	 * @param orgUUID the orgUUID to set
	 */
	public void setOrgUUID(String orgUUID) {
		this.orgUUID = orgUUID;
	}

	/**
	 * 返回变量orgCode
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置变量orgCode
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 返回变量orgName
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置变量orgName
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 返回变量account
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 设置变量account
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	
	/**
	 * 返回变量sessionID
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * 设置变量sessionID
	 * @param sessionID the sessionID to set
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/**
	 * 返回变量levelOneJson
	 * @return the levelOneJson
	 */
	public String getLevelOneJson() {
		return levelOneJson;
	}

	/**
	 * 设置变量levelOneJson
	 * @param levelOneJson the levelOneJson to set
	 */
	public void setLevelOneJson(String levelOneJson) {
		this.levelOneJson = levelOneJson;
	}

	/**
	 * 返回变量levelTwoJson
	 * @return the levelTwoJson
	 */
	public String getLevelTwoJson() {
		return levelTwoJson;
	}

	/**
	 * 设置变量levelTwoJson
	 * @param levelTwoJson the levelTwoJson to set
	 */
	public void setLevelTwoJson(String levelTwoJson) {
		this.levelTwoJson = levelTwoJson;
	}

	/**
	 * 返回变量levelThreeJson
	 * @return the levelThreeJson
	 */
	public String getLevelThreeJson() {
		return levelThreeJson;
	}

	/**
	 * 设置变量levelThreeJson
	 * @param levelThreeJson the levelThreeJson to set
	 */
	public void setLevelThreeJson(String levelThreeJson) {
		this.levelThreeJson = levelThreeJson;
	}

	/**
	 * 返回变量levelFourJson
	 * @return the levelFourJson
	 */
	public String getLevelFourJson() {
		return levelFourJson;
	}

	/**
	 * 设置变量levelFourJson
	 * @param levelFourJson the levelFourJson to set
	 */
	public void setLevelFourJson(String levelFourJson) {
		this.levelFourJson = levelFourJson;
	}

	/**
	 * 返回变量levelFiveJson
	 * @return the levelFiveJson
	 */
	public String getLevelFiveJson() {
		return levelFiveJson;
	}

	/**
	 * 设置变量levelFiveJson
	 * @param levelFiveJson the levelFiveJson to set
	 */
	public void setLevelFiveJson(String levelFiveJson) {
		this.levelFiveJson = levelFiveJson;
	}

	/**
	 * 返回变量roleMap
	 * @return the roleMap
	 */
	public Map<String, String> getRoleMap() {
		return roleMap;
	}

	/**
	 * 设置变量roleMap
	 * @param roleMap the roleMap to set
	 */
	public void setRoleMap(Map<String, String> roleMap) {
		this.roleMap = roleMap;
	}

	/**
	 * 返回变量resourceMap
	 * @return the resourceMap
	 */
	public Map<String, String> getResourceMap() {
		return resourceMap;
	}

	/**
	 * 设置变量resourceMap
	 * @param resourceMap the resourceMap to set
	 */
	public void setResourceMap(Map<String, String> resourceMap) {
		this.resourceMap = resourceMap;
	}
	
	/**
	 * 判断是否拥有某角色
	 * @param roleCode
	 * @return
	 */
	public boolean hasRole(String roleCode){
		return this.roleMap.containsKey(roleCode);
	}
}
