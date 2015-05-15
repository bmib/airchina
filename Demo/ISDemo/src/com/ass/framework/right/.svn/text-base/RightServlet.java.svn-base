/**
 * @title RightServlet.java
 * @package com.ass.framework.right
 * @date 2013-7-3 下午03:19:11
 */
package com.ass.framework.right;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass.framework.person.PersonHandler;
import com.ass.framework.resource.ResourceHandler;
import com.mybase.base.AbstractBaseServlet;
import com.mybase.db.Pagination;
import com.mybase.db.Record;

/**
 * 权限管理
 * @author Mr.liuyong
 */
public class RightServlet extends AbstractBaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		getRoleList(request,response);
	}
	
	/**
	 * 查询角色管理列表
	 * @param request
	 * @param response
	 */
	public void getRoleList(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("roleRecordSet", handler.getRoleList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/right/roleList.jsp");
	}
	
	/**
	 * 查询用户组管理列表
	 * @param request
	 * @param response
	 */
	public void getUsergroupList(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("usergroupRecordSet", handler.getUsergroupList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/right/usergroupList.jsp");
	}
	
	/**
	 * 进入组织维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String roleUUID=this.getParameter("roleUUID");
		Record roleRecord=(roleUUID==null||"".equals(roleUUID))?new Record():handler.getRole(roleUUID);
		request.setAttribute("roleRecord", roleRecord);
		this.forwardIgnoreException("/jsp/framework/right/roleMaintain.jsp");
	}
	
	/**
	 * 进入用户组维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainUsergroup(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String usergroupUUID=this.getParameter("usergroupUUID");
		Record usergroupRecord=(usergroupUUID==null||"".equals(usergroupUUID))?new Record():handler.getUsergroup(usergroupUUID);
		request.setAttribute("usergroupRecord", usergroupRecord);
		this.forwardIgnoreException("/jsp/framework/right/usergroupMaintain.jsp");
	}
	
	/**
	 * 新增角色
	 * @param request
	 * @param response
	 */
	public void createRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Record roleRecord=this.getParameterWithPrefix("ROLE_");
		this.appendStringIgnoreException(handler.createRole(roleRecord));
	}
	
	/**
	 * 新增用户组
	 * @param request
	 * @param response
	 */
	public void createUsergroup(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Record usergroupRecord=this.getParameterWithPrefix("USERGROUP_");
		this.appendStringIgnoreException(handler.createUsergroup(usergroupRecord));
	}
	
	/**
	 * 修改角色
	 * @param request
	 * @param response
	 */
	public void modifyRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Record roleRecord=this.getParameterWithPrefix("ROLE_");
		this.appendStringIgnoreException(handler.modifyRole(roleRecord));
	}
	
	/**
	 * 修改用户组
	 * @param request
	 * @param response
	 */
	public void modifyUsergroup(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Record usergroupRecord=this.getParameterWithPrefix("USERGROUP_");
		this.appendStringIgnoreException(handler.modifyUsergroup(usergroupRecord));
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param response
	 */
	public void deleteRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String roleUUIDs=this.getParameter("roleUUIDs");
		this.appendStringIgnoreException(handler.deleteRole(roleUUIDs));
	}
	
	/**
	 * 删除用户组
	 * @param request
	 * @param response
	 */
	public void deleteUsergroup(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String usergroupUUIDs=this.getParameter("usergroupUUIDs");
		this.appendStringIgnoreException(handler.deleteUsergroup(usergroupUUIDs));
	}
	
	/**
	 * 前往给用户组分配角色
	 * @param request
	 * @param response
	 */
	public void toAssignRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String type="role";
		String usergroupUUIDs=this.getParameter("usergroupUUIDs");
		request.setAttribute("usergroupRecordSet", handler.getUsergroupMore(usergroupUUIDs));
		request.setAttribute("rightRecordSet", handler.getAllRightList(type));
		this.forwardIgnoreException("/jsp/framework/right/assignRole.jsp");
	}
	
	/**
	 * 根据usergroupUUID获取已分配的角色UUID字符串
	 * @param request
	 * @param response
	 */
	public void getUsergroupRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String usergroupUUID=this.getParameter("usergroupUUID");
		this.appendStringIgnoreException(handler.getUsergroupRoleUUIDS(usergroupUUID));
	}
	
	/**
	 * 用户组包含角色变更
	 * @param request
	 * @param response
	 */
	public void modifyUsergroupRole(HttpServletRequest request,HttpServletResponse response){
		RightHandler rightHandler=new RightHandler();
		//add新增|delete删除
		String modifyType=this.getParameter("modifyType"); 
		String rightUUID=this.getParameter("rightUUID"); 
		String usergroupUUID=this.getParameter("usergroupUUID");
		this.appendStringIgnoreException(rightHandler.modifyUsergroupRole(usergroupUUID,modifyType,rightUUID));
	}
	
	/**
	 * 前往给角色分配资源
	 * @param request
	 * @param response
	 */
	public void toAssignResource(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		ResourceHandler resourceHandler=new ResourceHandler();
		String roleUUIDs=this.getParameter("roleUUIDs");
		request.setAttribute("treeJsonData", resourceHandler.getResourceTree());
		request.setAttribute("roleRecordSet", handler.getRoleMore(roleUUIDs));
		this.forwardIgnoreException("/jsp/framework/right/assignResource.jsp");
	}
	
	/**
	 * 根据roleUUID获取已分配的资源UUID字符串
	 * @param request
	 * @param response
	 */
	public void getRoleResource(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String roleUUID=this.getParameter("roleUUID");
		this.appendStringIgnoreException(handler.getRoleResourceUUIDS(roleUUID));
	}
	
	/**
	 * 角色包含的资源变更
	 * @param request
	 * @param response
	 */
	public void modifyRoleResource(HttpServletRequest request,HttpServletResponse response){
		RightHandler rightHandler=new RightHandler();
		String roleUUID=this.getParameter("roleUUID");
		String resourceUUIDs=this.getParameter("resourceUUIDs"); 
		this.appendStringIgnoreException(rightHandler.modifyRoleResource(roleUUID,resourceUUIDs));
	}
	
	/**
	 * 人员角色权限维护列表
	 * @param request
	 * @param response
	 */
	public void toRightPerson(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("personRecordSet", handler.getRightPersonList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/right/rightPerson.jsp");
	}
	
	/**
	 * 进入给人员分配角色或用户组
	 * @param request
	 * @param response
	 */
	public void toAssignRight(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		RightHandler rightHandler=new RightHandler();
		String personUUIDs=this.getParameter("personUUIDs");
		request.setAttribute("personRecordSet", handler.getPersonMore(personUUIDs));
		request.setAttribute("roleRecordSet", rightHandler.getAllRightList("ROLE"));
		request.setAttribute("usergroupRecordSet", rightHandler.getAllRightList("USERGROUP"));
		request.setAttribute("commonRightMap", rightHandler.getPersonsCommonRightUUIDS(personUUIDs));
		this.forwardIgnoreException("/jsp/framework/right/assignRight.jsp");
	}
	
	/**
	 * 权限变更（角色、用户组）
	 * @param request
	 * @param response
	 */
	public void modifyRight(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		//ROLE 角色权限|USERGROUP 用户组权限
		String type=this.getParameter("type");
		//ADD 新增|DELETE 删除
		String modifyType=this.getParameter("modifyType"); 
		String rightUUID=this.getParameter("rightUUID");
		String personUUIDs=this.getParameter("personUUIDs");
		this.appendStringIgnoreException(handler.modifyPersonRight(type,personUUIDs,modifyType,rightUUID));
	}
	
	/**
	 * 角色编码唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyRoleCode(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String roleCode=this.getParameter("roleCode");
		String roleUUID=this.getParameter("roleUUID");
		this.appendStringIgnoreException(handler.verifyRoleCode(roleCode,roleUUID));
	}
	
	/**
	 * 用户组编码唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyUsergroupCode(HttpServletRequest request,HttpServletResponse response){
		RightHandler handler=new RightHandler();
		String usergroupCode=this.getParameter("usergroupCode");
		String usergroupUUID=this.getParameter("usergroupUUID");
		this.appendStringIgnoreException(handler.verifyUsergroupCode(usergroupCode,usergroupUUID));
	}
}
