/**
 * @title SQLAdapter.java
 * @package com.mybase.db
 * @date 2013-7-5 下午05:10:53
 */
package com.mybase.db;

import org.apache.log4j.Logger;


/**
 * SQL层辅助类,根据数据库类型适配相应的处理类
 * @author Mr.liuyong
 */
public class SQLAdapter {
	
	private static Logger logger = Logger.getLogger(SQLAdapter.class);
	
	/**
	 * 获取默认数据库连接对应的SQL处理类
	 * @param o
	 * @return
	 */
	public static Object get(Class<?> o){
		return SQLAdapter.get("DEFAULT", o);
	}
	
	/**
	 * 获取指定数据库连接对应的SQL处理类
	 * @param dbConfigName
	 * @param o
	 * @return
	 */
	public static Object get(String dbConfigName,Class<?> o){
		Class<?> c=null;
		String classPathName=o.getName();
		String dbType=DBToolkit.getDBType(dbConfigName);
		if(DBType.ORACLE.equals(dbType)){
			classPathName=classPathName+DBType.ORACLE;
		}else if(DBType.DB2.equals(dbType)){
			classPathName=classPathName+DBType.DB2;
		}else if(DBType.MYSQL.equals(dbType)){
			classPathName=classPathName+DBType.MYSQL;
		}else if(DBType.SQL.equals(dbType)){
			classPathName=classPathName+DBType.SQL;
		}
		try {
			c=Class.forName(classPathName);
		} catch (ClassNotFoundException e) {
			try {
				c=Class.forName(o.getName());
			} catch (ClassNotFoundException e1) {
				logger.error("未找到合适的SQL处理类"+o.getName(),e1);
				throw new RuntimeException("未找到合适的SQL处理类"+o.getName(),e1);
			}
		}
		try {
			return c==null?null:c.newInstance();
		} catch (Exception e) {
			logger.error("实例化SQL处理类对象"+o.getName(),e);
			throw new RuntimeException("实例化SQL处理类对象"+o.getName(),e);
		}
	}
}
