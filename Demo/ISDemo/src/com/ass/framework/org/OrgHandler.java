/**
 * @title OrgHandler.java
 * @package com.ass.framework.org
 * @date 2013-7-3 下午03:04:24
 */
package com.ass.framework.org;

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
 * 组织管理
 * @author Mr.liuyong
 */
public class OrgHandler {
	
	private static Logger logger = Logger.getLogger(OrgHandler.class);
	
	/**
	 * 获取组织树
	 * @return
	 */
	public String getOrgTree(){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=OrgSql.getOrgTree();
			RecordSet rs=mqr.queryRecordSet(conn,sb.toSqlString(),sb.toValueArray());
			TreeBuilder tb=new TreeBuilder(rs,"ORGUUID","PID");
			return tb.toString();
		} catch (SQLException e) {
			logger.error("获取组织树",e);
			throw new RuntimeException("获取组织树",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 查询组织列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getOrgList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=OrgSql.getOrgList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("查询组织列表",e);
			throw new RuntimeException("查询组织列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据orgUUID获取组织对象
	 * @param orgUUID
	 * @return
	 */
	public Record getOrg(String orgUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=OrgSql.getOrg(orgUUID);
			return mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据orgUUID获取组织对象",e);
			throw new RuntimeException("根据orgUUID获取组织对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 新增组织
	 * @param storeRecord
	 * @return
	 */
	public boolean createOrg(Record storeRecord){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			TreeDefinition tree=new TreeDefinition();
			tree.setTableName("ASS_ORG");
			tree.setTableUUID("ORGUUID");
			tree.setTablePID("PID");
			tree.setTableLevelcode("LEVELCODE");
			tree.setTableSortNum("SORTNUM");
			//获取新创建组织的LEVELCODE和SORTNUM
			SQLBuilder sb=TreeSql.getLevelAndSort(tree,storeRecord.getString("PID",""));
			Record record=mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
			//构建组织对象
			LoginUser loginUser=LoginUser.getLoginUser();
			String createUser=loginUser.getPersonUUID();
			Object nowTime=DateTimeUtil.getCurTimestamp();
			
			storeRecord.setConnection(conn);
			storeRecord.setTableName("ASS_ORG");
			storeRecord.put("ORGUUID",UUID.randomUUID().toString());
			storeRecord.put("LEVELCODE", record.getString("PLEVELCODE","")+record.getString("LEVELCODE","1000"));
			storeRecord.put("SORTNUM",record.getString("SORTNUM","0"));
			storeRecord.put("DELTAG",0);
			storeRecord.put("CREATEUSER",createUser);
			storeRecord.put("CREATETIME",nowTime);
			storeRecord.put("UPDATEUSER",createUser);
			storeRecord.put("UPDATETIME",nowTime);
			storeRecord.create();
			return true;
		} catch (SQLException e) {
			logger.error("新增组织",e);
			throw new RuntimeException("新增组织",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 修改组织
	 * @param storeRecord
	 * @return
	 */
	public boolean modifyOrg(Record storeRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			storeRecord.setConnection(conn);
			storeRecord.addPrimaryKey("ORGUUID");
			storeRecord.setTableName("ASS_ORG");
			storeRecord.put("UPDATETIME", DateTimeUtil.getCurTimestamp());
			storeRecord.update();
			return true;
		} catch (SQLException e) {
			logger.error("修改组织",e);
			throw new RuntimeException("修改组织",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据orgUUIDs删除组织,假删
	 * @param orgUUIDs
	 * @return
	 */
	public boolean deleteOrg(String orgUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			String[] orgUUIDArray=orgUUIDs.split("@");
			Object[][] paramObject=new Object[orgUUIDArray.length][1];
			for(int i=0;i<orgUUIDArray.length;i++){
				paramObject[i][0]=orgUUIDArray[i];
			}
			SQLBuilder sb=OrgSql.deleteOrg();
			mqr.batch(conn, sb.toSqlString(), paramObject);
			return true;
		} catch (SQLException e) {
			logger.error("根据orgUUIDs删除组织,假删",e);
			throw new RuntimeException("根据orgUUIDs删除组织",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断orgCode的唯一性
	 * @param orgCode
	 * @param orgUUID
	 * @return
	 */
	public boolean verifyOrgCode(String orgCode,String orgUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=OrgSql.verifyOrgCode(orgCode,orgUUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("ORGCODE",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断orgCode的唯一性",e);
			throw new RuntimeException("判断orgCode的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
}
