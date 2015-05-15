/**
 * @title DBToolkit.java
 * @package com.mybase.db
 * @date 2013-6-14 下午07:54:37
 */
package com.mybase.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 数据源操作工具类
 * @author Mr.liuyong
 */
public class DBToolkit {
	
	private static Logger logger = Logger.getLogger(DBToolkit.class);
	
	/**
	 * 获取默认配置的数据库 类型
	 * @return
	 */
	public static String getDBType(){
		return DBToolkit.getDBType("DEFAULT");
	}
	
	/**
	 * 获取默认配置的数据库 类型
	 * @param dbConfigName
	 * @return
	 */
	public static String getDBType(String dbConfigName){
		return ConnectionProvider.getInstance().getDBType(dbConfigName);
	}
	
	/**
	 * 获取默认配置的数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		return DBToolkit.getConnection("DEFAULT");
	}
	
	/**
	 * 获取指定配置的数据库连接
	 * @param dbConfigName
	 * @return
	 */
	public static Connection getConnection(String dbConfigName){
		try {
			return ConnectionProvider.getInstance().getConnection(dbConfigName);
		} catch (SQLException e) {
			logger.error("获取默认配置的数据库连接"+dbConfigName,e);
			return null;
		}
	}
	
	/**
	 * 释放Connection、Statement、ResultSet
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			freeRs(rs);
			freeStmt(stmt);
			freeConn(conn);
		} catch (SQLException e) {
			logger.error("释放资源", e);
		}
	}

	/**
	 * 释放Connection
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			freeConn(conn);
		} catch (SQLException e) {
			logger.error("释放数据库连接资源", e);
		}
	}
	
	/**
	 * 释放ResultSet
	 * @param rs
	 */
	private static void freeRs(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
	
	/**
	 * 释放Statement
	 * @param stmt
	 */
	private static void freeStmt(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * 释放Connection
	 * @param conn
	 */
	private static void freeConn(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
}
