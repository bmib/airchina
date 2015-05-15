/**
 * @title FrameServlet.java
 * @package com.ass.framework.frame
 * @date 2013-11-30 下午08:00:22
 */
package com.ass.framework.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ass.framework.common.entity.LoginUser;
import com.ass.framework.person.PersonHandler;
import com.mybase.base.AbstractBaseServlet;
import com.mybase.db.Record;

/**
 * 框架管理
 * @author Mr.liuyong
 */
public class FrameServlet extends AbstractBaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		toMaintainMyInfo(request,response);
	}

	/**
	 * 进入个人设置维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainMyInfo(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		LoginUser loginUser=LoginUser.getLoginUser();
		Record personRecord=handler.getPerson(loginUser.getPersonUUID());
		request.setAttribute("personRecord", personRecord);
		this.forwardIgnoreException("/jsp/framework/person/myInfo.jsp");
	}
	
	/**
	 * 修改个人设置
	 * @param request
	 * @param response
	 */
	public void modifyMyInfo(HttpServletRequest request,HttpServletResponse response){
		PersonHandler handler=new PersonHandler();
		//人员信息
		Record personRecord=this.getParameterWithPrefix("PERSON_");
		//账号信息
		Record accountRecord=this.getParameterWithPrefix("ACCOUNT_");
		boolean flag1=handler.modifyPerson(personRecord,null,null);
		boolean flag2=handler.modifyAccount(accountRecord);
		this.appendStringIgnoreException(flag1 && flag2);
	}
}
