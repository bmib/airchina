/**  
 * @Title: SecurityFilter.java
 * @Package com.ass.framework.common.filter
 * @date 2012-11-19 下午03:18:26
 */ 
package com.ass.framework.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ass.framework.common.config.Constants;
import com.ass.framework.common.entity.LoginUser;
import com.mybase.util.UrlUtil;

/**
 * 访问权限的过滤器
 * @author Mr.liuyong
 */
public class SecurityFilter implements Filter {

	private static Logger log = Logger.getLogger(SecurityFilter.class);

    private String redirectURL = "";
    private String exceptUrlPattern="";

	/**
	 * 功能：初始化方法的实现
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		redirectURL = filterConfig.getInitParameter("redirectURL").trim(); 
		exceptUrlPattern = filterConfig.getInitParameter("exceptUrlPattern").trim(); 
	}

	/**
	 * 功能：访问权限的过滤(用户认证+URL认证)
	 */
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		LoginUser loginUser=null;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 分离exceptUrlPattern信息和获取当前URL地址
		String[] exceptUrlArray = exceptUrlPattern == null ? new String[0]: exceptUrlPattern.split("\\|");
		String currentUrl = request.getRequestURI();
		boolean exceptFlag = false;
		// 判断当前URL地址是否为不过滤的URL地址
		for (int i = 0; i < exceptUrlArray.length; i++) {
			if (currentUrl.indexOf(exceptUrlArray[i]) >= 0) {
				exceptFlag = true;
			}
		}
		try {
			loginUser=(LoginUser)request.getSession().getAttribute(Constants.LOGIN_USER_FLAG);
			if (!exceptFlag) {
				// 1、是否登录判断
				if (loginUser==null) {
					log.debug("拒绝非法用户登录，系统自动跳到登录页面");
					response.sendRedirect(request.getContextPath()+ redirectURL);
					return;
				}
				//2、是否有资源权限,针对sevlet的资源权限控制
				String resourceIdentifier=UrlUtil.getUrlResourceIdentifier(currentUrl);
				//ass/jsp/framework/frame 出现原因待查
				if (resourceIdentifier.toUpperCase().indexOf("FRAME") < 0 && resourceIdentifier.toUpperCase().indexOf("JSP") < 0 && !loginUser.getResourceMap().containsKey(resourceIdentifier)){
					log.debug("拒绝非法资源访问，系统自动跳到登录页面");
					response.sendRedirect(request.getContextPath()+ redirectURL);
					return;
				}
			}
		}catch (Exception e) {
			log.debug("发生异常，系统自动跳到登录页面",e);
			response.sendRedirect(request.getContextPath() + redirectURL);
			return;
		}
		LoginUser.set(loginUser);
		chain.doFilter(request, response);
	}

	/**
	 * 功能：实现销毁方法
	 */
	public void destroy() {}
}
