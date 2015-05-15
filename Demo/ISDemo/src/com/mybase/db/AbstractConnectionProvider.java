/**  
 * @title AbstractConnectionProvider.java
 * @package com.mybase.db
 * @date 2013-3-16 下午11:14:26
 */ 
package com.mybase.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

/**
 * 数据源提供抽象类
 * @author Mr.liuyong
 */
public abstract class AbstractConnectionProvider {
	
	protected static Map<String,DataSource> dataSourceMap = new ConcurrentHashMap<String,DataSource>();
	protected static Map<String,String> dataSourceTypeMap = new ConcurrentHashMap<String,String>();
	
	protected AbstractConnectionProvider(){}
	
	/**
	 * 获取默认配置数据源 数据库类型
	 * @param dbConfigName
	 * @return
	 */
	public String getDBType(String dbConfigName) {
		return dataSourceTypeMap.get(dbConfigName)==null?null:dataSourceTypeMap.get(dbConfigName);
    }
	   
	/**
	 * 获取指定配置数据源
	 * @param dbConfigName
	 * @return Connection
	 * @throws SQLException 
	 */
	public Connection getConnection(String dbConfigName) throws SQLException {
		return dataSourceMap.get(dbConfigName)==null?null:dataSourceMap.get(dbConfigName).getConnection();
    }
}
