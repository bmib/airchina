/**
 * @title PersonSql.java
 * @package com.ass.framework.person
 * @date 2013-7-3 下午03:14:53
 */
package com.ass.framework.person;

import com.ass.framework.common.entity.LoginUser;
import com.mybase.db.Record;
import com.mybase.db.SQLBuilder;

/**
 * 人员管理
 * @author Mr.liuyong
 */
public class PersonSql {
	/**
	 * 获取人员列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getPersonList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.PERSONUUID,T.PERSONCODE,T.PERSONNAME,T.SEX,T.IDNUM,T.MOBILE,T.EMAIL,T.MSN_QQ,T.MEMO,PC.ACCOUNT,PC.STATUS,O.ORGNAME FROM ASS_PERSON T ");
		sb.append(" LEFT JOIN ASS_PERSONACCOUNT PC ON PC.PERSONUUID=T.PERSONUUID ");
		sb.append(" LEFT JOIN ASS_ORGPERSON OP ON OP.PERSONUUID=T.PERSONUUID ");
		sb.append(" LEFT JOIN ASS_ORG O ON O.ORGUUID=OP.ORGUUID ");
		sb.append(" WHERE 1=1 ");
		//只有webmaster才能管理webmaster这个超级账号
		LoginUser loginUser=LoginUser.getLoginUser();
		if(!"webmaster".equals(loginUser.getAccount())){
			sb.append(" AND PC.ACCOUNT !='webmaster' ");
		}
		if(paramRecord.isValidate("PLEVELCODE")){
			sb.append(" AND INSTR(O.LEVELCODE,?)>0");
			sb.appendValue(paramRecord.getString("PLEVELCODE"));
		}
		if(paramRecord.isValidate("PERSONNAME")){
			sb.append(" AND (INSTR(T.PERSONNAME,?)>0 OR INSTR(T.PERSONCODE,?)>0 OR INSTR(PC.ACCOUNT,?)>0)");
			sb.appendValue(paramRecord.getString("PERSONNAME"));
			sb.appendValue(paramRecord.getString("PERSONNAME"));
			sb.appendValue(paramRecord.getString("PERSONNAME"));
		}
		if(paramRecord.isValidate("STATUS")){
			sb.append(" AND PC.STATUS= ? ");
			sb.appendValue(paramRecord.getString("STATUS"));
		}
		sb.append(" ORDER BY T.UPDATETIME DESC");
		return sb;
	}
	
	/**
	 * 根据personUUID获取人员
	 * @param personUUID
	 * @return
	 */
	public static SQLBuilder getPerson(String personUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.PERSONUUID,T.PERSONCODE,T.PERSONNAME,T.SEX,T.IDNUM,T.MOBILE,T.EMAIL,T.MSN_QQ,T.MEMO,PC.ACCOUNT,PC.STATUS,O.ORGNAME,O.ORGUUID FROM ASS_PERSON T ");
		sb.append(" LEFT JOIN ASS_PERSONACCOUNT PC ON PC.PERSONUUID=T.PERSONUUID ");
		sb.append(" LEFT JOIN ASS_ORGPERSON OP ON OP.PERSONUUID=T.PERSONUUID ");
		sb.append(" LEFT JOIN ASS_ORG O ON O.ORGUUID=OP.ORGUUID ");
		sb.append(" WHERE T.PERSONUUID=? ");
		sb.appendValue(personUUID);
		return sb;
	}
	
	/**
	 * 根据personUUIDs获取多个人员
	 * @param personUUIDs
	 * @return
	 */
	public static SQLBuilder getPersonMore(String personUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.PERSONUUID,T.PERSONCODE,T.PERSONNAME,PC.ACCOUNT FROM ASS_PERSON T ");
		sb.append(" LEFT JOIN ASS_PERSONACCOUNT PC ON PC.PERSONUUID=T.PERSONUUID ");
		sb.append(" WHERE INSTR(?,T.PERSONUUID)>0 ");
		sb.appendValue(personUUIDs);
		return sb;
	}
	
	/**
	 * 删除人员
	 * @param personUUIDs
	 * @return
	 */
	public static SQLBuilder deletePerson(String personUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_PERSON WHERE INSTR(?,PERSONUUID)>0");
		sb.appendValue(personUUIDs);
		return sb;
	}
	
	/**
	 *  获取多个人员共有的角色或用户组
	 * @param personUUIDs
	 * @return
	 */
	public static SQLBuilder getPersonsCommonRightList(String personUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append("SELECT RES.RIGHTUUID FROM (");
		sb.append("SELECT AL.RIGHTUUID, COUNT(*) CC FROM (");
		sb.append("SELECT CASE WHEN T.ROLEUUID IS NULL THEN T.USERGROUPUUID ELSE T.ROLEUUID END RIGHTUUID FROM ASS_PERSONRIGHT T WHERE INSTR(?, T.PERSONUUID) > 0) AL GROUP BY AL.RIGHTUUID");
		sb.append(") RES WHERE RES.CC = ? ");
		sb.appendValue(personUUIDs);
		sb.appendValue(personUUIDs.split("@").length);
		return sb;
	}
	
	
	
	/**
	 *  删除人员的某角色或某用户组
	 * @param type
	 * @param personUUIDs
	 * @param rightUUID
	 * @return
	 */
	public static SQLBuilder deleteRight(String type,String personUUIDs,String rightUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		if("ROLE".equals(type)){
			sb.append(" DELETE FROM ASS_PERSONRIGHT WHERE INSTR(?, PERSONUUID)>0 AND ROLEUUID = ? ");
			sb.appendValue(personUUIDs);
			sb.appendValue(rightUUID);
		}else{
			sb.append(" DELETE FROM ASS_PERSONRIGHT WHERE INSTR(?, PERSONUUID)>0 AND USERGROUPUUID = ? ");
			sb.appendValue(personUUIDs);
			sb.appendValue(rightUUID);
		}
		return sb;
	}
	
	/**
	 * 校验人员编码的唯一性
	 * @param personCode
	 * @param personUUID
	 * @return
	 */
	public static SQLBuilder verifyPersonCode(String personCode,String personUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.PERSONCODE FROM ASS_PERSON O WHERE O.PERSONCODE= ? ");
		sb.appendValue(personCode);
		if(personUUID!=null && !"".equals(personUUID)){
			sb.append(" AND O.PERSONUUID != ? ");
			sb.appendValue(personUUID);
		}
		return sb;
	}
	
	/**
	 * 校验人员账号的唯一性
	 * @param account
	 * @param personUUID
	 * @return
	 */
	public static SQLBuilder verifyAccount(String account,String personUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.ACCOUNT FROM ASS_PERSONACCOUNT O WHERE O.ACCOUNT= ? ");
		sb.appendValue(account);
		if(personUUID!=null && !"".equals(personUUID)){
			sb.append(" AND O.PERSONUUID != ? ");
			sb.appendValue(personUUID);
		}
		return sb;
	}

}
