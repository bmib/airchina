/**
 * @title ResourceServlet.java
 * @package com.ass.framework.resource
 * @date 2013-7-3 下午03:16:19
 */
package com.ass.framework.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass.component.tree.TreeDefinition;
import com.ass.component.tree.TreeHandler;
import com.mybase.base.AbstractBaseServlet;
import com.mybase.db.Pagination;
import com.mybase.db.Record;

/**
 * 资源管理
 * @author Mr.liuyong
 */
public class ResourceServlet extends AbstractBaseServlet {

	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		getResourceTree(request,response);
	}
	
	/**
	 * 获取资源树,进入资源管理导航页面
	 * @param request
	 * @param response
	 */
	public void getResourceTree(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		request.setAttribute("treeJsonData", handler.getResourceTree());
		this.forwardIgnoreException("/jsp/framework/resource/resourceNav.jsp");
	}
	
	/**
	 * 查询资源列表
	 * @param request
	 * @param response
	 */
	public void getResourceList(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("resourceRecordSet", handler.getResourceList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/resource/resourceList.jsp");
	}
	
	/**
	 * 进入资源维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainResource(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		String resourceUUID=this.getParameter("resourceUUID");
		Record resourceRecord=(resourceUUID==null || "".equals(resourceUUID))?new Record():handler.getResource(resourceUUID);
		request.setAttribute("resourceRecord", resourceRecord);
		this.forwardIgnoreException("/jsp/framework/resource/resourceMaintain.jsp");
	}
	
	/**
	 * 新增资源
	 * @param request
	 * @param response
	 */
	public void createResource(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		Record storeRecord=this.getParameterWithPrefix("STORE_");
		this.appendStringIgnoreException(handler.createResource(storeRecord));
	}
	
	/**
	 * 修改资源
	 * @param request
	 * @param response
	 */
	public void modifyResource(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		Record storeRecord=this.getParameterWithPrefix("STORE_");
		this.appendStringIgnoreException(handler.modifyResource(storeRecord));
	}
	
	/**
	 * 删除资源
	 * @param request
	 * @param response
	 */
	public void deleteResource(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		String resourceUUIDs=this.getParameter("resourceUUIDs");
		this.appendStringIgnoreException(handler.deleteResource(resourceUUIDs));
	}
	
	/**
	 * 资源编码唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyResourceCode(HttpServletRequest request,HttpServletResponse response){
		ResourceHandler handler=new ResourceHandler();
		String resourceCode=this.getParameter("resourceCode");
		String resourceUUID=this.getParameter("resourceUUID");
		this.appendStringIgnoreException(handler.verifyResourceCode(resourceCode,resourceUUID));
	}
	
	/**
	 * 资源移动
	 * @param request
	 * @param response
	 */
	public void moveResourceTree(HttpServletRequest request,HttpServletResponse response){
		TreeHandler handler=new TreeHandler();
		//移动信息
		String moveType=this.getParameter("moveType");
		String sameParentFlag=this.getParameter("sameParentFlag");
		String sourceObjUUID=this.getParameter("sourceObjUUID");
		String targetObjUUID=this.getParameter("targetObjUUID");
		//树描述
		TreeDefinition tree=new TreeDefinition();
		tree.setTableName("ASS_RESOURCE");
		tree.setTableUUID("RESOURCEUUID");
		tree.setTablePID("PID");
		tree.setTableLevelcode("LEVELCODE");
		tree.setTableSortNum("SORTNUM");
		this.appendStringIgnoreException(handler.moveTreeNode(tree,moveType,sameParentFlag,sourceObjUUID,targetObjUUID));
	}

}
