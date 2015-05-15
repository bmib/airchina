/**
 * @title TreeHandler.java
 * @package com.isf.component.tree
 * @date 2013-8-12 下午07:40:59
 */
package com.ass.component.tree;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mybase.db.DBToolkit;
import com.mybase.db.MyQueryRunner;
import com.mybase.db.Record;
import com.mybase.db.RecordSet;
import com.mybase.db.SQLBuilder;

/**
 * 树形结构移动树节点操作
 * @author Mr.liuyong
 */
public class TreeHandler{
	
	private static Logger logger = Logger.getLogger(TreeHandler.class);
	
	/**
	 * 树节点移动
	 * @param tree 树形结构表的相关信息
	 * @param moveType 移动类型，前移/后移/移入 
	 * @param sameParentFlag 是否同级移动
	 * @param sourceObjUUID 源节点UUID
	 * @param targetObjUUID 目标节点UUID
	 * @return
	 */
	public boolean moveTreeNode(TreeDefinition tree,String moveType,String sameParentFlag,String sourceObjUUID,String targetObjUUID){
		Connection conn=null;
		try{	
			conn=DBToolkit.getConnection();
			conn.setAutoCommit(false);
			if("true".equals(sameParentFlag)){
				//源、目标节点为同级节点
				if("inner".equals(moveType)){
					//同级移入[更新父节点，LEVELCODE，排序号]
					String parentUUID=targetObjUUID;
					this.updateNodeLevelcode(conn,tree,sourceObjUUID, parentUUID);
					this.updateNodeParent(conn,tree,sourceObjUUID,parentUUID);
					this.updateNodeSort(conn,tree,moveType,sourceObjUUID,targetObjUUID, parentUUID);
				}else{
					//同级、移前及移后[更新排序]
					String parentUUID=this.getParentNode(conn,tree,targetObjUUID);
					this.updateNodeSort(conn,tree,moveType,sourceObjUUID, targetObjUUID,parentUUID);
				}
			}else{
				if("inner".equals(moveType)){
					//不同级移入[更新父节点，LEVELCODE，排序号]
					String parentUUID=targetObjUUID;
					this.updateNodeLevelcode(conn,tree,sourceObjUUID, parentUUID);
					this.updateNodeParent(conn,tree,sourceObjUUID,parentUUID);
					this.updateNodeSort(conn,tree,moveType,sourceObjUUID,targetObjUUID, parentUUID);
					
				}else if("prev".equals(moveType)){
					//不同级前移[更新父节点，LEVELCODE，排序号]
					String parentUUID=this.getParentNode(conn,tree,targetObjUUID);
					this.updateNodeLevelcode(conn,tree,sourceObjUUID, parentUUID);
					this.updateNodeParent(conn,tree,sourceObjUUID,parentUUID);
					this.updateNodeSort(conn,tree,moveType,sourceObjUUID,targetObjUUID, parentUUID);
				}else{
					//不同级后移[更新父节点，LEVELCODE，排序号]
					String parentUUID=this.getParentNode(conn,tree,targetObjUUID);
					this.updateNodeLevelcode(conn,tree,sourceObjUUID, parentUUID);
					this.updateNodeParent(conn,tree,sourceObjUUID,parentUUID);
					this.updateNodeSort(conn,tree,moveType,sourceObjUUID,targetObjUUID, parentUUID);
				}
			}
			conn.commit();
			return true;
		}catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("树移动,回滚",e1);
			}
			logger.error("树移动",e);
			throw new RuntimeException("树移动",e);
		}finally{
			DBToolkit.close(conn);
		}
		
	}
	
	/**
	 * 树移动-获取目标节点的父节点UUID
	 * @param conn
	 * @param targetObjUUID
	 * @return
	 * @throws SQLException 
	 */
	private String getParentNode(Connection conn,TreeDefinition tree,String targetObjUUID) throws SQLException{
		MyQueryRunner mqr= new MyQueryRunner();
		SQLBuilder sb=TreeSql.getParentNode(tree,targetObjUUID);
		Record record=mqr.queryRecord(conn, sb.toSqlString(),sb.toValueArray());
		return record.getString("PID","");
	}
	
	/**
	 * 树移动-更新源节点的父级节点属性
	 * @param conn
	 * @param sourceObjUUID
	 * @param parentUUID
	 * @return
	 * @throws SQLException 
	 */
	private void updateNodeParent(Connection conn,TreeDefinition tree,String sourceObjUUID,String parentUUID) throws SQLException{
		Record record=new Record(conn,tree.getTableName());
		record.addPrimaryKey(tree.getTableUUID());
		record.put(tree.getTableUUID(), sourceObjUUID);
		record.put(tree.getTablePID(), parentUUID);
		record.update();
	}
	
	/**
	 * 树移动-更新源节点及子孙节点层级
	 * @param conn
	 * @param sourceObjUUID
	 * @param parentUUID
	 * @return
	 * @throws SQLException 
	 */
	private void updateNodeLevelcode(Connection conn,TreeDefinition tree,String sourceObjUUID,String parentUUID) throws SQLException{
		MyQueryRunner mqr= new MyQueryRunner();
		//获取源新的LEVELCODE
		SQLBuilder sb=TreeSql.getLevelAndSort(tree,parentUUID);
		Record record=mqr.queryRecord(conn ,sb.toSqlString(),sb.toValueArray());
		String newLevelcode=record.getString("PLEVELCODE","")+record.getString("LEVELCODE","1000");
		//更新单位层级
		sb=TreeSql.updateLevelcode(tree,sourceObjUUID,newLevelcode);
		mqr.update(conn, sb.toSqlString(),sb.toValueArray());
	}
	
	/**
	 * 树移动-更新排序号
	 * @param conn
	 * @param moveType
	 * @param sourceObjUUID
	 * @param parentUUID
	 * @return
	 * @throws SQLException 
	 */
	private void updateNodeSort(Connection conn,TreeDefinition tree,String moveType,String sourceObjUUID,String targetObjUUID,String parentUUID) throws SQLException{
		MyQueryRunner mqr= new MyQueryRunner();
		SQLBuilder sb=TreeSql.getChildNode(tree,parentUUID);
		//现有数据库中的顺序
		RecordSet recordSet=mqr.queryRecordSet(conn, sb.toSqlString(),sb.toValueArray());
		//调整后的新的顺序
		RecordSet newRecordSet=new RecordSet();
		//新加入的节点
		Record sourceRecord=new Record();
		sourceRecord.put("UUID", sourceObjUUID);
		
		if(recordSet.size()<=0){return;}
		
		for(int i=0;i<recordSet.size();i++){
			Record record=recordSet.get(i);
			if(record.getString("UUID","").equals(targetObjUUID)){
				//前移或者后移
				if("prev".equals(moveType)){
					newRecordSet.add(sourceRecord);
					newRecordSet.add(record);
				}else if("next".equals(moveType)){
					newRecordSet.add(record);
					newRecordSet.add(sourceRecord);
				}
			}else if(record.getString("UUID","").equals(sourceObjUUID)){
				continue;
			}else{
				newRecordSet.add(record);
			}
		}
		if("inner".equals(moveType)){
			newRecordSet.add(sourceRecord);
		}
		
		Object[][] paramObject=new Object[newRecordSet.size()][2];
		for(int i=0;i<newRecordSet.size();i++){
			Record record=newRecordSet.get(i);
			paramObject[i][0]=i;
			paramObject[i][1]=record.getString("UUID");
		}
		
		sb=TreeSql.updateSortnum(tree);
		mqr.batch(conn,sb.toSqlString(),paramObject);
	}
}
