/**  
 * @Title: DBConfig.java
 * @Package com.mybase.config
 * @date 2012-11-14 下午05:20:59
 */
package com.mybase.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;

/**
 * 数据库系统基础参数配置管理
 * @author Mr.liuyong
 */
public class DBConfig{
	
	private static Map<String,String> configsSystemMap = new ConcurrentHashMap<String,String>();
	private static List<Record> dbInfoList=new ArrayList<Record>();
    private static DBConfig cu=new DBConfig();
    
    private DBConfig(){}
    
	/**
	 * 得到一个实例
	 * @return
	 */
    public static DBConfig getInstance(){
		if(cu==null) cu= new DBConfig();
		return cu;
	}
    
    /**
     * 根据传入键获取对应配置中的值
     * @param key
     * @return
     */
    public static String getProperty(String key){
		return DBConfig.getProperty(key, "");
	}
    
    /**
     * 根据传入键获取对应配置中的值
     * @param key
     * @return
     */
    public static int getPropertyInt(String key){
    	return Integer.parseInt(DBConfig.getProperty(key, "0"));
    }
    
    /**
     * 根据传入键获取对应配置中的值
     * @param key
     * @return
     */
    public static float getPropertyFloat(String key){
    	return Float.parseFloat(DBConfig.getProperty(key, "0"));
    }
    
    /**
     * 根据传入键获取对应配置中的值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key,String defaultValue){
    	String tem=DBConfig.getInstance().getSystemConfig().get(key);
		return tem==null?defaultValue:tem;
	}
    
	/**
	 * 加载基础参数放入configsSystemMap中
	 * @throws SQLException 
	 */
	public void initConfig(String tableName,String keyColumn,String valueColumn) throws SQLException{
		this.readConfig(false,tableName,keyColumn,valueColumn);	
	}
	
	/**
	 * 重新加载参数
	 * @throws SQLException 
	 */
	public void reloadConfig() throws SQLException{
		configsSystemMap.clear();
		Record record=null;
		for(int i=0;i<dbInfoList.size();i++){
			record=dbInfoList.get(i);
			readConfig(true,record.getString("TABLENAME"),record.getString("KEYCOLUMN"),record.getString("VALUECOLUMN"));
		}
	}
	
    /**
     * 得到基础参数的configsSystemMap
     * @return
     */
    private Map<String,String> getSystemConfig(){
		return configsSystemMap;
	}
	
	/**
	 * 加载基础参数放入configsSystemMap中
	 * @param reloadFlag
	 * @param tableName
	 * @param keyColumn
	 * @param valueColumn
	 * @throws SQLException
	 */
	private void readConfig(boolean reloadFlag,String tableName,String keyColumn,String valueColumn) throws SQLException{
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			RecordSet recordSet=mqr.queryRecordSet(conn,"SELECT "+keyColumn+","+valueColumn+" FROM "+tableName);
			Record record=null;
			for(int i=0;i<recordSet.size();i++){
				record=recordSet.get(i);
				configsSystemMap.put(record.getString(keyColumn,""), record.getString(valueColumn,""));
			}
			if(!reloadFlag){
				Record dbInfo=new Record();
				dbInfo.put("TABLENAME", tableName);
				dbInfo.put("KEYCOLUMN", keyColumn);
				dbInfo.put("VALUECOLUMN", valueColumn);
				dbInfoList.add(dbInfo);
			}
		} catch (SQLException e) {
			throw e;
		}finally{
			DBToolkit.close(conn);
		}	
	}
}
