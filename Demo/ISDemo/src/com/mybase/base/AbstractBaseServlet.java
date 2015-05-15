/**  
 * @title AbstractBaseServlet.java
 * @package com.mybase.base
 * @date 2013-3-19 下午08:10:40
 */ 
package com.mybase.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.mybase.db.Pagination;
import com.mybase.db.Record;
import com.mybase.exception.ExceptionKiller;

/**
 * Servlet父类抽象类-充当MVC中C的角色
 * @author Mr.liuyong
 */
public abstract class AbstractBaseServlet extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(AbstractBaseServlet.class);
	
	private static final long serialVersionUID = 1L;
	private static ThreadLocal<HttpServletRequest> m_request   = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> m_response = new ThreadLocal<HttpServletResponse>();
	
	protected AbstractBaseServlet(){}
	
	public final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	/**
	 * Servlet方法调用控制，已默认根据method参数进行查找
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		m_request.set(request);
		m_response.set(response);
		String methodName = request.getParameter("method");
		logger.debug("进入Servlet:"+this.getClass());
		logger.debug("执行Servlet方法:"+(methodName==null?"defaultMethod":methodName));
		try{
			if(methodName == null || "".equals(methodName)){
				this.defaultMethod(request, response);
			}else{
				Class<?>[] paramTypes = {Class.forName("javax.servlet.http.HttpServletRequest"),
									  Class.forName("javax.servlet.http.HttpServletResponse")};
				Method method = getClass().getMethod(methodName, paramTypes);
				Object[] paramValues = { request, response };
				method.invoke(this, paramValues);
			}
		}catch(InvocationTargetException e){
			ExceptionKiller.killExceptionAndError(e.getTargetException(), request, response);
		}catch (Throwable e) {
			ExceptionKiller.killExceptionAndError(e, request, response);
		}
	}
	
	/**
	 * 未传入method时默认执行的方法，供子类覆盖
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void defaultMethod(HttpServletRequest request, HttpServletResponse response) throws Exception{
		this.appendString("未传入Servlet的method参数，若要走默认，请在Servlet中重写defaultMethod方法");
	}
	
	/**
	 * 以字符形式返还，多用于配合AJAX使用
	 * @param s
	 * @throws IOException
	 */
	public void appendString(Object s) throws IOException{
		m_response.get().setContentType("text/html;charset=UTF-8");
		PrintWriter pw = m_response.get().getWriter();
		pw.write(String.valueOf(s));
		pw.close();
	}
	
	/**
	 * 以字符形式返还-忽略错误，多用于配合AJAX使用
	 * @param s
	 */
	public void appendStringIgnoreException(Object s){
		try {
			this.appendString(s);
		} catch (Throwable e) {
			ExceptionKiller.killExceptionAndError(e, m_request.get(), m_response.get());
		}
	}
	
	/**
	 * forward跳转
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forward(String url) throws ServletException, IOException{
		m_request.get().getRequestDispatcher(url).forward(m_request.get(), m_response.get());
	}
	
	/**
	 * forward跳转-忽略错误
	 * @param url
	 */
	public void forwardIgnoreException(String url){
		try {
			this.forward(url);
		} catch (Throwable e) {
			ExceptionKiller.killExceptionAndError(e, m_request.get(), m_response.get());
		}
	}
	
	/**
	 * redirect跳转
	 * @param url
	 * @throws IOException
	 */
	public void redirect(String url) throws IOException{
		m_response.get().sendRedirect(url);
	}
	
	/**
	 * redirect跳转-忽略错误
	 * @param url
	 */
	public void redirectIgnoreException(String url){
		try {
			this.redirect(url);
		} catch (Throwable e) {
			ExceptionKiller.killExceptionAndError(e, m_request.get(), m_response.get());
		}
	}
	
	/**
	 * 根据参数名从Request对象中获取单个参数值
	 * @param paramName
	 * @return String
	 */
	public String getParameter(String paramName){
		return m_request.get().getParameter(paramName);
	}
	
	/**
	 * 根据参数名从Request对象中获取单个参数值,带默认值
	 * @param paramName
	 * @param defaultValue
	 * @return String
	 */
	public String getParameter(String paramName,String defaultValue){
		String ret = this.getParameter(paramName);
		return ( ret == null || "".equals(ret) ) ? defaultValue : ret;
	}
	
	/**
	 * 根据参数名从Request对象中获取多个参数值
	 * @param paramName
	 * @return String[]
	 */
	public String[] getParameterValues(String paramName){
		return m_request.get().getParameterValues(paramName);
	}
	
	/**
	 * 根据指定的参数前缀从Request对象中获取参数值，并封装到Record对象
	 * 默认忽略NULL或空值，获取的均为单参数值
	 * @param prefix 参数前缀
	 * @return Record
	 */
	public Record getParameterWithPrefix(String prefix){
		return this.getParameterWithPrefix(prefix, true, false);
	}
	
	/**
	 * 根据指定的参数前缀从Request对象中获取参数值，并封装到Record对象
	 * @param prefix 参数前缀
	 * @param ignoredNullOrEmptyValue 是否忽略空白值的参数
	 * @param hasMultiValue 是否有一个参数对应多个值的情况
	 * @return Record
	 */
	public Record getParameterWithPrefix(String prefix, boolean ignoredNullOrEmptyValue, boolean hasMultiValue){
		if(prefix==null){prefix="";}
		Enumeration<?> paramEnum = m_request.get().getParameterNames();
		Record record = new Record();
		int prefixLength = prefix.length();
		while(paramEnum.hasMoreElements()){
		    String key = (String)paramEnum.nextElement();
			if(!key.startsWith(prefix)) continue;
		    if(hasMultiValue){
			    String values[] = m_request.get().getParameterValues(key);
			    if(ignoredNullOrEmptyValue && (values == null || values.length == 0)) continue;
			    record.put(key.substring(prefixLength).toUpperCase(), values.length == 1 ? values[0] : values);
		    }else{
			    String value = m_request.get().getParameter(key);
			    if(ignoredNullOrEmptyValue && (value == null || "".equals(value))) continue;			
			    record.put(key.substring(prefixLength).toUpperCase(), value);
		    }
		}
		return record;
	}
	
	/**
	 * 获取分页对象,默认每页10条记录
	 * @return Pagination
	 */
	public Pagination getPagination(){
		return this.getPagination(10);
	}
	
	/**
	 * 获取分页信息对象
	 * @param defaultPageSize
	 * @return Pagination
	 */
	public Pagination getPagination(int defaultPageSize){
		String pageNo = this.getParameter("mybasePageNo","1");
		String pageSize =this.getParameter("mybasePageSize",String.valueOf(defaultPageSize));
		//Base64编码URL及Form表单信息
		String encodeUrlAndParam=this.encodeUrlAndParam();
		Pagination pagingInfo = new Pagination();
		pagingInfo.setCurrentPageNo(Integer.parseInt(pageNo));
		pagingInfo.setPageSize(Integer.parseInt(pageSize));
		pagingInfo.setEncodingUrlAndParam(encodeUrlAndParam);
		return pagingInfo;
	}
	
	/**
	 * 拼装分页URL和param,并Base64编码
	 * @return String
	 */
	private String encodeUrlAndParam(){
		StringBuffer sb = new StringBuffer();
		String requestURI = m_request.get().getRequestURI();
		sb.append(requestURI);
		
		Enumeration<?> paramEnum = m_request.get().getParameterNames();
		if(paramEnum != null && paramEnum.hasMoreElements()){
			sb.append("?");
			while(paramEnum.hasMoreElements()){
			    String key = (String)paramEnum.nextElement();
			    String values[] = m_request.get().getParameterValues(key);
			    for(int i=0;i<values.length;i++){
			    	sb.append(key).append("=").append(values[i]).append("&");
			    }
			}
		}
		String string = sb.toString();
		//去除最后一个分隔符
		if(string.endsWith("&")) string = string.substring(0, string.length()-1);
		return Base64.encodeBase64String(string.getBytes());
	}
	
}
