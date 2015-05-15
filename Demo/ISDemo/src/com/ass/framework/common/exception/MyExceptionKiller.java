/**  
 * @Title: ExceptionKiller.java
 * @Package com.ass.framework.common.exception
 * @date 2012-3-1 下午04:13:27
 */ 
package com.ass.framework.common.exception;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybase.exception.ExceptionKiller;

/**
 * 自定义异常处理
 * @author Mr.liuyong
 */
public class MyExceptionKiller extends ExceptionKiller {
	
	public void killException(Exception e, ServletRequest request, ServletResponse response){
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		try{
			try{
				throw e;
			}catch(LoginException le){
				//登录异常
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp?exceptionCode="+le.getMessage());
			}catch(Exception ne){
				String exceptionMsg=this.getExceptionOrErrorDetail(ne.getCause()==null?ne:ne.getCause());
				httpRequest.setAttribute("exceptionTitle", ne.getMessage());
				httpRequest.setAttribute("exceptionMsg", exceptionMsg);
				httpRequest.getRequestDispatcher("/jsp/common/error.jsp").forward(httpRequest, httpResponse);
			}
		}catch(Exception oe){
			e.printStackTrace();
		}
	}
}
