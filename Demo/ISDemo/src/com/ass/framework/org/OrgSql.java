/**
 * @title OrgSql.java
 * @package com.ass.framework.org
 * @date 2013-7-3 下午03:06:07
 */
package com.ass.framework.org;

import com.mybase.db.Record;
import com.mybase.db.SQLBuilder;

/**
 * 组织管理
 * @author Mr.liuyong
 */
public class OrgSql {
	
	/**
	 * 获取组织树
	 * @return
	 */
	public static SQLBuilder getOrgTree(){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ORGUUID,T.ORGCODE,T.ORGNAME,T.LEVELCODE,T.PID FROM ASS_ORG T WHERE T.DELTAG!=1 ORDER BY T.SORTNUM");
		return sb;
	}
	
	/**
	 * 查询组织列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getOrgList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ORGUUID,T.ORGCODE,T.ORGNAME,T.LEVELCODE,T.MEMO FROM ASS_ORG T WHERE T.DELTAG!=1 ");
		if(paramRecord.isValidate("ORGNAME")){
			sb.append(" AND (T.ORGNAME LIKE ? OR T.ORGCODE LIKE ?)");
			sb.appendValue("%"+paramRecord.getString("ORGNAME")+"%");
			sb.appendValue("%"+paramRecord.getString("ORGNAME")+"%");
		}
		if(paramRecord.isValidate("PID")){
			sb.append(" AND T.PID = ? ");
			sb.appendValue(paramRecord.getString("PID"));
			sb.append(" ORDER BY T.SORTNUM");
		}else{
			sb.append(" AND (T.PID IS NULL OR T.PID ='') ORDER BY T.LEVELCODE,T.SORTNUM");
		}
		return sb;
	}
	
	/**
	 * 根据orgUUID获取组织
	 * @param orgUUID
	 * @return
	 */
	public static SQLBuilder getOrg(String orgUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ORGUUID,T.ORGCODE,T.ORGNAME,T.LEVELCODE,T.PID,T.MEMO,O.ORGNAME PNAME FROM ASS_ORG T LEFT JOIN ASS_ORG O ON O.ORGUUID=T.PID");
		sb.append(" WHERE T.ORGUUID=? ");
		sb.appendValue(orgUUID);
		return sb;
	}
	
	/**
	 * 根据orgUUIDs删除组织
	 * @return
	 */
	public static SQLBuilder deleteOrg(){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" UPDATE ASS_ORG O SET O.DELTAG=1 WHERE O.LEVELCODE LIKE (SELECT CONCAT(T.LEVELCODE,'%') FROM (SELECT * FROM ASS_ORG) T WHERE T.ORGUUID = ?)");
		return sb;
	}
	
	/**
	 * 校验组织代码的唯一性
	 * @param orgCode
	 * @param orgUUID
	 * @return
	 */
	public static SQLBuilder verifyOrgCode(String orgCode,String orgUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.ORGCODE FROM ASS_ORG O WHERE O.ORGCODE= ? ");
		sb.appendValue(orgCode);
		if(orgUUID!=null && !"".equals(orgUUID)){
			sb.append(" AND O.ORGUUID != ? ");
			sb.appendValue(orgUUID);
		}
		return sb;
	}
}
