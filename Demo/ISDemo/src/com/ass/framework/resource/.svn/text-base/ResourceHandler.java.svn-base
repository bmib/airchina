/**
 * @title ResourceHandler.java
 * @package com.ass.framework.resource
 * @date 2013-7-3 下午03:17:15
 */
package com.ass.framework.resource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.ass.component.tree.TreeBuilder;
import com.ass.component.tree.TreeDefinition;
import com.ass.component.tree.TreeSql;
import com.ass.framework.common.entity.LoginUser;
import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Pagination;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.db.SQLBuilder;
import com.mybase.util.DateTimeUtil;

/**
 * 资源管理
 * @author Mr.liuyong
 */
public class ResourceHandler {
	
	private static Logger logger = Logger.getLogger(ResourceHandler.class);
	
	/**
	 * 获取资源树
	 * @return
	 */
	public String getResourceTree(){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ResourceSql.getResourceTree();
			RecordSet rs=mqr.queryRecordSet(conn,sb.toSqlString(),sb.toValueArray());
			TreeBuilder tb=new TreeBuilder(rs,"RESOURCEUUID","PID");
			return tb.toString();
		} catch (SQLException e) {
			logger.error("获取资源树",e);
			throw new RuntimeException("获取资源树",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 查询资源列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getResourceList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ResourceSql.getResourceList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("查询资源列表",e);
			throw new RuntimeException("查询资源列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据resourceUUID获取资源对象详情
	 * @param resourceUUID
	 * @return
	 */
	public Record getResource(String resourceUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ResourceSql.getResource(resourceUUID);
			return mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据resourceUUID获取资源对象详情",e);
			throw new RuntimeException("根据resourceUUID获取资源对象详情",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 创建资源
	 * @param storeRecord
	 * @return
	 */
	public boolean createResource(Record storeRecord){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			TreeDefinition tree=new TreeDefinition();
			tree.setTableName("ASS_RESOURCE");
			tree.setTableUUID("RESOURCEUUID");
			tree.setTablePID("PID");
			tree.setTableLevelcode("LEVELCODE");
			tree.setTableSortNum("SORTNUM");
			//获取新创建资源的LEVELCODE和SORTNUM
			SQLBuilder sb=TreeSql.getLevelAndSort(tree,storeRecord.getString("PID",""));
			Record record=mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
			//构建资源对象
			LoginUser loginUser=LoginUser.getLoginUser();
			String createUser=loginUser.getPersonUUID();
			Object nowTime=DateTimeUtil.getCurTimestamp();
			
			storeRecord.setConnection(conn);
			storeRecord.setTableName("ASS_RESOURCE");
			storeRecord.put("RESOURCEUUID",UUID.randomUUID().toString());
			storeRecord.put("LEVELCODE", record.getString("PLEVELCODE","")+record.getString("LEVELCODE","1000"));
			storeRecord.put("SORTNUM",record.getString("SORTNUM","0"));
			storeRecord.put("CREATEUSER",createUser);
			storeRecord.put("CREATETIME",nowTime);
			storeRecord.put("UPDATEUSER",createUser);
			storeRecord.put("UPDATETIME",nowTime);
			storeRecord.create();
			return true;
		} catch (SQLException e) {
			logger.error("创建资源",e);
			throw new RuntimeException("创建资源",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 修改资源
	 * @param storeRecord
	 * @return
	 */
	public boolean modifyResource(Record storeRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			storeRecord.setConnection(conn);
			storeRecord.addPrimaryKey("RESOURCEUUID");
			storeRecord.setTableName("ASS_RESOURCE");
			storeRecord.put("UPDATETIME", DateTimeUtil.getCurTimestamp());
			storeRecord.update();
			return true;
		} catch (SQLException e) {
			logger.error("修改资源",e);
			throw new RuntimeException("修改资源",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据resourceUUIDs删除资源
	 * @param resourceUUIDs
	 * @return
	 */
	public boolean deleteResource(String resourceUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			String[] resourceUUIDArray=resourceUUIDs.split("@");
			Object[][] paramObject=new Object[resourceUUIDArray.length][1];
			for(int i=0;i<resourceUUIDArray.length;i++){
				paramObject[i][0]=resourceUUIDArray[i];
			}
			SQLBuilder sb=ResourceSql.deleteResource();
			mqr.batch(conn, sb.toSqlString(), paramObject);
			return true;
		} catch (SQLException e) {
			logger.error("根据resourceUUIDs删除资源",e);
			throw new RuntimeException("根据resourceUUIDs删除资源",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断resourceCode的唯一性
	 * @param resourceCode
	 * @param resourceUUID
	 * @return
	 */
	public boolean verifyResourceCode(String resourceCode,String resourceUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=ResourceSql.verifyResourceCode(resourceCode,resourceUUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("RESOURCECODE",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断resourceCode的唯一性",e);
			throw new RuntimeException("判断resourceCode的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
}
