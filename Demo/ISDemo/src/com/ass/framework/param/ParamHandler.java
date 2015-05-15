/**
 * @title ParamHandler.java
 * @package com.ass.framework.param
 * @date 2013-7-3 下午03:04:24
 */
package com.ass.framework.param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.mybase.config.DBConfig;
import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Pagination;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.db.SQLBuilder;
import com.mybase.util.DateTimeUtil;

/**
 * 参数配置管理
 * @author Mr.liuyong
 */
public class ParamHandler {
	
	private static Logger logger = Logger.getLogger(ParamHandler.class);
	
	/**
	 * 查询参数配置列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getParamList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ParamSql.getParamList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("查询参数配置列表",e);
			throw new RuntimeException("查询参数配置列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据UUID获取参数配置对象
	 * @param UUID
	 * @return
	 */
	public Record getParam(String UUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ParamSql.getParam(UUID);
			return mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据UUID获取参数配置对象",e);
			throw new RuntimeException("根据UUID获取参数配置对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 新增参数配置
	 * @param storeRecord
	 * @return
	 */
	public boolean createParam(Record storeRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			storeRecord.setConnection(conn);
			storeRecord.setTableName("ASS_CONFIG");
			storeRecord.put("UUID",UUID.randomUUID().toString());
			storeRecord.put("CREATETIME",DateTimeUtil.getCurTimestamp());
			storeRecord.create();
			return true;
		} catch (SQLException e) {
			logger.error("新增参数配置",e);
			throw new RuntimeException("新增参数配置",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 修改参数配置
	 * @param storeRecord
	 * @return
	 */
	public boolean modifyParam(Record storeRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			storeRecord.setConnection(conn);
			storeRecord.addPrimaryKey("UUID");
			storeRecord.setTableName("ASS_CONFIG");
			storeRecord.update();
			return true;
		} catch (SQLException e) {
			logger.error("修改参数配置",e);
			throw new RuntimeException("修改参数配置",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据UUIDs删除参数配置
	 * @param UUIDs
	 * @return
	 */
	public boolean deleteParam(String UUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ParamSql.deleteParam(UUIDs);
			mqr.update(conn, sb.toSqlString(), sb.toValueArray());
			return true;
		} catch (SQLException e) {
			logger.error("根据UUIDs删除参数配置",e);
			throw new RuntimeException("根据UUIDs删除参数配置",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断paramCode的唯一性
	 * @param paramCode
	 * @param UUID
	 * @return
	 */
	public boolean verifyParamCode(String paramCode,String UUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ParamSql.verifyParamCode(paramCode,UUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("PARAMCODE",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断paramCode的唯一性",e);
			throw new RuntimeException("判断paramCode的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}	
	
	/**
	 * 重载参数
	 * @return
	 */
	public boolean reloadParam(){
		try {
			DBConfig.getInstance().reloadConfig();
			return true;
		} catch (SQLException e) {
			logger.error("重载DB参数",e);
			throw new RuntimeException("重载DB参数",e);
		}
	}
}
