/**  
 * @title Record.java
 * @package com.mybase.db
 * @date 2013-3-17 下午06:16:34
 */ 
package com.mybase.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.simple.JSONValue;

import com.mybase.util.StringUtil;

/**
 * 单实体，一般对应数据库中一条记录，继承自HashMap
 * @author Mr.liuyong
 */
public class Record extends HashMap<String,Object>{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Record.class);
	
	//数据库链接
	Connection conn=null;
	//表名
	private String tableName;
	//主键
	private List<String> primaryKeyList = new ArrayList<String>();
	//Map 各value值 List
	private List<Object> valueList = new ArrayList<Object>();
	
	/**
	 * 构造函数 Record.
	 */
	public Record(){}
	
	/**
	 * 构造函数 Record.
	 * @param tableName
	 */
	public Record(String tableName){
		this.tableName=tableName;
	}
	
	/**
	 * 构造函数 Record.
	 * @param conn
	 * @param tableName
	 */
	public Record(Connection conn,String tableName){
		this.conn=conn;
		this.tableName=tableName;
	}
	
	/**
	 * 构造函数 Record.
	 * @param conn
	 * @param tableName
	 * @param primaryKey
	 */
	public Record(Connection conn,String tableName,String primaryKey){
		this.conn=conn;
		this.tableName=tableName;
		this.primaryKeyList.add(primaryKey);
	}
	
	/**
	 * 构造函数 Record.
	 * @param tableName
	 * @param primaryKey
	 */
	public Record(String tableName,String primaryKey){
		this.tableName=tableName;
		this.primaryKeyList.add(primaryKey);
	}
	
	/**
	 * 设置数据库连接
	 * @param conn
	 */
	public void setConnection(Connection conn){
		this.conn=conn;
	}
	
	/**
	 * 设置表名
	 * @param tableName
	 */
	public void setTableName(String tableName){
		this.tableName=tableName;
	}
	
	/**
	 * 增加主键项
	 * @param primaryKey
	 */
	public void addPrimaryKey(String primaryKey){
		this.primaryKeyList.add(primaryKey);
	}
	
	/**
	 * 增加主键项
	 * @param primaryKeyArray
	 */
	public void addPrimaryKey(String[] primaryKeyArray){
		for(int i=0;i<primaryKeyArray.length;i++){
			this.primaryKeyList.add(primaryKeyArray[i]);
		}
	}
	
	/**
	 * 保存记录
	 * @return
	 * @throws SQLException 
	 */
	public int create() throws SQLException{
		MyQueryRunner mqr = new MyQueryRunner();
		String sql=this.toCreateSql();
		Object[] tem=this.toValueArray();
		logger.debug("SQL语句:"+sql);
		logger.debug("参数值:"+this.toJsonString());
		if(tem.length>0){
			return mqr.update(conn, sql, tem);
		}else{
			return mqr.update(conn, sql);
		}
	}
	
	/**
	 * 生成插入SQL
	 * @return String
	 */
	public String toCreateSql(){
		valueList=new ArrayList<Object>();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<primaryKeyList.size();i++){
			if(!this.containsKey(primaryKeyList.get(i))){
				this.put(primaryKeyList.get(i),UUID.randomUUID().toString());
			}
		}
		sb.append("INSERT INTO ").append(this.tableName).append("(");
		Iterator<String> it=this.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			sb.append(key).append(",");
			this.valueList.add(this.get(key));
		}
		sb.deleteCharAt(sb.length()-1).append(")VALUES(");
		it=this.keySet().iterator();
		while(it.hasNext()){
			it.next();
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1).append(")");
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 更新记录
	 * @return
	 * @throws SQLException 
	 */
	public int update() throws SQLException{
		MyQueryRunner mqr = new MyQueryRunner();
		String sql=this.toUpdateSql();
		Object[] tem=this.toValueArray();
		logger.debug("SQL语句:"+sql);
		logger.debug("参数值:"+this.toJsonString());
		if(tem.length>0){
			return mqr.update(conn, sql, tem);
		}else{
			return mqr.update(conn, sql);
		}
	}
	
	/**
	 * 生成更新SQL
	 * @return String
	 */
	public String toUpdateSql(){
		valueList=new ArrayList<Object>();
		StringBuffer sb=new StringBuffer();
		sb.append("UPDATE ").append(this.tableName).append(" SET ");
		Map<String,Object> pkMap=new HashMap<String,Object>();
		for(int i=0;i<primaryKeyList.size();i++){
			pkMap.put(primaryKeyList.get(i),this.get(primaryKeyList.get(i)));
		}
		Iterator<String> it=this.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			if(pkMap.containsKey(key)){continue;}
			if(this.valueList.size()!=0){
				sb.append(" , ");
			}
			sb.append(key).append(" = ? ");
			this.valueList.add(this.get(key));
		}
		sb.append(" WHERE 1=1 ");
		it=pkMap.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			sb.append(" AND ").append(key).append(" = ? ");
			this.valueList.add(this.get(key));
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 删除记录
	 * @return
	 * @throws SQLException 
	 */
	public int delete() throws SQLException{
		MyQueryRunner mqr = new MyQueryRunner();
		String sql=this.toDeleteSql();
		Object[] tem=this.toValueArray();
		logger.debug("SQL语句:"+sql);
		logger.debug("参数值:"+this.toJsonString());
		if(tem.length>0){
			return mqr.update(conn, sql, tem);
		}else{
			return mqr.update(conn, sql);
		}
	}
	
	/**
	 * 生成删除SQL
	 * @return String
	 */
	public String toDeleteSql(){
		valueList=new ArrayList<Object>();
		StringBuffer sb=new StringBuffer();
		sb.append("DELETE FROM ").append(this.tableName).append(" WHERE 1=1 ");
		Iterator<String> it=this.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			sb.append(" AND ").append(key).append(" = ? ");
			this.valueList.add(this.get(key));
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 查找记录
	 * @return
	 * @throws SQLException 
	 */
	public Record select() throws SQLException{
		MyQueryRunner mqr = new MyQueryRunner();
		String sql=this.toSelectSql();
		Object[] tem=this.toValueArray();
		logger.debug("SQL语句:"+sql);
		logger.debug("参数值:"+this.toJsonString());
		if(tem.length>0){
			return mqr.queryRecord(conn, sql, tem);
		}else{
			return mqr.queryRecord(conn, sql);
		}
	}
	
	/**
	 * 生成查询SQL
	 * @return String
	 */
	public String toSelectSql(){
		valueList=new ArrayList<Object>();
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM ").append(this.tableName).append(" WHERE 1=1 ");
		Iterator<String> it=this.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			sb.append(" AND ").append(key).append(" = ? ");
			this.valueList.add(this.get(key));
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * Map中各value值转换为Object数组
	 * 在toXXSql()后调用，要不然你什么也得不到
	 * @return Object[]
	 */
	public Object[] toValueArray(){
		Object[] object=new Object[this.valueList.size()];
		for(int i=0;i<this.valueList.size();i++){
			object[i]=this.valueList.get(i);
		}
		return object;
	}
	
	/**
	 * 将Record转换为JSON格式字符串
	 * @return String
	 */
	public String toJsonString(){
		return JSONValue.toJSONString(this);
	}
	
	/**
	 * 获取键Key对应的值,返回Object
	 * @param key
	 * @return Object
	 */
	public Object getObject(String key){
		Object object=this.get(key.toUpperCase());
		return object;
	}
	
	/**
	 * 获取键Key对应的值,返回String
	 * @param key
	 * @return String
	 */
	public String getString(String key){
		return this.getString(key,"");
	}
	
	/**
	 * 获取键Key对应的值,返回String
	 * @param key
	 * @param defaultValue
	 * @return String
	 */
	public String getString(String key,String defaultValue){
		return this.getString(key, defaultValue, "GBK");
	}
	
	/**
	 * 获取键Key对应的值,返回String
	 * encoding只对oracle.sql.BLOB、byte[]转字符串时有效
	 * @param key
	 * @param defaultValue
	 * @param encoding
	 * @return
	 */
	public String getString(String key,String defaultValue,String encoding){
		Object object=this.get(key.toUpperCase());
		if(object==null){
			return defaultValue;
		}else{
			if(object instanceof java.lang.String){
				return object.toString();
			}else if(object instanceof java.lang.Integer){
				return object.toString();
			}else if(object instanceof oracle.sql.CLOB){
				return StringUtil.OracleClobToString(object);
			}else if(object instanceof oracle.sql.BLOB){
				return StringUtil.OracleBlobToString(object,encoding==null?"GBK":encoding);
			}else if(object instanceof byte[]){
				return StringUtil.byteArrayToString(object,encoding==null?"GBK":encoding);
			}else{
				return object.toString();
			}
		}
	}
	
	/**
	 * 获取键Key对应的值,返回int
	 * @param key
	 * @return int
	 */
	public int getInt(String key){
		Object object=this.get(key.toUpperCase());
		if(object==null){
			return 0;
		}else{
			return Integer.parseInt(object.toString());
		}
	}
	
	/**
	 * 获取键Key对应的值,返回float
	 * @param key
	 * @return float
	 */
	public float getFloat(String key){
		Object object=this.get(key.toUpperCase());
		if(object==null){
			return 0;
		}else{
			return Float.parseFloat(object.toString());
		}
	}
	
	/**
	 * 获取键Key对应的值,返回long
	 * @param key
	 * @return long
	 */
	public long getLong(String key){
		Object object=this.get(key.toUpperCase());
		if(object==null){
			return 0;
		}else{
			return Long.parseLong(object.toString());
		}
	}
	
	/**
	 * 获取键Key对应的值,返回double
	 * @param key
	 * @return double
	 */
	public double getDouble(String key){
		Object object=this.get(key.toUpperCase());
		if(object==null){
			return 0;
		}else{
			return Double.parseDouble(object.toString());
		}
	}
	
	/**
	 * 判断键是否存在或者不为空
	 * @param key
	 * @return
	 */
	public boolean isValidate(String key){
		if("".equals(this.getString(key, ""))){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 单个ResultSet转换为Record
	 * @param rs
	 * @throws SQLException
	 */
	public static Record generateFrom(ResultSet rs) throws SQLException{
		Record record=new Record();
		if(rs!=null){
			ResultSetMetaData rsmd = rs.getMetaData();
	        int cols = rsmd.getColumnCount();
	
	        for (int i = 1; i <= cols; i++) {
	            String columnName = rsmd.getColumnLabel(i);
	            if (null == columnName || 0 == columnName.length()) {
	              columnName = rsmd.getColumnName(i);
	            }
	            record.put(columnName.toUpperCase(), rs.getObject(i));
	        }
		}
        return record;
	}
	
}
