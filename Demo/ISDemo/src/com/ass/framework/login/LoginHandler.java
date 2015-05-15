/**
 * @title LoginHandler.java
 * @package com.ass.framework.login
 * @date 2013-7-3 下午03:21:49
 */
package com.ass.framework.login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.ass.framework.common.entity.LoginUser;
import com.ass.framework.common.exception.LoginException;
import com.mybase.config.DBConfig;
import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.db.SQLBuilder;
import com.mybase.util.UrlUtil;

/**
 * 登录管理
 * @author Mr.liuyong
 */
public class LoginHandler {
	
	private static Logger logger = Logger.getLogger(LoginHandler.class);
	
	/**
	 * 系统登录
	 * @param account
	 * @param password
	 * @return
	 */
	public String login(String account,String password){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=LoginSql.verify(account);
			Record record=mqr.queryRecord(conn,sb.toSqlString(),sb.toValueArray());
			String personUUID=record.getString("PERSONUUID","");
			//判断登录用户是否存在
			if(personUUID==""){
				throw new LoginException("NOTFOUND");
			}
			//判断账号状态是否正常
			if(record.getString("STATUS","1").equals("1")){
				throw new LoginException("LOCKED");
			}
			//判断密码是否正确
			if(!record.getString("PASSWORD","ERROR").equals(DigestUtils.md5Hex("ISF"+password))){
				Record updateRecord=new Record(conn,"ASS_PERSONACCOUNT");
				updateRecord.addPrimaryKey("ACCOUNT");
				updateRecord.put("ACCOUNT", account);
				updateRecord.put("FAILNUM", record.getInt("FAILNUM")+1);
				updateRecord.put("STATUS", updateRecord.getInt("FAILNUM")>=DBConfig.getPropertyInt("NUM_TO_LOCK")?"1":"0");
				updateRecord.update();
				throw new LoginException("WRONGPASSWORD");
			}
			//登录成功，清空登录失败次数
			if(record.getInt("FAILNUM")!=0){
				Record updateRecord=new Record(conn,"ASS_PERSONACCOUNT");
				updateRecord.addPrimaryKey("ACCOUNT");
				updateRecord.put("ACCOUNT", account);
				updateRecord.put("FAILNUM", 0);
				updateRecord.update();
			}
			return personUUID;
		} catch (SQLException e) {
			logger.error("系统登录",e);
			throw new LoginException("UNKNOW");
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 初始化系统菜单,一级、二级、三级、四级、五级
	 * @param personUUID
	 * @return
	 */
	public LoginUser iniUserData(String personUUID){
		LoginUser loginUser=LoginUser.getLoginUser();
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			//第一部分：用户基本信息
			SQLBuilder sb=LoginSql.getPerson(personUUID);
			Record personRecord=mqr.queryRecord(conn,sb.toSqlString(),sb.toValueArray());
			loginUser.setPersonUUID(personRecord.getString("PERSONUUID",""));
			loginUser.setPersonCode(personRecord.getString("PERSONCODE",""));
			loginUser.setPersonName(personRecord.getString("PERSONNAME",""));
			loginUser.setSex(personRecord.getString("SEX",""));
			loginUser.setIdNum(personRecord.getString("IDNUM",""));
			loginUser.setMobile(personRecord.getString("MOBILE",""));
			loginUser.setEmail(personRecord.getString("EMAIL",""));
			loginUser.setMSN_QQ(personRecord.getString("MSNQQ",""));
			loginUser.setOrgUUID(personRecord.getString("ORGUUID",""));
			loginUser.setOrgCode(personRecord.getString("ORGCODE",""));
			loginUser.setOrgName(personRecord.getString("ORGNAME",""));
			loginUser.setAccount(personRecord.getString("ACCOUNT",""));
			//第二部分：用户权限，资源信息
			sb=LoginSql.getPersonRightResource(personUUID);
			RecordSet rs=mqr.queryRecordSet(conn,sb.toSqlString(),sb.toValueArray());
			//菜单数据
			RecordSet levelOne=new RecordSet();
			RecordSet levelTwo=new RecordSet();
			RecordSet levelThree=new RecordSet();
			RecordSet levelFour=new RecordSet();
			RecordSet levelFive=new RecordSet();
			//父节点集合
			Map<String,Object> parentMap=new HashMap<String,Object>();
			
			//用户角色集合<角色代码,角色名称>
			Map<String,String> roleMap=new HashMap<String,String>();
			//用户功能资源集合<资源标识码,"">
			Map<String,String> resourceMap=new HashMap<String,String>();
			
			for(int i=0;i<rs.size();i++){
				Record record=rs.get(i);
				roleMap.put(record.getString("ROLECODE","-"), record.getString("ROLENAME","-"));
				resourceMap.put(UrlUtil.getUrlResourceIdentifier(record.getString("PAGEURL","")),"");
				//每一级排在第一个的模块为默认
				if(!parentMap.containsKey(record.getString("PID","ROOT"))){
					parentMap.put(record.getString("PID","ROOT"),null);
					record.put("SELECTED", true);
				}
				//处理掉不在菜单中显示的资源
				if("0".equals(record.getString("MENU","1"))){
					continue;
				}
				if(record.getString("LEVELCODE","").length()==4){
					levelOne.add(record);
				}else if(record.getString("LEVELCODE","").length()==8){
					levelTwo.add(record);
				}else if(record.getString("LEVELCODE","").length()==12){
					levelThree.add(record);
				}else if(record.getString("LEVELCODE","").length()==16){
					levelFour.add(record);
				}else if(record.getString("LEVELCODE","").length()==20){
					levelFive.add(record);
				}
			}
			loginUser.setRoleMap(roleMap);
			loginUser.setResourceMap(resourceMap);
			loginUser.setLevelOneJson(levelOne.toJsonString());
			loginUser.setLevelTwoJson(levelTwo.toJsonString());
			loginUser.setLevelThreeJson(levelThree.toJsonString());
			loginUser.setLevelFourJson(levelFour.toJsonString());
			loginUser.setLevelFiveJson(levelFive.toJsonString());
		} catch (SQLException e) {
			logger.error("初始化系统菜单",e);
			throw new RuntimeException("初始化系统菜单",e);
		}finally{
			DBToolkit.close(conn);
		}
		return loginUser;
	}
	
}
