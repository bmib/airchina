/**
 * @title PersonServlet.java
 * @package com.ass.framework.person
 * @date 2013-7-3 下午03:10:13
 */
package com.ass.framework.person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass.framework.org.OrgHandler;
import com.mybase.base.AbstractBaseServlet;
import com.mybase.db.Pagination;
import com.mybase.db.Record;

/**
 * 人员管理
 * @author Mr.liuyong
 */
public class PersonServlet extends AbstractBaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		getOrgTree(request,response);
	}
	
	/**
	 * 获取组织树,进入人员管理导航页面
	 * @param request
	 * @param response
	 */
	public void getOrgTree(HttpServletRequest request,HttpServletResponse response){
		OrgHandler handler=new OrgHandler();
		request.setAttribute("treeJsonData", handler.getOrgTree());
		this.forwardIgnoreException("/jsp/framework/person/personNav.jsp");
	}
	
	/**
	 * 查询人员列表
	 * @param request
	 * @param response
	 */
	public void getPersonList(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("personRecordSet", handler.getPersonList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/person/personList.jsp");
	}
	
	/**
	 * 进入人员维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainPerson(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		String personUUID=this.getParameter("personUUID");
		Record personRecord=(personUUID==null || "".equals(personUUID))?new Record():handler.getPerson(personUUID);
		request.setAttribute("personRecord", personRecord);
		this.forwardIgnoreException("/jsp/framework/person/personMaintain.jsp");
	}
	
	/**
	 * 新增人员
	 * @param request
	 * @param response
	 */
	public void createPerson(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		//人员信息
		Record personRecord=this.getParameterWithPrefix("PERSON_");
		//人员所在组织信息
		Record orgRecord=this.getParameterWithPrefix("ORG_");
		//账号信息
		Record accountRecord=this.getParameterWithPrefix("ACCOUNT_");
		this.appendStringIgnoreException(handler.createPerson(personRecord,orgRecord,accountRecord));
	}
	
	/**
	 * 修改人员
	 * @param request
	 * @param response
	 */
	public void modifyPerson(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		//人员信息
		Record personRecord=this.getParameterWithPrefix("PERSON_");
		//人员所在组织信息
		Record orgRecord=this.getParameterWithPrefix("ORG_");
		//账号信息
		Record accountRecord=this.getParameterWithPrefix("ACCOUNT_");
		this.appendStringIgnoreException(handler.modifyPerson(personRecord,orgRecord,accountRecord));
	}
	
	/**
	 * 根据personUUIDs删除人员
	 * @param request
	 * @param response
	 */
	public void deletePerson(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		String personUUIDs=this.getParameter("personUUIDs");
		this.appendStringIgnoreException(handler.deletePerson(personUUIDs));
	}
	
	/**
	 * 人员编码唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyPersonCode(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		String personCode=this.getParameter("personCode");
		String personUUID=this.getParameter("personUUID");
		this.appendStringIgnoreException(handler.verifyPersonCode(personCode,personUUID));
	}
	
	/**
	 * 账号唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyAccount(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		String account=this.getParameter("account");
		String personUUID=this.getParameter("personUUID");
		this.appendStringIgnoreException(handler.verifyAccount(account,personUUID));
	}
	
	/**
	 * 前往修改账号密码
	 * @param request
	 * @param response
	 */
	public void toModifyPassword(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		String personUUID=this.getParameter("personUUID");
		Record personRecord=(personUUID==null || "".equals(personUUID))?new Record():handler.getPerson(personUUID);
		request.setAttribute("personRecord", personRecord);
		this.forwardIgnoreException("/jsp/framework/person/passwordMaintain.jsp");
	}
	
	/**
	 * 修改人员-账号密码
	 * @param request
	 * @param response
	 */
	public void modifyAccount(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		//账号信息
		Record accountRecord=this.getParameterWithPrefix("ACCOUNT_");
		this.appendStringIgnoreException(handler.modifyAccount(accountRecord));
	}
	
}
