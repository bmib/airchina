/**
 * @title PersonHandler.java
 * @package com.ass.framework.person
 * @date 2013-7-3 下午03:13:25
 */
package com.ass.framework.person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.ass.framework.common.entity.LoginUser;
import com.mybase.config.DBConfig;
import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Pagination;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.db.SQLBuilder;
import com.mybase.util.DateTimeUtil;

/**
 * 人员管理
 * @author Mr.liuyong
 */
public class PersonHandler {
	
	private static Logger logger = Logger.getLogger(PersonHandler.class);
	
	/**
	 * 查询人员列表
	 * @param paramRecord
	 * @param pagination
	 * @return
	 */
	public RecordSet getPersonList(Record paramRecord,Pagination pagination){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=PersonSql.getPersonList(paramRecord);
			return mqr.queryRecordSet(conn, pagination , sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("查询人员列表",e);
			throw new RuntimeException("查询人员列表",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据personUUID获取单个人员对象详情
	 * @param personUUID
	 * @return
	 */
	public Record getPerson(String personUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=PersonSql.getPerson(personUUID);
			return mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据personUUID获取人员对象",e);
			throw new RuntimeException("根据personUUID获取人员对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据personUUIDs获取多个人员对象
	 * @param personUUIDs
	 * @return
	 */
	public RecordSet getPersonMore(String personUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=PersonSql.getPersonMore(personUUIDs);
			return mqr.queryRecordSet(conn ,sb.toSqlString(),sb.toValueArray());
		} catch (SQLException e) {
			logger.error("根据人员personUUIDs获取人员对象",e);
			throw new RuntimeException("根据人员personUUIDs获取人员对象",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 新增人员
	 * @param personRecord
	 * @param orgRecord
	 * @param accountRecord
	 * @return
	 */
	public boolean createPerson(Record personRecord,Record orgRecord,Record accountRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			String personUUID=UUID.randomUUID().toString();
			LoginUser loginUser=LoginUser.getLoginUser();
			String createUser=loginUser.getPersonUUID();
			Object nowTime=DateTimeUtil.getCurTimestamp();
			//存储人员信息
			conn.setAutoCommit(false);
			personRecord.setConnection(conn);
			personRecord.setTableName("ASS_PERSON");
			personRecord.put("PERSONUUID",personUUID);
			personRecord.put("CREATEUSER",createUser);
			personRecord.put("CREATETIME",nowTime);
			personRecord.put("UPDATEUSER",createUser);
			personRecord.put("UPDATETIME",nowTime);
			personRecord.create();
			//存储人员和组织的关系
			orgRecord.setConnection(conn);
			orgRecord.setTableName("ASS_ORGPERSON");
			orgRecord.put("PERSONUUID", personUUID);
			orgRecord.create();
			//存储账号信息
			accountRecord.setConnection(conn);
			accountRecord.setTableName("ASS_PERSONACCOUNT");
			accountRecord.put("PERSONUUID", personUUID);
			accountRecord.put("PASSWORD", DigestUtils.md5Hex("ISF"+DBConfig.getProperty("SYSTEM_PASSWORD","1234%ffff")));
			accountRecord.put("CREATEUSER",createUser);
			accountRecord.put("CREATETIME",nowTime);
			accountRecord.put("UPDATEUSER",createUser);
			accountRecord.put("UPDATETIME",nowTime);
			accountRecord.create();
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("新增人员回滚",e1);
			}
			logger.error("新增人员",e);
			throw new RuntimeException("新增人员",e);
		}finally{
			DBToolkit.close(conn);
		}
	}

	
	/**
	 * 修改人员
	 * @param personRecord
	 * @param orgRecord
	 * @param accountRecord
	 * @return
	 */
	public boolean modifyPerson(Record personRecord,Record orgRecord,Record accountRecord){
		Connection conn=DBToolkit.getConnection();
		try {
			String updateUser="";
			Object nowTime=DateTimeUtil.getCurTimestamp();
			//修改人员信息
			conn.setAutoCommit(false);
			personRecord.setConnection(conn);
			personRecord.setTableName("ASS_PERSON");
			personRecord.addPrimaryKey("PERSONUUID");
			personRecord.put("UPDATEUSER",updateUser);
			personRecord.put("UPDATETIME",nowTime);
			personRecord.update();
			//修改人员和组织的关系
			if(orgRecord!=null){
				orgRecord.setConnection(conn);
				orgRecord.setTableName("ASS_ORGPERSON");
				orgRecord.addPrimaryKey("PERSONUUID");
				orgRecord.put("PERSONUUID", personRecord.getString("PERSONUUID"));
				orgRecord.update();
			}
			//修改账号信息
			if(accountRecord!=null){
				accountRecord.setConnection(conn);
				accountRecord.setTableName("ASS_PERSONACCOUNT");
				accountRecord.addPrimaryKey("PERSONUUID");
				accountRecord.put("PERSONUUID", personRecord.getString("PERSONUUID"));
				accountRecord.put("UPDATEUSER",updateUser);
				accountRecord.put("UPDATETIME",nowTime);
				accountRecord.update();
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("修改人员回滚",e1);
			}
			logger.error("修改人员",e);
			throw new RuntimeException("修改人员",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 修改人员-密码
	 * @param accountRecord
	 * @return
	 */
	public boolean modifyAccount(Record accountRecord){
		if("".equals(accountRecord.getString("PASSWORD",""))){
			return true;
		}
		Connection conn=DBToolkit.getConnection();
		try {
			LoginUser loginUser=LoginUser.getLoginUser();
			String updateUser=loginUser.getPersonUUID();
			Object nowTime=DateTimeUtil.getCurTimestamp();
			//修改账号信息
			accountRecord.setConnection(conn);
			accountRecord.setTableName("ASS_PERSONACCOUNT");
			accountRecord.addPrimaryKey("PERSONUUID");
			if(!"".equals(accountRecord.getString("PASSWORD",""))){
				accountRecord.put("PASSWORD", DigestUtils.md5Hex("ISF"+accountRecord.getString("PASSWORD")));
			}
			accountRecord.put("UPDATEUSER",updateUser);
			accountRecord.put("UPDATETIME",nowTime);
			accountRecord.update();
			return true;
		} catch (SQLException e) {
			logger.error("修改人员-密码",e);
			throw new RuntimeException("修改人员-密码",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 根据personUUIDs删除人员
	 * @param personUUIDs
	 * @return
	 */
	public boolean deletePerson(String personUUIDs){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=PersonSql.deletePerson(personUUIDs);
			mqr.update(conn ,sb.toSqlString(),sb.toValueArray());
			return true;
		} catch (SQLException e) {
			logger.error("根据personUUIDs删除人员",e);
			throw new RuntimeException("根据personUUIDs删除人员",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断personCode的唯一性
	 * @param personCode
	 * @param personUUID
	 * @return
	 */
	public boolean verifyPersonCode(String personCode,String personUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=PersonSql.verifyPersonCode(personCode,personUUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("PERSONCODE",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断personCode的唯一性",e);
			throw new RuntimeException("判断personCode的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	/**
	 * 判断account的唯一性
	 * @param account
	 * @param personUUID
	 * @return
	 */
	public boolean verifyAccount(String account,String personUUID){
		Connection conn=DBToolkit.getConnection();
		MyQueryRunner mqr= new MyQueryRunner();
		try {
			SQLBuilder sb=PersonSql.verifyAccount(account,personUUID);
			Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
			return "".equals(record.getString("ACCOUNT",""))?true:false;
		} catch (SQLException e) {
			logger.error("判断account的唯一性",e);
			throw new RuntimeException("判断account的唯一性",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
	
}
