/**
 * @title TreeSql.java
 * @package com.isf.component.tree
 * @date 2013-8-12 下午07:41:48
 */
package com.ass.component.tree;

import com.mybase.db.SQLBuilder;

/**
 * 树形结构移动树节点操作
 * @author Mr.liuyong
 */
public class TreeSql {
	/**
	 * 根据传入的父节点主键PID，计算新创建组织的LEVELCODE和SORTNUM
	 * @param tree 树形结构表的相关信息
	 * @param pid
	 * @return
	 */
	public static SQLBuilder getLevelAndSort(TreeDefinition tree,String pid){
		SQLBuilder sb=SQLBuilder.getInstance();
		if(pid==null || "".equals(pid)){
			sb.append("SELECT '' PLEVELCODE,CONCAT(MAX(T."+tree.getTableLevelcode()+")+1,'') LEVELCODE,CONCAT(MAX(T."+tree.getTableSortNum()+")+1,'') SORTNUM FROM "+tree.getTableName()+" T ");
			sb.append(" WHERE T."+tree.getTablePID()+" IS NULL OR T."+tree.getTablePID()+" = ''");
		}else{
			sb.append("SELECT CONCAT(MAX(O."+tree.getTableLevelcode()+"),'') PLEVELCODE,CONCAT(MAX(SUBSTR(T."+tree.getTableLevelcode()+", LENGTH(T."+tree.getTableLevelcode()+") - 3)) + 1,'') LEVELCODE,CONCAT(MAX(T."+tree.getTableSortNum()+") + 1,'') SORTNUM ");
			sb.append(" FROM "+tree.getTableName()+" O LEFT JOIN "+tree.getTableName()+" T ON T."+tree.getTablePID()+" = O."+tree.getTableUUID()+" WHERE O."+tree.getTableUUID()+" = ?");
			sb.appendValue(pid);
		}
		return sb;
	}
	/**
	 * 组织树移动-获取目标节点的父节点
	 * @param tree 树形结构表的相关信息
	 * @param targetObjUUID
	 * @return
	 */
	public static SQLBuilder getParentNode(TreeDefinition tree,String targetObjUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append("SELECT T."+tree.getTablePID()+" PID FROM "+tree.getTableName()+" T WHERE T."+tree.getTableUUID()+" = ? ");
		sb.appendValue(targetObjUUID);
		return sb;
	}
	
	/**
	 * 组织树移动-用新的levelcode更新源及子节点
	 * @param tree 树形结构表的相关信息
	 * @param sourceObjUUID
	 * @param newLevelcode
	 * @return
	 */
	public static SQLBuilder updateLevelcode(TreeDefinition tree,String sourceObjUUID,String newLevelcode){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" UPDATE "+tree.getTableName()+" O SET O."+tree.getTableLevelcode()+"=REPLACE(O."+tree.getTableLevelcode()+",(SELECT O1."+tree.getTableLevelcode()+" FROM (SELECT * FROM "+tree.getTableName()+") O1 WHERE O1."+tree.getTableUUID()+"= ?),?)");
		sb.append(" WHERE O."+tree.getTableLevelcode()+" LIKE (SELECT CONCAT(T."+tree.getTableLevelcode()+",'%') FROM (SELECT * FROM "+tree.getTableName()+") T WHERE T."+tree.getTableUUID()+"=?)");
		sb.appendValue(sourceObjUUID);
		sb.appendValue(newLevelcode);
		sb.appendValue(sourceObjUUID);
		return sb;
	}
	
	/**
	 * 组织树移动-获取第一级子节点
	 * @param tree 树形结构表的相关信息
	 * @param parentUUID
	 * @return
	 */
	public static SQLBuilder getChildNode(TreeDefinition tree,String parentUUID){
		SQLBuilder sb=SQLBuilder.getInstance();
		if(parentUUID==null || "".equals(parentUUID)){
			sb.append(" select T."+tree.getTableUUID()+" UUID from "+tree.getTableName()+" t WHERE T."+tree.getTablePID()+" IS NULL ORDER BY T."+tree.getTableSortNum()+" ");
		}else{
			sb.append(" select T."+tree.getTableUUID()+" UUID from "+tree.getTableName()+" t WHERE T."+tree.getTablePID()+"= ? ORDER BY T."+tree.getTableSortNum()+" ");
			sb.appendValue(parentUUID);
		}
		return sb;
	}
	
	/**
	 * 组织树移动-更新排序号
	 * @param tree 树形结构表的相关信息
	 * @return
	 */
	public static SQLBuilder updateSortnum(TreeDefinition tree){
		SQLBuilder sb=SQLBuilder.getInstance();
		sb.append(" UPDATE "+tree.getTableName()+" O SET O."+tree.getTableSortNum()+" = ? WHERE O."+tree.getTableUUID()+" = ? ");
		return sb;
	}
	
}
