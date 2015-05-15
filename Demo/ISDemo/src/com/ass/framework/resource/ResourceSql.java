/**
 * @title ResourceSql.java
 * @package com.ass.framework.resource
 * @date 2013-7-3 下午03:17:42
 */
package com.ass.framework.resource;

import com.mybase.db.Record;
import com.mybase.db.SQLBuilder;

/**
 * 资源管理
 * @author Mr.liuyong
 */
public class ResourceSql {
	/**
	 * 获取资源树
	 * @return
	 */
	public static SQLBuilder getResourceTree(){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.RESOURCEUUID,T.RESOURCECODE,T.RESOURCENAME,T.LEVELCODE,T.PID FROM ASS_RESOURCE T ORDER BY T.SORTNUM");
		return sb;
	}
	
	/**
	 * 查询资源列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getResourceList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.RESOURCEUUID,T.RESOURCECODE,T.RESOURCENAME,T.LEVELCODE,T.PID,T.MENU,T.CONTROL,T.MEMO,T.URL,T.LEFTURL FROM ASS_RESOURCE T WHERE 1=1 ");
		if(paramRecord.isValidate("RESOURCENAME")){
			sb.append(" AND (T.RESOURCENAME LIKE ? OR T.RESOURCECODE LIKE ?)");
			sb.appendValue("%"+paramRecord.getString("RESOURCENAME")+"%");
			sb.appendValue("%"+paramRecord.getString("RESOURCENAME")+"%");
		}
		if(paramRecord.isValidate("PID")){
			sb.append(" AND T.PID = ? ");
			sb.appendValue(paramRecord.getString("PID"));
			sb.append(" ORDER BY T.SORTNUM");
		}else{
			sb.append(" AND (T.PID IS NULL OR T.PID ='') ORDER BY T.SORTNUM");
		}
		return sb;
	}	
	
	/**
	 * 根据resourceUUID获取资源详情
	 * @param resourceUUID
	 * @return
	 */
	public static SQLBuilder getResource(String resourceUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.RESOURCEUUID,T.RESOURCECODE,T.RESOURCENAME,T.LEVELCODE,T.PID,T.URL,T.LEFTURL,T.MENU,T.CONTROL,T.MEMO,O.RESOURCENAME PNAME FROM ASS_RESOURCE T LEFT JOIN ASS_RESOURCE O ON O.RESOURCEUUID=T.PID");
		sb.append(" WHERE T.RESOURCEUUID=? ");
		sb.appendValue(resourceUUID);
		return sb;
	}
	
	/**
	 * 删除资源
	 * @return
	 */
	public static SQLBuilder deleteResource(){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_RESOURCE WHERE LEVELCODE LIKE (SELECT CONCAT(T.LEVELCODE,'%') FROM (SELECT * FROM ASS_RESOURCE) T WHERE T.RESOURCEUUID = ?)");
		return sb;
	}
	
	/**
	 * 校验资源代码的唯一性
	 * @param resourceCode
	 * @param resourceUUID
	 * @return
	 */
	public static SQLBuilder verifyResourceCode(String resourceCode,String resourceUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.RESOURCECODE FROM ASS_RESOURCE O WHERE O.RESOURCECODE= ? ");
		sb.appendValue(resourceCode);
		if(resourceUUID!=null && !"".equals(resourceUUID)){
			sb.append(" AND O.RESOURCEUUID != ? ");
			sb.appendValue(resourceUUID);
		}
		return sb;
	}

}
