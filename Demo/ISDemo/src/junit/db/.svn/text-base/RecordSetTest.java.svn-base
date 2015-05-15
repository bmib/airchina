/**
 * @title RecordSetTest.java
 * @package junit.db
 * @date 2013-6-5 下午08:36:37
 */
package junit.db;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;

import com.mybase.db.DBToolkit;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.util.DateTimeUtil;

/**
 * 描述一下吧
 * @author liuyong
 */
public class RecordSetTest {
	
	@Test public void create(){
    	Connection conn=DBToolkit.getConnection();
    	int count=0;
    	try {
    		//第一个record
	    	Record record =new Record("EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", "batch create测试1");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	//第二个record
	    	Record record2 =new Record("EMPLOYEE");
	    	record2.put("UUID", UUID.randomUUID().toString());
	    	record2.put("NAME", "batch create测试2");
	    	record2.put("AGE", "26");
	    	record2.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record2.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record2.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	//第三个record
	    	Record record3 =new Record("EMPLOYEE");
	    	record3.put("UUID", UUID.randomUUID().toString());
	    	record3.put("NAME", "batch create测试3");
	    	record3.put("AGE", "26");
	    	record3.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record3.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record3.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	
	    	RecordSet rs=new RecordSet();
	    	rs.setConnection(conn);
	    	rs.add(record);
	    	rs.add(record2);
	    	rs.add(record3);
	    	int[] num=rs.create();
	    	if(num!=null){
	    		for(int i=0;i<num.length;i++){
	    			count=count+num[i];
	    		}
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(3, count==-6?3:count);
    }
	
	@Test public void update(){
    	Connection conn=DBToolkit.getConnection();
    	int count=0;
    	try {
    		String pk=UUID.randomUUID().toString();
    		String pk2=UUID.randomUUID().toString();
    		String pk3=UUID.randomUUID().toString();
    		
    		//第一个record
	    	Record record =new Record("EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "batch create测试1");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	//第二个record
	    	Record record2 =new Record("EMPLOYEE");
	    	record2.put("UUID", pk2);
	    	record2.put("NAME", "batch create测试2");
	    	record2.put("AGE", "26");
	    	record2.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record2.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record2.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	//第三个record
	    	Record record3 =new Record("EMPLOYEE");
	    	record3.put("UUID", pk3);
	    	record3.put("NAME", "batch create测试3");
	    	record3.put("AGE", "26");
	    	record3.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record3.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record3.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	
	    	RecordSet rs=new RecordSet();
	    	rs.setConnection(conn);
	    	rs.add(record);
	    	rs.add(record2);
	    	rs.add(record3);
	    	rs.create();
	    	
	    	Record recordUpdate =new Record("EMPLOYEE");
	    	recordUpdate.addPrimaryKey("UUID");
	    	recordUpdate.put("UUID", pk);
	    	recordUpdate.put("NAME", "batch update测试1");
	    	
	    	Record record2Update =new Record("EMPLOYEE");
	    	record2Update.addPrimaryKey("UUID");
	    	record2Update.put("UUID", pk2);
	    	record2Update.put("NAME", "batch update测试2");
	    	
	    	Record record3Update =new Record("EMPLOYEE");
	    	record3Update.addPrimaryKey("UUID");
	    	record3Update.put("UUID", pk3);
	    	record3Update.put("NAME", "batch update测试3");
	    	
	    	RecordSet rsUpdate=new RecordSet();
	    	rsUpdate.setConnection(conn);
	    	rsUpdate.add(recordUpdate);
	    	rsUpdate.add(record2Update);
	    	rsUpdate.add(record3Update);
	    	
	    	int[] num=rsUpdate.update();
	    	
	    	if(num!=null){
	    		for(int i=0;i<num.length;i++){
	    			count=count+num[i];
	    		}
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(3, count==-6?3:count);
    }
	
	@Test public void delete(){
    	Connection conn=DBToolkit.getConnection();
    	int count=0;
    	try {
    		String pk=UUID.randomUUID().toString();
    		String pk2=UUID.randomUUID().toString();
    		String pk3=UUID.randomUUID().toString();
    		
    		//第一个record
	    	Record record =new Record("EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "batch 删除测试1");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	//第二个record
	    	Record record2 =new Record("EMPLOYEE");
	    	record2.put("UUID", pk2);
	    	record2.put("NAME", "batch 删除测试2");
	    	record2.put("AGE", "26");
	    	record2.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record2.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record2.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	//第三个record
	    	Record record3 =new Record("EMPLOYEE");
	    	record3.put("UUID", pk3);
	    	record3.put("NAME", "batch 删除测试3");
	    	record3.put("AGE", "26");
	    	record3.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record3.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record3.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	
	    	RecordSet rs=new RecordSet();
	    	rs.setConnection(conn);
	    	rs.add(record);
	    	rs.add(record2);
	    	rs.add(record3);
	    	rs.create();
	    	
	    	Record recordUpdate =new Record("EMPLOYEE");
	    	recordUpdate.addPrimaryKey("UUID");
	    	recordUpdate.put("UUID", pk);
	    	
	    	Record record2Update =new Record("EMPLOYEE");
	    	record2Update.addPrimaryKey("UUID");
	    	record2Update.put("UUID", pk2);
	    	
	    	Record record3Update =new Record("EMPLOYEE");
	    	record3Update.addPrimaryKey("UUID");
	    	record3Update.put("UUID", pk3);
	    	
	    	RecordSet rsUpdate=new RecordSet();
	    	rsUpdate.setConnection(conn);
	    	rsUpdate.add(recordUpdate);
	    	rsUpdate.add(record2Update);
	    	rsUpdate.add(record3Update);
	    	
	    	int[] num=rsUpdate.delete();
	    	
	    	if(num!=null){
	    		for(int i=0;i<num.length;i++){
	    			count=count+num[i];
	    		}
	    	}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(3, count==-6?3:count);
    }
}
