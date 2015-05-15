/**
 * @title RightSql.java
 * @package com.ass.framework.right
 * @date 2013-7-3 下午03:20:21
 */
package com.ass.framework.right;

import com.mybase.db.Record;
import com.mybase.db.SQLBuilder;

/**
 * 权限管理
 * @author Mr.liuyong
 */
public class RightSql {
	/**
	 * 查询角色列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getRoleList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ROLEUUID,T.ROLECODE,T.ROLENAME,T.MEMO FROM ASS_ROLE T ");
		sb.append(" WHERE 1=1 ");
		if(paramRecord.isValidate("NAME")){
			sb.append(" AND (INSTR(T.ROLENAME,?)>0 OR INSTR(T.ROLECODE,?)>0)");
			sb.appendValue(paramRecord.getString("NAME"));
			sb.appendValue(paramRecord.getString("NAME"));
		}
		sb.append(" ORDER BY T.UPDATETIME DESC");
		return sb;
	}
	
	/**
	 * 查询用户组列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getUsergroupList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.USERGROUPUUID,T.USERGROUPCODE,T.USERGROUPNAME,T.MEMO FROM ASS_USERGROUP T ");
		sb.append(" WHERE 1=1 ");
		if(paramRecord.isValidate("NAME")){
			sb.append(" AND (INSTR(T.USERGROUPNAME,?)>0 OR INSTR(T.USERGROUPCODE,?)>0)");
			sb.appendValue(paramRecord.getString("NAME"));
			sb.appendValue(paramRecord.getString("NAME"));
		}
		sb.append(" ORDER BY T.UPDATETIME DESC");
		return sb;
	}
	
	/**
	 * 根据roleUUID获取角色对象
	 * @param roleUUID
	 * @return
	 */
	public static SQLBuilder getRole(String roleUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ROLEUUID,T.ROLECODE,T.ROLENAME,T.MEMO,T.UPDATETIME FROM ASS_ROLE T ");
		sb.append(" WHERE T.ROLEUUID = ? ");
		sb.appendValue(roleUUID);
		return sb;
	}
	
	/**
	 * 根据usergroupUUID获取角色对象
	 * @param usergroupUUID
	 * @return
	 */
	public static SQLBuilder getUsergroup(String usergroupUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.USERGROUPUUID,T.USERGROUPCODE,T.USERGROUPNAME,T.MEMO,T.UPDATETIME FROM ASS_USERGROUP T ");
		sb.append(" WHERE T.USERGROUPUUID = ? ");
		sb.appendValue(usergroupUUID);
		return sb;
	}
	
	/**
	 * 根据roleUUIDs获取多个角色
	 * @param roleUUIDs
	 * @return
	 */
	public static SQLBuilder getRoleMore(String roleUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ROLEUUID,T.ROLECODE,T.ROLENAME FROM ASS_ROLE T ");
		sb.append(" WHERE INSTR(?,T.ROLEUUID)>0 ");
		sb.appendValue(roleUUIDs);
		sb.append(" ORDER BY T.UPDATETIME DESC");
		return sb;
	}
	
	/**
	 * 根据usergroupUUIDs获取多个用户组
	 * @param usergroupUUIDs
	 * @return
	 */
	public static SQLBuilder getUsergroupMore(String usergroupUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.USERGROUPUUID,T.USERGROUPCODE,T.USERGROUPNAME FROM ASS_USERGROUP T ");
		sb.append(" WHERE INSTR(?,T.USERGROUPUUID)>0 ");
		sb.appendValue(usergroupUUIDs);
		sb.append(" ORDER BY T.UPDATETIME DESC");
		return sb;
	}
	
	/**
	 * 根据roleUUIDs删除角色
	 * @param roleUUIDs
	 * @return
	 */
	public static SQLBuilder deleteRole(String roleUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_ROLE WHERE INSTR(?,ROLEUUID)>0");
		sb.appendValue(roleUUIDs);
		return sb;
	}
	
	/**
	 * 根据usergroupUUIDs删除用户组
	 * @param usergroupUUIDs
	 * @return
	 */
	public static SQLBuilder deleteUsergroup(String usergroupUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_USERGROUP WHERE INSTR(?,USERGROUPUUID)>0");
		sb.appendValue(usergroupUUIDs);
		return sb;
	}
	
	/**
	 * 获取全部角色或全部用户组列表
	 * @param type
	 * @return
	 */
	public static SQLBuilder getAllRightList(String type){
		SQLBuilder sb=SQLBuilder.getInstance();
		if("ROLE".equals(type)){
			sb.append(" SELECT T.ROLEUUID UUID,T.ROLECODE CODE,T.ROLENAME NAME,'ROLE' type FROM ASS_ROLE T ");
			sb.append(" ORDER BY T.UPDATETIME DESC");
		}else{
			sb.append(" SELECT T.USERGROUPUUID UUID,T.USERGROUPCODE CODE,T.USERGROUPNAME NAME,'USERGROUP' type FROM ASS_USERGROUP T ");
			sb.append(" ORDER BY T.UPDATETIME DESC");
		}
		return sb;
	}
	
	/**
	 * 获取角色对应的资源列表
	 * @param roleUUID
	 * @return
	 */
	public static SQLBuilder getRoleResourceList(String roleUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.ROLEUUID,T.RESOURCEUUID FROM ASS_ROLERESOURCE T ");
		sb.append(" WHERE T.ROLEUUID=? ");
		sb.appendValue(roleUUID);
		return sb;
	}
	
	/**
	 *  根据usergroupUUID获取对应的角色
	 * @param usergroupUUID
	 * @return
	 */
	public static SQLBuilder getUsergroupRightList(String usergroupUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.USERGROUPUUID,T.ROLEUUID RIGHTUUID FROM ASS_USERGROUPROLE T WHERE T.USERGROUPUUID=? ");
		sb.appendValue(usergroupUUID);
		return sb;
	}
	
	/**
	 *  删除某用户组的角色
	 * @param usergroupUUID
	 * @param rightUUID
	 * @return
	 */
	public static SQLBuilder deleteUsergroupRole(String usergroupUUID,String rightUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_USERGROUPROLE WHERE USERGROUPUUID = ? and ROLEUUID=?");
		sb.appendValue(usergroupUUID);
		sb.appendValue(rightUUID);
		return sb;
	}
	
	/**
	 *  删除角色的资源
	 * @param roleUUIDs
	 * @return
	 */
	public static SQLBuilder deleteRoleResource(String roleUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_ROLERESOURCE WHERE INSTR(?,ROLEUUID)>0 ");
		sb.appendValue(roleUUIDs);
		return sb;
	}
	
	/**
	 *  给角色赋资源
	 * @param roleUUID
	 * @param resourceUUIDs
	 * @return
	 */
	public static SQLBuilder assignRoleResource(String roleUUID,String resourceUUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" INSERT INTO ASS_ROLERESOURCE(RESOURCEUUID,ROLEUUID) SELECT T.RESOURCEUUID,? FROM ASS_RESOURCE T WHERE INSTR(?,T.RESOURCEUUID)>0 ");
		sb.appendValue(roleUUID);
		sb.appendValue(resourceUUIDs);
		return sb;
	}
	
	/**
	 * 人员角色权限维护列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getRightPersonList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.PERSONUUID,T.PERSONCODE,T.PERSONNAME,T.SEX,T.IDNUM,T.MOBILE,T.EMAIL,T.MSN_QQ,T.MEMO,PC.ACCOUNT,PC.STATUS,O.ORGNAME FROM ASS_PERSON T ");
		sb.append(" left join ASS_PERSONACCOUNT PC ON PC.PERSONUUID=T.PERSONUUID ");
		sb.append(" LEFT JOIN ASS_ORGPERSON OP ON OP.PERSONUUID=T.PERSONUUID ");
		sb.append(" LEFT JOIN ASS_ORG O ON O.ORGUUID=OP.ORGUUID ");
		sb.append(" WHERE 1=1 ");
		if(paramRecord.isValidate("PERSONNAME")){
			sb.append(" AND (INSTR(T.PERSONNAME,?)>0 OR INSTR(T.PERSONCODE,?)>0 OR INSTR(PC.ACCOUNT,?)>0)");
			sb.appendValue(paramRecord.getString("PERSONNAME"));
			sb.appendValue(paramRecord.getString("PERSONNAME"));
			sb.appendValue(paramRecord.getString("PERSONNAME"));
		}
		sb.append(" ORDER BY T.UPDATETIME DESC");
		return sb;
	}
	
	/**
	 * 校验角色代码的唯一性
	 * @param roleCode
	 * @param roleUUID
	 * @return
	 */
	public static SQLBuilder verifyRoleCode(String roleCode,String roleUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.ROLECODE FROM ASS_ROLE O WHERE O.ROLECODE= ? ");
		sb.appendValue(roleCode);
		if(roleUUID!=null && !"".equals(roleUUID)){
			sb.append(" AND O.ROLEUUID != ? ");
			sb.appendValue(roleUUID);
		}
		return sb;
	}
	
	/**
	 * 校验用户组的唯一性
	 * @param usergroupCode
	 * @param usergroupUUID
	 * @return
	 */
	public static SQLBuilder verifyUsergroupCode(String usergroupCode,String usergroupUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.USERGROUPCODE FROM ASS_USERGROUP O WHERE O.USERGROUPCODE= ? ");
		sb.appendValue(usergroupCode);
		if(usergroupUUID!=null && !"".equals(usergroupUUID)){
			sb.append(" AND O.USERGROUPUUID != ? ");
			sb.appendValue(usergroupUUID);
		}
		return sb;
	}
}
