/**
 * @title LoginSql.java
 * @package com.ass.framework.login
 * @date 2013-7-3 下午03:22:16
 */
package com.ass.framework.login;

import com.mybase.db.SQLBuilder;

/**
 * 登录管理
 * @author Mr.liuyong
 */
public class LoginSql {
	
	/**
	 * 用户名及密码校验
	 * @param account
	 * @param password
	 * @return
	 */
	public static SQLBuilder verify(String account){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.PERSONUUID,A.ACCOUNT,A.PASSWORD,A.STATUS,A.FAILNUM FROM ASS_PERSON T LEFT JOIN ASS_PERSONACCOUNT A ON A.PERSONUUID=T.PERSONUUID");
		sb.append(" WHERE A.ACCOUNT=?");
		sb.appendValue(account);
		return sb;
	}
	
	/**
	 * 初始化用户基本数据
	 * @param account
	 * @param password
	 * @return
	 */
	public static SQLBuilder getPerson(String personUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.PERSONUUID,T.PERSONCODE,T.PERSONNAME,T.SEX,T.IDNUM,T.MOBILE,T.EMAIL,T.MSN_QQ,PA.ACCOUNT,O.ORGUUID,O.ORGCODE,O.ORGNAME FROM ASS_PERSON T ");
		sb.append(" LEFT JOIN ASS_PERSONACCOUNT PA ON PA.PERSONUUID=T.PERSONUUID");
		sb.append(" LEFT JOIN ASS_ORGPERSON OP ON OP.PERSONUUID=T.PERSONUUID");
		sb.append(" LEFT JOIN ASS_ORG O ON OP.ORGUUID=O.ORGUUID");
		sb.append(" WHERE T.PERSONUUID=?");
		sb.appendValue(personUUID);
		return sb;
	}
	
	/**
	 * 获取单个人员的权限及资源
	 * @param personUUID
	 * @return
	 */
	public static SQLBuilder getPersonRightResource(String personUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ROLEUUID,T.ROLECODE,T.ROLENAME,T.RESOURCEUUID UUID,T.RESOURCECODE,T.RESOURCENAME NAME,T.PID,T.LEVELCODE,T.MENU,T.CONTROL,T.URL PAGEURL,T.LEFTURL LEFTPAGEURL");
		sb.append(" FROM ASS_V_PERSONRIGHTRESOURCE T WHERE T.PERSONUUID=? OR T.PERSONUUID IS NULL ORDER BY T.SORTNUM");
		sb.appendValue(personUUID);
		return sb;
	}
	
}
