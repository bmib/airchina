/**  
 * @title SQLBuilder.java
 * @package com.mybase.db
 * @date 2013-3-25 下午07:35:28
 */ 
package com.mybase.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * SQL构建工具类
 * @author Mr.liuyong
 */
public class SQLBuilder{
	
	private static Logger logger = Logger.getLogger(SQLBuilder.class);
	
	private StringBuffer sb=new StringBuffer();
	private List<Object> valueList=new ArrayList<Object>();
	
	private SQLBuilder(){}
	
	/**
	 * 获取SQLBuilder对象
	 */
	public static SQLBuilder getInstance(){
		SQLBuilder sb=new SQLBuilder();
		return sb;
	}
	
	/**
	 * 追加字符串
	 * @param sql
	 */
	public void append(String sql){
		sb.append(sql);
	}
	
	/**
	 * 重新初始化并添加
	 * @param sql
	 */
	public void reAppend(String sql){
		sb=new StringBuffer(sql);
	}
	
	/**
	 * 增加参数值
	 * @param o
	 */
	public void appendValue(Object o){
		valueList.add(o);
	}
	
	/**
	 * 删除起始点字符
	 * @param start
	 * @param end
	 */
	public void delete(int start,int end){
		sb.delete(start, end);
	}
	
	/**
	 * 删除某个字符
	 * @param index
	 */
	public void deleteCharAt(int index){
		sb.deleteCharAt(index);
	}
	
	/**
	 * 获取自start之后的字符串
	 * @param start
	 * @return String
	 */
	public String substring(int start){
		return sb.substring(start);
	}
	
	/**
	 * 获取两个下标之间的字符串
	 * @param start
	 * @param end
	 * @return String
	 */
	public String substring(int start,int end){
		return sb.substring(start, end);
	}
	
	/**
	 * 将拼装的sql片段转化为完整的sql,与toString同功能
	 * @return String
	 */
	public String toSqlString (){
		logger.debug("SQL语句:"+sb.toString());
		return sb.toString();
	}
	
	/**
	 * 将拼装的sql片段转化为完整的sql
	 * @return String
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		logger.debug("SQL语句:"+sb.toString());
		return sb.toString();
	}
	
	/**
	 * 将sql对应的参数对象转化为对象数据
	 * @return Object[]
	 */
	public Object[] toValueArray(){
		Object[] object=new Object[this.valueList.size()];
		logger.debug("参数值开始");
		for(int i=0;i<this.valueList.size();i++){
			object[i]=this.valueList.get(i);
			logger.debug("Value:"+object[i]);
		}
		logger.debug("参数值结束");
		return object;
	}
	
	/**
	 * 获取拼装sql的长度
	 * @return int
	 */
	public int length(){
		return sb.length();
	}
}
