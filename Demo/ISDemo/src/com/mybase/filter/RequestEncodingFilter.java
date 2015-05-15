/**  
 * @title RequestEncodingFilter.java
 * @package com.mybase.filter
 * @date 2013-3-16 下午09:41:38
 */ 
package com.mybase.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 字符编码过滤器,默认为UTF-8,如要指定请传入encoding参数
 * @author Mr.liuyong
 */
public class RequestEncodingFilter implements Filter{
	
  private String encoding;
  
  /**
   * 参数初始化
   * @param paramFilterConfig
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  public void init(FilterConfig paramFilterConfig){
    this.encoding = paramFilterConfig.getInitParameter("encoding");
    if(this.encoding == null || "".equals(this.encoding)){
    	this.encoding = "UTF-8";
    }
  }
  
  /**
   * 设置字符编码
   * @param paramServletRequest
   * @param paramServletResponse
   * @param paramFilterChain
   * @throws IOException
   * @throws ServletException
   */
  public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain)
    throws IOException, ServletException{
    HttpServletRequest localHttpServletRequest = (HttpServletRequest)paramServletRequest;
    localHttpServletRequest.setCharacterEncoding(this.encoding);
    paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
  }
  
  /**
   * 销毁
   */
  public void destroy(){}
}