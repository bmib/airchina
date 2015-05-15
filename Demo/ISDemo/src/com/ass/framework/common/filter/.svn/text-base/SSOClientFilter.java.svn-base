package com.ass.framework.common.filter;
/**  
 * @Title: SSOClientFilter.java
 * @Package com.ass.framework.common.filter
 * @date 2012-11-19 下午03:18:26
 */ 

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;

import com.ass.framework.common.entity.LoginInfo;


/**
 * SSO Client
 * @author Mr.liuyong
 */
public class SSOClientFilter implements Filter {

	/**
	 * 功能：初始化方法的实现
	 */
	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//优先本地判断是否已登录
		LoginInfo loginInfo=(LoginInfo)request.getSession().getAttribute("ASS_SSOClient_FLAG");
		
		if(loginInfo==null){
			loginInfo = this.analyzeISFUserToken(request);
			if(loginInfo==null){
				response.sendRedirect("/ass/servlet/SSOServer?isfBackUrl="+this.getEncodeUrlAndParam(request));
			}else{
				LoginInfo.set(loginInfo);
				request.getSession().setAttribute("ASS_SSOClient_FLAG", loginInfo);
				chain.doFilter(request, response);
			}
		}else{
			LoginInfo.set(loginInfo);
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * 解析用户令牌，进行本地登录
	 * @param request
	 * @return
	 */
	private LoginInfo analyzeISFUserToken(HttpServletRequest request){
		try{
			String ISFUserToken=request.getParameter("ISFUserToken");
			if(ISFUserToken==null){return null;}
			String userToken = new String(Base64.decodeBase64(new String(ISFUserToken).getBytes()));
			String[] userTokenArray=userToken.split("@");
			if(userTokenArray.length!=3){
				return null;
			}else{
				long oTime=Long.parseLong(userTokenArray[1]);
				long newTime=Long.parseLong(userTokenArray[2]);
				long distance=oTime-(newTime-12345)*2;
				String personUUID=userTokenArray[0];
				//认证30秒有效
				boolean timeOK=(new Date()).getTime()-oTime<=1000*30;
				if(distance==0 && personUUID.endsWith("Y0") && timeOK){
					personUUID=personUUID.substring(0,personUUID.length()-2);
					LoginInfo loginInfo=new LoginInfo();
					loginInfo.setPersonUUID(personUUID);
					return loginInfo;
				}else if(distance==1 && personUUID.startsWith("XO") && timeOK){
					personUUID=personUUID.substring(2);
					LoginInfo loginInfo=new LoginInfo();
					loginInfo.setPersonUUID(personUUID);
					return loginInfo;
				}else{
					return null;
				}
			}
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * 获取当前请求的Url地址及参数,同时进行Url编码
	 * @param request
	 * @return
	 */
	private String getEncodeUrlAndParam(HttpServletRequest request){
		String isfBackUrl=request.getRequestURI();
		boolean isFirstParam=true;
		Enumeration<?> paramEnum = request.getParameterNames();
		while(paramEnum.hasMoreElements()){
			String key = (String)paramEnum.nextElement();
			if("ISFUserToken".equals(key)){continue;}
			String values[] = request.getParameterValues(key);
			if(values== null || values.length==0){continue;}
			for(int i=0;i<values.length;i++){
				if(isFirstParam){
					isFirstParam=false;
					isfBackUrl=isfBackUrl+"?"+key+"="+values[i];
				}else{
					isfBackUrl=isfBackUrl+"&"+key+"="+values[i];
				}
			}
		}
		return new String(URLCodec.encodeUrl(null,isfBackUrl.getBytes()));
	}
	
	/**
	 * 功能：实现销毁方法
	 */
	public void destroy() {}
}
