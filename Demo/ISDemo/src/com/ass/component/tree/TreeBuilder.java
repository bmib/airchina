/**  
 * @title TreeBuilder.java
 * @package com.isf.component.tree
 * @date 2013-3-26 下午10:32:15
 */ 
package com.ass.component.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.mybase.db.Record;
import com.mybase.db.RecordSet;

/**
 * 树构建工具类
 * 将RecordSet树节点集合转换为树结构JSON字符串，默认支持ZTREE(http://www.ztree.me)
 * @author Mr.liuyong
 */
public class TreeBuilder {
	
	//顶级节点的PID值，默认为空
	private String rootId="";
	//树节点唯一ID对应的属性值
	private String nodeIdColumn="ID";
	//树节点父亲节点ID对应的属性值
	private String nodeParentIdColumn="PID";
	//树节点对应儿子节点属性值
	private String nodeChildrenColumn="CHILDREN";
	//树的所有节点
	private RecordSet recordSet=new RecordSet();
	//第一层树节点
	private List<Record> topNodeList=new ArrayList<Record>();
	//节点对应的子节点
	private Map<String,List<Record>> parentMap=new HashMap<String,List<Record>>();
	
	/**
	 * 创建一个新的实例 TreeBuilder.
	 * @param _recordSet所有树节点
	 * @param _nodeIdColumn树节点唯一ID对应列名
	 * @param _nodeParentIdColumn树节点父亲节点ID对应列名
	 */
	public TreeBuilder(RecordSet _recordSet,String _nodeIdColumn,String _nodeParentIdColumn){
		this.recordSet = _recordSet;
		this.nodeIdColumn = _nodeIdColumn;
		this.nodeParentIdColumn = _nodeParentIdColumn;
	}
	
	/**
	 * 创建一个新的实例 TreeBuilder.
	 * @param _recordSet所有树节点
	 * @param _nodeIdColumn树节点唯一ID对应列名
	 * @param _nodeParentIdColumn树节点父亲节点ID对应列名
	 * @param _nodeChildrenColumn树节点对应子节点虚拟列名称
	 */
	public TreeBuilder(RecordSet _recordSet,String _nodeIdColumn,String _nodeParentIdColumn,String _nodeChildrenColumn){
		this.recordSet = _recordSet;
		this.nodeIdColumn = _nodeIdColumn;
		this.nodeParentIdColumn = _nodeParentIdColumn;
		this.nodeChildrenColumn=_nodeChildrenColumn;
	}
	
	/**
	 * 创建一个新的实例 TreeBuilder.
	 * @param _recordSet所有树节点
	 * @param _nodeIdColumn树节点唯一ID对应列名
	 * @param _nodeParentIdColumn树节点父亲节点ID对应列名
	 * @param _nodeChildrenColumn树节点对应子节点虚拟列名称
	 * @param _rootId最顶级树节点对应的PID
	 */
	public TreeBuilder(RecordSet _recordSet,String _nodeIdColumn,String _nodeParentIdColumn,String _nodeChildrenColumn,String _rootId){
		this.recordSet = _recordSet;
		this.nodeIdColumn = _nodeIdColumn;
		this.nodeParentIdColumn = _nodeParentIdColumn;
		this.nodeChildrenColumn=_nodeChildrenColumn;
		this.rootId=_rootId;
	}
	
	/**
	 * 获取树节点JSON字符串
	 * @Title: toTreeJsonString
	 * @Description: TODO
	 * @return String
	 */
	public String toTreeJsonString(){
		if(this.recordSet==null){
			return "[]";
		}else{
			for(int i=0;i<this.recordSet.size();i++){
				Record record=this.recordSet.get(i);
				String pid=record.getString(this.nodeParentIdColumn,"");
				if(this.rootId.equals(pid)){
					this.topNodeList.add(record);
				}
				if(this.parentMap.containsKey(pid)){
					this.parentMap.get(pid).add(record);
				}else{
					List<Record> list=new ArrayList<Record>();
					list.add(record);
					this.parentMap.put(pid, list);
				}
			}
			for(int i=0;i<this.topNodeList.size();i++){
				List<Record> temList=this.getChild(this.topNodeList.get(i).getString(this.nodeIdColumn));
				if(temList!=null){
					this.topNodeList.get(i).put(this.nodeChildrenColumn, temList);
				}
			}
			return JSONArray.toJSONString(topNodeList);
		}
	}
	
	
	public String toString(){
		return this.toTreeJsonString();
	}
	
	/**
	 * 根据节点唯一ID获取对应的子节点，递归算法
	 * @Title: getChild
	 * @Description: TODO
	 * @param key
	 * @return List<Record>
	 */
	private List<Record> getChild(String key){
		List<Record> recordlist=this.parentMap.get(key);
		if(recordlist==null){
			return null;
		}
		for(int i=0;i<recordlist.size();i++){
			List<Record> temList=this.getChild(recordlist.get(i).getString(this.nodeIdColumn));
			if(temList!=null){
				recordlist.get(i).put(this.nodeChildrenColumn, temList);
			}
		}
		return this.parentMap.get(key);
	}
	
}
