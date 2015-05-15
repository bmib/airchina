/**  
 * @title ConnectionProvider.java
 * @package com.mybase.db
 * @date 2013-3-16 下午09:41:38
 */ 
package com.mybase.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取数据源配置，提供数据连接，AbstractConnectionProvider子类
 * @author Mr.liuyong
 */
public class ConnectionProvider extends AbstractConnectionProvider{
	
	private static Logger logger = Logger.getLogger(ConnectionProvider.class);
	private static ConnectionProvider instance = null;
	
	/**
	 * 构造函数 DBProvider.
	 */
	private ConnectionProvider() {
		try {
    		//DataBaseConfig.xml为数据库默认配置文件
			Configuration config = new XMLConfiguration("DataBaseConfig.xml");
			//数据源配置类型 JNDI或者C3P0或者 JNDI|C3P0或者C3P0|JNDI
			String configType=config.getString("ConfigType");
			if("JNDI".equals(configType)){
				iniDataSource_JNDI(config);
			}else if("C3P0".equals(configType)){
				iniDataSource_C3P0();
			}else if("JNDI|C3P0".equals(configType)){
				iniDataSource_JNDI(config);
				iniDataSource_C3P0();
			}else if("C3P0|JNDI".equals(configType)){
				iniDataSource_C3P0();
				iniDataSource_JNDI(config);
			}
			//获取数据源对应的数据库类型
			iniDataSourceType();
		} catch (NamingException e) {
			logger.error("查找DataBaseConfig.xml中JNDI数据源",e);
		} catch (ConfigurationException e) {
			logger.error("初始化c3p0-config.xml/DataBaseConfig.xml配置文件",e);
		} catch (SQLException e) {
			logger.error("初始化数据源对应的数据库类型",e);
		}
	}
	
	/**
	 * 从DataBaseConfig.xml配置信息中初始化JNDI数据源配置
	 * @param config
	 * @throws NamingException
	 */
	private void iniDataSource_JNDI(Configuration config) throws NamingException{
		//处理DataBaseConfig.xml中JNDISource配置
		List<Object> nameList = config.getList("DataSourceList.JNDISource[@name]");
		List<Object> jndiNameList = config.getList("DataSourceList.JNDISource[@jndiName]");
		if(nameList!=null && jndiNameList!=null && nameList.size()==jndiNameList.size()){
			String name=null;
			String jndiName=null;
			DataSource datasource;
			Context context= new InitialContext();
			for(int i=0;i<nameList.size();i++){
				name=nameList.get(i).toString();
				jndiName=jndiNameList.get(i).toString();
				datasource=null;
				try{
					datasource =(DataSource) context.lookup(jndiName);
				}catch(Exception e){
					datasource =(DataSource) context.lookup("java:comp/env/" + jndiName);
				}
				dataSourceMap.put(name, datasource);
			}
		}
	}
	
	/**
	 * 从c3p0-config.xml配置文件中读取数据源配置
	 * @throws ConfigurationException
	 */
	private void iniDataSource_C3P0() throws ConfigurationException{
		//c3p0-config.xml为c3p0数据库连接池默认配置文件
		Configuration config = new XMLConfiguration("c3p0-config.xml");
		List<Object> configList = config.getList("default-config.property");
		if(configList.size()>0){
			// 初始化并读取数据库连接信息,c3p0默认
	    	ComboPooledDataSource dataSource = new ComboPooledDataSource();
	    	dataSourceMap.put("DEFAULT", dataSource);
		}
		configList = config.getList("named-config[@name]");
		for(int i=0;configList!=null && i<configList.size();i++){
			String dbConfigName=configList.get(i).toString();
			ComboPooledDataSource dataSource = new ComboPooledDataSource(dbConfigName);
	    	dataSourceMap.put(dbConfigName,dataSource);
		}
	}
	
	/**
	 * 初始化数据源对应的数据库类型
	 * @throws SQLException 
	 */
	private void iniDataSourceType() throws SQLException{
		Connection conn=null;
		String databaseProductName=null;
		Iterator<String> it=dataSourceMap.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			DataSource datasource=dataSourceMap.get(key);
			try {
				conn=datasource.getConnection();
				databaseProductName=conn.getMetaData().getDatabaseProductName().toUpperCase();
				if(databaseProductName.indexOf(DBType.ORACLE) != -1){
					dataSourceTypeMap.put(key, DBType.ORACLE);
				}else if(databaseProductName.indexOf(DBType.DB2) != -1){
					dataSourceTypeMap.put(key, DBType.DB2);
		        }else if(databaseProductName.indexOf(DBType.MYSQL) != -1){
		        	dataSourceTypeMap.put(key, DBType.MYSQL);
		        }else if(databaseProductName.indexOf(DBType.SQL) != -1 ){
		        	dataSourceTypeMap.put(key,DBType.SQL);
		        }else{
		        	dataSourceTypeMap.put(key,DBType.UNKNOW);
		        }
			}finally{
				DBToolkit.close(conn);
			}
		}
	}
	
	/**
	 * 获取DBProvider实例，全局唯一
	 * @return
	 */
    public synchronized static ConnectionProvider getInstance() {
	    if (instance == null){
	    	instance = new ConnectionProvider();
	    }
        return instance;
    }
}
