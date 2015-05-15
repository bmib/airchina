/**
 * @title OrgServlet.java
 * @package com.ass.framework.org
 * @date 2013-7-3 下午02:52:58
 */
package com.ass.framework.org;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass.component.tree.TreeDefinition;
import com.ass.component.tree.TreeHandler;
import com.mybase.base.AbstractBaseServlet;
import com.mybase.db.Pagination;
import com.mybase.db.Record;

/**
 * 组织管理
 * @author Mr.liuyong
 */
public class OrgServlet extends AbstractBaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		getOrgTree(request,response);
	}
	
	/**
	 * 获取组织树,进入组织管理导航页面
	 * @param request
	 * @param response
	 */
	public void getOrgTree(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		request.setAttribute("treeJsonData", handler.getOrgTree());
		this.forwardIgnoreException("/jsp/framework/org/orgNav.jsp");
	}
	
	/**
	 * 查询组织列表
	 * @param request
	 * @param response
	 */
	public void getOrgList(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("orgRecordSet", handler.getOrgList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/org/orgList.jsp");
	}
	
	/**
	 * 进入组织维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainOrg(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		String orgUUID=this.getParameter("orgUUID");
		Record orgRecord=(orgUUID==null || "".equals(orgUUID))?new Record():handler.getOrg(orgUUID);
		request.setAttribute("orgRecord", orgRecord);
		this.forwardIgnoreException("/jsp/framework/org/orgMaintain.jsp");
	}
	
	/**
	 * 新增组织
	 * @param request
	 * @param response
	 */
	public void createOrg(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		Record storeRecord=this.getParameterWithPrefix("STORE_");
		this.appendStringIgnoreException(handler.createOrg(storeRecord));
	}
	
	/**
	 * 修改组织
	 * @param request
	 * @param response
	 */
	public void modifyOrg(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		Record storeRecord=this.getParameterWithPrefix("STORE_");
		this.appendStringIgnoreException(handler.modifyOrg(storeRecord));
	}
	
	/**
	 * 删除组织
	 * @param request
	 * @param response
	 */
	public void deleteOrg(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		String orgUUIDs=this.getParameter("orgUUIDs");
		this.appendStringIgnoreException(handler.deleteOrg(orgUUIDs));
	}
	
	/**
	 * 组织代码唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyOrgCode(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		String orgCode=this.getParameter("orgCode");
		String orgUUID=this.getParameter("orgUUID");
		this.appendStringIgnoreException(handler.verifyOrgCode(orgCode,orgUUID));
	}
	
	/**
	 * 组织移动
	 * @param request
	 * @param response
	 */
	public void moveOrgTree(HttpServletRequest request,HttpServletResponse response){
		TreeHandler handler=new TreeHandler();
		//移动信息
		String moveType=this.getParameter("moveType");
		String sameParentFlag=this.getParameter("sameParentFlag");
		String sourceObjUUID=this.getParameter("sourceObjUUID");
		String targetObjUUID=this.getParameter("targetObjUUID");
		//树描述
		TreeDefinition tree=new TreeDefinition();
		tree.setTableName("ASS_ORG");
		tree.setTableUUID("ORGUUID");
		tree.setTablePID("PID");
		tree.setTableLevelcode("LEVELCODE");
		tree.setTableSortNum("SORTNUM");
		this.appendStringIgnoreException(handler.moveTreeNode(tree,moveType,sameParentFlag,sourceObjUUID,targetObjUUID));
	}
	
}
