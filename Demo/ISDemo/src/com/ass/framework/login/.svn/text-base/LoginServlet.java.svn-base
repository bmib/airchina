/**
 * @title LoginServlet.java
 * @package com.ass.framework.login
 * @date 2013-7-3 下午03:21:02
 */
package com.ass.framework.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ass.framework.common.config.Constants;
import com.ass.framework.common.entity.LoginUser;
import com.ass.framework.common.exception.LoginException;
import com.ass.framework.common.util.OnlineUserUtil;
import com.mybase.base.AbstractBaseServlet;
import com.mybase.util.CookieUtil;

/**
 * 登录管理
 * @author Mr.liuyong
 */
public class LoginServlet extends AbstractBaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		login(request,response);
	}
	
	/**
	 * 系统登录
	 * @param request
	 * @param response
	 */
	public void login(HttpServletRequest request,HttpServletResponse response){
		LoginHandler handler=new LoginHandler();
		String account=this.getParameter("account","").trim();
		String password=this.getParameter("password","").trim();
		String verifyCode=this.getParameter("verifyCode","").trim().toUpperCase();
		HttpSession session = request.getSession();
		String verifyCodeInSession=(String)session.getAttribute(Constants.VERIFY_CODE_FLAG);
		//记住登录account
		CookieUtil.addCookie(response,"account",account,365*24*3600);
		//验证码校验
		if(!verifyCode.equals(verifyCodeInSession)){
			throw new LoginException("WRONGVERIFYCODE");
		}
		//登录校验
		String personUUID=handler.login(account,password);
		//用户数据初始化
		LoginUser loginUser=handler.iniUserData(personUUID);
		loginUser.setSessionID(session.getId());
		LoginUser.set(loginUser);
		session.setAttribute(Constants.LOGIN_USER_FLAG, loginUser);
		OnlineUserUtil.addUser(loginUser);
		this.redirectIgnoreException(request.getContextPath()+"/jsp/framework/frame/index.jsp");
	}
	
}
