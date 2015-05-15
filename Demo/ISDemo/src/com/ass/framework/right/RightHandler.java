/**
 * @title RightHandler.java
 * @package com.ass.framework.right
 * @date 2013-7-3 下午03:19:52
 */
package com.ass.framework.right;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.ass.framework.common.entity.LoginUser;
import com.ass.framework.person.PersonSql;
import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Pagination;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.db.SQLBuilder;
import com.mybase.util.DateTimeUtil;

/**
 * 权限管理
 * @author Mr.liuyong
 */
public class RightHandler {
	
	private static Logger logger = Logger.getLogger(RightHandler.class);
	
	/**
	 * 查询角色列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getRoleList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getRoleList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("查询角色列表",e);
			throw new RuntimeException("查询角色列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 查询用户组列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getUsergroupList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getUsergroupList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("查询用户组列表",e);
			throw new RuntimeException("查询用户组列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据roleUUIDs获取多个角色对象
	 * @param roleUUIDs
	 * @return
	 */
	public RecordSet getRoleMore(String roleUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getRoleMore(roleUUIDs);
			return mqr.queryRecordSet(conn,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据roleUUIDs获取角色列表",e);
			throw new RuntimeException("根据roleUUIDs获取角色列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据usergroupUUIDs获取多个用户组对象
	 * @param usergroupUUIDs
	 * @return
	 */
	public RecordSet getUsergroupMore(String usergroupUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getUsergroupMore(usergroupUUIDs);
			return mqr.queryRecordSet(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据usergroupUUIDs获取用户组对象",e);
			throw new RuntimeException("根据usergroupUUIDs获取用户组对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据roleUUID获取角色对象
	 * @param roleUUID
	 * @return
	 */
	public Record getRole(String roleUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getRole(roleUUID);
			return mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据roleUUID获取角色对象",e);
			throw new RuntimeException("根据roleUUID获取角色对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据usergroupUUID获取用户组对象
	 * @param usergroupUUID
	 * @return
	 */
	public Record getUsergroup(String usergroupUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getUsergroup(usergroupUUID);
			return mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据用户组UUID获取用户组对象",e);
			throw new RuntimeException("根据用户组UUID获取用户组对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 新建角色
	 * @param roleRecord
	 * @return
	 */
	public boolean createRole(Record roleRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			//构建角色对象
			LoginUser loginUser=LoginUser.getLoginUser();
			String createUser=loginUser.getPersonUUID();
			Object nowTime=DateTimeUtil.getCurTimestamp();
			roleRecord.setConnection(conn);
			roleRecord.setTableName("ASS_ROLE");
			roleRecord.put("ROLEUUID",UUID.randomUUID().toString());
			roleRecord.put("CREATEUSER",createUser);
			roleRecord.put("CREATETIME",nowTime);
			roleRecord.put("UPDATEUSER",createUser);
			roleRecord.put("UPDATETIME",nowTime);
			roleRecord.create();
			return true;
		} catch (SQLException e) {
			logger.error("新建角色",e);
			throw new RuntimeException("新建角色",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 新建用户组
	 * @param usergroupRecord
	 * @return
	 */
	public boolean createUsergroup(Record usergroupRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			//构建用户组对象
			LoginUser loginUser=LoginUser.getLoginUser();
			String createUser=loginUser.getPersonUUID();
			Object nowTime=DateTimeUtil.getCurTimestamp();
			
			usergroupRecord.setConnection(conn);
			usergroupRecord.setTableName("ASS_USERGROUP");
			usergroupRecord.put("USERGROUPUUID",UUID.randomUUID().toString());
			usergroupRecord.put("CREATEUSER",createUser);
			usergroupRecord.put("CREATETIME",nowTime);
			usergroupRecord.put("UPDATEUSER",createUser);
			usergroupRecord.put("UPDATETIME",nowTime);
			usergroupRecord.create();
			return true;
		} catch (SQLException e) {
			logger.error("新建用户组",e);
			throw new RuntimeException("新建用户组",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 修改角色
	 * @param storeRecord
	 * @return
	 */
	public boolean modifyRole(Record roleRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			roleRecord.setConnection(conn);
			roleRecord.addPrimaryKey("ROLEUUID");
			roleRecord.setTableName("ASS_ROLE");
			roleRecord.put("UPDATETIME", DateTimeUtil.getCurTimestamp());
			roleRecord.update();
			return true;
		} catch (SQLException e) {
			logger.error("修改角色",e);
			throw new RuntimeException("修改角色",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 修改用户组
	 * @param usergroupRecord
	 * @return
	 */
	public boolean modifyUsergroup(Record usergroupRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			usergroupRecord.setConnection(conn);
			usergroupRecord.addPrimaryKey("USERGROUPUUID");
			usergroupRecord.setTableName("ASS_USERGROUP");
			usergroupRecord.put("UPDATETIME", DateTimeUtil.getCurTimestamp());
			usergroupRecord.update();
			return true;
		} catch (SQLException e) {
			logger.error("修改用户组",e);
			throw new RuntimeException("修改用户组",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据roleUUIDs删除角色
	 * @param roleUUIDs
	 * @return
	 */
	public boolean deleteRole(String roleUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.deleteRole(roleUUIDs);
			mqr.update(conn ,sb.toSqlString(),sb.toValueArray());
			return true;
		} catch (SQLException e) {
			logger.error("根据roleUUIDs删除角色",e);
			throw new RuntimeException("根据roleUUIDs删除角色",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据usergroupUUIDs删除用户组
	 * @param usergroupUUIDs
	 * @return
	 */
	public boolean deleteUsergroup(String usergroupUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.deleteUsergroup(usergroupUUIDs);
			mqr.update(conn ,sb.toSqlString(),sb.toValueArray());
			return true;
		} catch (SQLException e) {
			logger.error("根据usergroupUUIDs删除用户组",e);
			throw new RuntimeException("根据usergroupUUIDs删除用户组",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据type获取全部角色或全部用户组列表 type role角色|usergroup用户组
	 * @param type
	 * @return
	 */
	public RecordSet getAllRightList(String type){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getAllRightList(type);
			return mqr.queryRecordSet(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据type获取全部角色或全部用户组列表 type role角色|usergroup用户组",e);
			throw new RuntimeException("根据type获取全部角色或全部用户组列表 type role角色|usergroup用户组",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据usergroupUUID获取已分配的角色
	 * @param usergroupUUID
	 * @return
	 */
	public String getUsergroupRoleUUIDS(String usergroupUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getUsergroupRightList(usergroupUUID);
			RecordSet recordSet=mqr.queryRecordSet(conn ,sb.toSqlString(),sb.toValueArray());
			String rightUUID="";
			for(int i=0;i<recordSet.size();i++){
				Record record=recordSet.get(i);
				if(i==0){
					rightUUID=record.getString("RIGHTUUID","");
				}else{
					rightUUID=rightUUID+"@"+record.getString("RIGHTUUID","");
				}
			}
			return rightUUID;
		} catch (SQLException e) {
			logger.error("根据usergroupUUID获取对应的角色",e);
			throw new RuntimeException("根据usergroupUUID获取对应的角色",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 用户组包含角色变更
	 * @param usergroupUUID
	 * @param modifyType
	 * @param rightUUID
	 * @return
	 */
	public boolean modifyUsergroupRole(String usergroupUUID,String modifyType,String rightUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			conn.setAutoCommit(false);
			if("add".equals(modifyType)){
				//新增
				//1、先删除
				SQLBuilder sb=RightSql.deleteUsergroupRole(usergroupUUID,rightUUID);
				mqr.update(conn, sb.toSqlString(),sb.toValueArray());
				//2、再增加
				Record record=new Record(conn,"ASS_USERGROUPROLE");
				record.put("USERGROUPUUID", usergroupUUID);
				record.put("ROLEUUID", rightUUID);
				record.create();
			}else{
				//删除
				SQLBuilder sb=RightSql.deleteUsergroupRole(usergroupUUID,rightUUID);
				mqr.update(conn, sb.toSqlString(),sb.toValueArray());
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("用户组权限变更(角色)",e1);
			}
			logger.error("用户组权限变更(角色)",e);
			throw new RuntimeException("用户组权限变更(角色)",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据roleUUID获取已分配的资源
	 * @param roleUUID
	 * @return
	 */
	public String getRoleResourceUUIDS(String roleUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getRoleResourceList(roleUUID);
			RecordSet recordSet=mqr.queryRecordSet(conn ,sb.toSqlString(),sb.toValueArray());
			String rightUUID="";
			for(int i=0;i<recordSet.size();i++){
				Record record=recordSet.get(i);
				if(i==0){
					rightUUID=record.getString("RESOURCEUUID","");
				}else{
					rightUUID=rightUUID+"@"+record.getString("RESOURCEUUID","");
				}
			}
			return rightUUID;
		} catch (SQLException e) {
			logger.error("根据roleUUID获取对应的资源",e);
			throw new RuntimeException("根据roleUUID获取对应的资源",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 角色资源变更
	 * @param roleUUID
	 * @param resourceUUIDs
	 * @return
	 */
	public boolean modifyRoleResource(String roleUUID,String resourceUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			conn.setAutoCommit(false);
			//新增
			//1、先删除
			SQLBuilder sb=RightSql.deleteRoleResource(roleUUID);
			mqr.update(conn, sb.toSqlString(),sb.toValueArray());
			//2、再增加
			sb=RightSql.assignRoleResource(roleUUID,resourceUUIDs);
			mqr.update(conn, sb.toSqlString(),sb.toValueArray());
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("角色资源变更",e1);
			}
			logger.error("角色资源变更",e);
			throw new RuntimeException("角色资源变更",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 人员角色权限维护列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getRightPersonList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.getRightPersonList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("人员角色权限维护列表",e);
			throw new RuntimeException("人员角色权限维护列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据personUUIDs获取人员共有的已分配的角色或用户组
	 * @param personUUIDs
	 * @return
	 */
	public Map<String,Object> getPersonsCommonRightUUIDS(String personUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		Map<String,Object> commonRightMap=new HashMap<String,Object>();
		try {
			SQLBuilder sb=PersonSql.getPersonsCommonRightList(personUUIDs);
			RecordSet recordSet=mqr.queryRecordSet(conn ,sb.toSqlString(),sb.toValueArray());
			for(int i=0;i<recordSet.size();i++){
				Record record=recordSet.get(i);
				commonRightMap.put(record.getString("RIGHTUUID"), "");
			}
			return commonRightMap;
		} catch (SQLException e) {
			logger.error("根据personUUIDs获取人员共有的已分配的角色或用户组",e);
			throw new RuntimeException("根据personUUIDs获取人员共有的已分配的角色或用户组",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 人员权限变更(角色、用户组)type ROLE角色|USERGROUP用户组 modifyType ADD新增|DELETE删除
	 * @param type
	 * @param personUUIDs
	 * @param modifyType
	 * @param rightUUID
	 * @return
	 */
	public boolean modifyPersonRight(String type,String personUUIDs,String modifyType,String rightUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			conn.setAutoCommit(false);
			//删除权限
			SQLBuilder sb=PersonSql.deleteRight(type,personUUIDs,rightUUID);
			mqr.update(conn, sb.toSqlString(),sb.toValueArray());
			if("ADD".equals(modifyType)){
				RecordSet recordSet=new RecordSet(conn);
				String[] personUUIDsArray=personUUIDs.split("@");
				for(int i=0;i<personUUIDsArray.length;i++){
					Record record=new Record(conn,"ASS_PERSONRIGHT");
					record.put("PERSONUUID", personUUIDsArray[i]);
					if("ROLE".equals(type)){
						record.put("ROLEUUID", rightUUID);
					}else{
						record.put("USERGROUPUUID", rightUUID);
					}
					recordSet.add(record);
				}
				recordSet.create();
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("权限变更(角色、用户组)",e1);
			}
			logger.error("权限变更(角色、用户组)",e);
			throw new RuntimeException("权限变更(角色、用户组)",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断roleCode的唯一性
	 * @param roleCode
	 * @parem roleUUID
	 * @return
	 */
	public boolean verifyRoleCode(String roleCode,String roleUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.verifyRoleCode(roleCode,roleUUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("ROLECODE",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断roleCode的唯一性",e);
			throw new RuntimeException("判断roleCode的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断usergroupCode的唯一性
	 * @param usergroupCode
	 * @param usergroupUUID
	 * @return
	 */
	public boolean verifyUsergroupCode(String usergroupCode,String usergroupUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=RightSql.verifyUsergroupCode(usergroupCode,usergroupUUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("USERGROUPCODE",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断usergroupCode的唯一性",e);
			throw new RuntimeException("判断usergroupCode的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
}
