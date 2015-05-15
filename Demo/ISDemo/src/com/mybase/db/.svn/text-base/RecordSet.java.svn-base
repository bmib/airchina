/**  
 * @title RecordSet.java
 * @package com.mybase.db
 * @date 2013-3-18 下午09:25:58
 */ 
package com.mybase.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

/**
 * 多实体，一般对应数据库中多条记录，继承自ArrayList
 * @author Mr.liuyong
 */
public class RecordSet extends ArrayList<Record>{

	//数据库链接
	Connection conn=null;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 构造函数 RecordSet.
	 */
	public RecordSet(){}
	
	/**
	 * 构造函数 RecordSet.
	 * @param conn
	 */
	public RecordSet(Connection conn){
		this.conn=conn;
	}
	
	/**
	 * 设置数据库连接
	 * @param conn
	 */
	public void setConnection(Connection conn){
		this.conn=conn;
	}
	
	/**
	 * 保存多条记录
	 * 别忘了设置数据库连接
	 * PrepareStatement 的 executeBatch方法成功执行后，如果统计不出条数，会返回SUCCESS_NO_INFO(-2),但实际执行成功了。
	 * @return
	 * @throws SQLException 
	 */
	public int[] create() throws SQLException{
		int rsSize=this.size();
		if(rsSize>0){
			String sql=null;
			Object[] param=null;
			Object[][] paramObject=null;
			for(int i=0;i<rsSize;i++){
				Record record=this.get(i);
				sql=record.toCreateSql();
				param=record.toValueArray();
				if(i==0){
					paramObject=new Object[rsSize][param.length];
				}
				paramObject[i]=param;
			}
			MyQueryRunner mqr = new MyQueryRunner();
			return mqr.batch(conn, sql, paramObject);
		}else{
			return new int[0];
		}
	}
	
	/**
	 * 更新多条记录
	 * 别忘了设置数据库连接
	 * PrepareStatement 的 executeBatch方法成功执行后，如果统计不出条数，会返回SUCCESS_NO_INFO(-2),但实际执行成功了。
	 * @return
	 * @throws SQLException 
	 */
	public int[] update() throws SQLException{
		int rsSize=this.size();
		if(rsSize>0){
			String sql=null;
			Object[] param=null;
			Object[][] paramObject=null;
			for(int i=0;i<rsSize;i++){
				Record record=this.get(i);
				sql=record.toUpdateSql();
				param=record.toValueArray();
				if(i==0){
					paramObject=new Object[rsSize][param.length];
				}
				paramObject[i]=param;
			}
			MyQueryRunner mqr = new MyQueryRunner();
			return mqr.batch(conn, sql, paramObject);
		}else{
			return new int[0];
		}
	}
	
	/**
	 * 删除多条记录
	 * 别忘了设置数据库连接
	 * PrepareStatement 的 executeBatch方法成功执行后，如果统计不出条数，会返回SUCCESS_NO_INFO(-2),但实际执行成功了。
	 * @return
	 * @throws SQLException 
	 */
	public int[] delete() throws SQLException{
		int rsSize=this.size();
		if(rsSize>0){
			String sql=null;
			Object[] param=null;
			Object[][] paramObject=null;
			for(int i=0;i<rsSize;i++){
				Record record=this.get(i);
				sql=record.toDeleteSql();
				param=record.toValueArray();
				if(i==0){
					paramObject=new Object[rsSize][param.length];
				}
				paramObject[i]=param;
			}
			MyQueryRunner mqr = new MyQueryRunner();
			return mqr.batch(conn, sql, paramObject);
		}else{
			return new int[0];
		}
	}
	
	/**
	 * 将RecordSet转换为JSON格式字符串
	 * @return
	 */
	public String toJsonString(){
		return JSONArray.toJSONString(this);
	}
	
	/**
	 * ResultSet转换为RecordSet
	 * @param rs
	 * @throws SQLException
	 */
	public static RecordSet generateFrom(ResultSet rs) throws SQLException{
		RecordSet recordSet=new RecordSet();
		while (rs!=null && rs.next()) {
			recordSet.add(Record.generateFrom(rs));
        }
		return recordSet;
	}
}
