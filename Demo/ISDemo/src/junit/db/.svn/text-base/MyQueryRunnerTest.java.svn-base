/**
 * @title MyQueryRunnerTest.java
 * @package junit.db
 * @date 2013-6-5 下午08:37:42
 */
package junit.db;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;

import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Pagination;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.util.DateTimeUtil;

/**
 * 描述一下吧
 * @author liuyong
 */
public class MyQueryRunnerTest {
	
	@Test public void queryRecord(){
    	Connection conn=DBToolkit.getConnection();
    	String name=null;
    	try {
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", "queryRecord");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	MyQueryRunner mqr= new MyQueryRunner();
	    	record=mqr.queryRecord(conn, "SELECT * FROM EMPLOYEE WHERE NAME='queryRecord'");
	    	if(record!=null){
	    		name=record.getString("NAME");
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals("queryRecord", name);
    }
	
	@Test public void queryRecordWithParam(){
    	Connection conn=DBToolkit.getConnection();
    	String name=null;
    	try {
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", "queryRecordWithParam");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	MyQueryRunner mqr= new MyQueryRunner();
	    	record=mqr.queryRecord(conn, "SELECT * FROM EMPLOYEE WHERE NAME=?",new Object[]{"queryRecordWithParam"});
	    	if(record!=null){
	    		name=record.getString("NAME");
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals("queryRecordWithParam", name);
    }
	
	@Test public void queryRecordSet(){
    	Connection conn=DBToolkit.getConnection();
    	int num=1000;
    	try {
    		String pk=UUID.randomUUID().toString();
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "queryRecordSet");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	MyQueryRunner mqr= new MyQueryRunner();
	    	RecordSet rs=mqr.queryRecordSet(conn, "SELECT * FROM EMPLOYEE WHERE UUID='"+pk+"'");
	    	num=rs.size();
	    	
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(1, num);
    }
	
	@Test public void queryRecordSetWithParam(){
    	Connection conn=DBToolkit.getConnection();
    	int num=1000;
    	try {
    		String pk=UUID.randomUUID().toString();
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "queryRecordSetWithParam");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	MyQueryRunner mqr= new MyQueryRunner();
	    	RecordSet rs=mqr.queryRecordSet(conn, "SELECT * FROM EMPLOYEE WHERE UUID=?",new Object[]{pk});
	    	num=rs.size();
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(1, num);
    }
	
	@Test public void queryRecordSetWithPagination(){
    	Connection conn=DBToolkit.getConnection();
    	int age=1000;
    	try {
    		String name=UUID.randomUUID().toString();
	    	
    		Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", name);
	    	record.put("AGE", "25");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", name);
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	MyQueryRunner mqr= new MyQueryRunner();
	    	Pagination pagination=new Pagination();
	    	pagination.setCurrentPageNo(2);
	    	pagination.setPageSize(1);
	    	
	    	RecordSet rs=mqr.queryRecordSet(conn, pagination,"SELECT * FROM EMPLOYEE WHERE name='"+name+"' ORDER BY AGE DESC");
	    	System.out.println("name:"+name);
	    	if(rs.size()>0){
	    		Record temRecord=rs.get(0);
	    		age=temRecord.getInt("AGE");
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(25, age);
    }
	
	@Test public void queryRecordSetWithParamAndPagination(){
    	Connection conn=DBToolkit.getConnection();
    	int age=1000;
    	try {
    		String name=UUID.randomUUID().toString();
	    	
    		Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", name);
	    	record.put("AGE", "25");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", name);
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	
	    	MyQueryRunner mqr= new MyQueryRunner();
	    	Pagination pagination=new Pagination();
	    	pagination.setCurrentPageNo(2);
	    	pagination.setPageSize(1);
	    	
	    	RecordSet rs=mqr.queryRecordSet(conn, pagination,"SELECT * FROM EMPLOYEE WHERE name=? ORDER BY AGE DESC",new Object[]{name});
	    	if(rs.size()>0){
	    		Record temRecord=rs.get(0);
	    		age=temRecord.getInt("AGE");
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(25, age);
    }
	
	
}
