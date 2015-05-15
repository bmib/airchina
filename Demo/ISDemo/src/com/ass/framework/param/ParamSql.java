/**
 * @title ParamSql.java
 * @package com.ass.framework.param
 * @date 2013-7-3 下午03:06:07
 */
package com.ass.framework.param;

import com.mybase.db.Record;
import com.mybase.db.SQLBuilder;

/**
 * 参数配置管理
 * @author Mr.liuyong
 */
public class ParamSql {
	
	/**
	 * 查询配置参数列表
	 * @param paramRecord
	 * @return
	 */
	public static SQLBuilder getParamList(Record paramRecord){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.UUID,T.PARAMCODE,T.PARAMVALUE,T.MEMO FROM ASS_CONFIG T WHERE 1=1 ");
		if(paramRecord.isValidate("NAME")){
			sb.append(" AND (T.PARAMCODE LIKE ? OR T.PARAMVALUE LIKE ?)");
			sb.appendValue("%"+paramRecord.getString("NAME")+"%");
			sb.appendValue("%"+paramRecord.getString("NAME")+"%");
		}
		sb.append(" ORDER BY T.CREATETIME");
		return sb;
	}
	
	/**
	 * 根据UUID获取配置参数
	 * @param UUID
	 * @return
	 */
	public static SQLBuilder getParam(String UUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT T.UUID,T.PARAMCODE,T.PARAMVALUE,T.MEMO FROM ASS_CONFIG T");
		sb.append(" WHERE T.UUID=? ");
		sb.appendValue(UUID);
		return sb;
	}
	
	/**
	 * 根据UUIDs删除配置参数
	 * @return
	 */
	public static SQLBuilder deleteParam(String UUIDs){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" DELETE FROM ASS_CONFIG WHERE INSTR(?,UUID)>0");
		sb.appendValue(UUIDs);
		return sb;
	}
	
	/**
	 * 校验配置参数代码的唯一性
	 * @param paramCode
	 * @param UUID
	 * @return
	 */
	public static SQLBuilder verifyParamCode(String paramCode,String UUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" SELECT O.PARAMCODE FROM ASS_CONFIG O WHERE O.PARAMCODE= ? ");
		sb.appendValue(paramCode);
		if(UUID!=null && !"".equals(UUID)){
			sb.append(" AND O.UUID != ? ");
			sb.appendValue(UUID);
		}
		return sb;
	}

}
