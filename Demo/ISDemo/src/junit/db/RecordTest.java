/**
 * @title RecordTest.java
 * @package junit.db
 * @date 2013-6-5 下午08:36:18
 */
package junit.db;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Test;

import com.mybase.db.DBToolkit;
import com.mybase.db.Record;
import com.mybase.util.DateTimeUtil;

/**
 * 描述一下吧
 * @author Mr.liuyong
 */
public class RecordTest {
	
    @Test public void create(){
    	Connection conn=DBToolkit.getConnection();
    	int num=1000;
    	try {
    		//新增
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", UUID.randomUUID().toString());
	    	record.put("NAME", "create测试");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	num=record.create();
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(1, num);
    }
    
    @Test public void update(){
    	Connection conn=DBToolkit.getConnection();
    	int num=1000;
    	try {
    		String pk=UUID.randomUUID().toString();
    		//新增
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "create测试");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	//修改
	    	Record record2 =new Record(conn,"EMPLOYEE");
	    	record2.addPrimaryKey("UUID");
	    	record2.put("UUID", pk);
	    	record2.put("NAME", "update测试");
	    	num=record2.update();
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(1, num);
    }
    
    @Test public void select(){
    	Connection conn=DBToolkit.getConnection();
    	String name=null;
    	try {
    		String pk=UUID.randomUUID().toString();
    		//新增
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "select测试");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	//查询
	    	Record record3 =new Record(conn,"EMPLOYEE");
	    	record3.addPrimaryKey("UUID");
	    	record3.put("UUID", pk);
	    	name=record3.select().getString("NAME");
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals("select测试", name);
    }
    
    @Test public void delete(){
    	Connection conn=DBToolkit.getConnection();
    	int num=1000;
    	try {
    		String pk=UUID.randomUUID().toString();
    		//新增
	    	Record record =new Record(conn,"EMPLOYEE");
	    	record.put("UUID", pk);
	    	record.put("NAME", "delete测试");
	    	record.put("AGE", "26");
	    	record.put("INTRODUCTION", "这是我的简介，clob测试");
	    	record.put("PHOTO", "这是我的照片，blob测试".getBytes());
	    	record.put("CREATETIME", DateTimeUtil.getCurTimestamp());
	    	record.create();
	    	//删除
	    	Record record2 =new Record(conn,"EMPLOYEE");
	    	record2.addPrimaryKey("UUID");
	    	record2.put("UUID", pk);
	    	num=record2.delete();
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBToolkit.close(conn);
		}
		assertEquals(1, num);
    }

}
