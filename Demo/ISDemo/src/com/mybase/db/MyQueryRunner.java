/**  
 * @title MyQueryRunner.java
 * @package com.mybase.db
 * @date 2013-3-18 下午08:15:48
 */ 
package com.mybase.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.mybase.db.dbutils.RecordHandler;
import com.mybase.db.dbutils.RecordSetHandler;

/**
 * 数据库操作聚合类
 * 数据库各种操作入口-继承自DBUTILS QueryRunner
 * 增加对Record、RecordSet支持
 * @author Mr.liuyong
 */
public class MyQueryRunner extends QueryRunner{
	
	/**
	 * 构造函数 MyQueryRunner.
	 */
	public MyQueryRunner(){}
	
	/**
	 * 根据条件查询，返回值为Record，若结果不唯一，默认返回第一条 
	 * @param conn
	 * @param sql
	 * @return Record
	 * @throws SQLException
	 */
	public Record queryRecord(Connection conn, String sql) throws SQLException{
		return this.query(conn, sql, new RecordHandler());
	}
	
	/**
	 * 根据条件查询，返回值为Record，若结果不唯一，默认返回第一条
	 * @param conn
	 * @param sql
	 * @param params
	 * @return Record
	 * @throws SQLException
	 */
	public Record queryRecord(Connection conn, String sql, Object... params) throws SQLException{
		return this.query(conn, sql, new RecordHandler(), params);
	}
	
	/**
	 * 根据条件查询，返回值为RecordSet
	 * @param conn
	 * @param sql
	 * @return RecordSet
	 * @throws SQLException
	 */
	public RecordSet queryRecordSet(Connection conn, String sql) throws SQLException{
		return this.query(conn, sql, new RecordSetHandler());
	}
	
	/**
	 * 根据条件查询，返回值为RecordSet
	 * @param conn
	 * @param pagingInfo
	 * @param sql
	 * @param params
	 * @return RecordSet
	 * @throws SQLException
	 */
	public RecordSet queryRecordSet(Connection conn, String sql , Object... params) throws SQLException{
		return this.query(conn, sql, new RecordSetHandler(), params);
	}
	
	/**
	 * 根据条件查询，返回值为RecordSet
	 * @param conn
	 * @param pagingInfo
	 * @param sql
	 * @return RecordSet
	 * @throws SQLException
	 */
	public RecordSet queryRecordSet(Connection conn, Pagination pagingInfo ,String sql) throws SQLException{
		//丰富分页信息
		pagingInfo.richInfo(conn, sql,(Object[])null);
		return this.query(conn, pagingInfo.getPageSql(), new RecordSetHandler());
	}
	
	/**
	 * 根据条件查询，返回值为RecordSet
	 * @param conn
	 * @param sql
	 * @param params
	 * @return RecordSet
	 * @throws SQLException
	 */
	public RecordSet queryRecordSet(Connection conn, Pagination pagingInfo , String sql , Object... params) throws SQLException{
		//丰富分页信息
		pagingInfo.richInfo(conn, sql,params);
		return this.query(conn,pagingInfo.getPageSql() , new RecordSetHandler(),params);
	}
	
}
