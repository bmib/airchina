/**  
 * @title ExceptionKiller.java
 * @package com.mybase.exception
 * @date 2013-3-27 下午09:15:56
 */ 
package com.mybase.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 异常处理类
 * 可通过继承ExceptionKiller，同时覆盖killException和killError方法来完成自定义的异常处理
 * 子类生效需在系统启动时通过setConcreteExceptionKiller指定，一般在listener中设置
 * @author Mr.liuyong
 */
public class ExceptionKiller {

	private static ExceptionKiller m_ins = new ExceptionKiller();

	protected ExceptionKiller(){}

	/**
	 * 设置具体的异常处理类，在系统启动时设置一次
	 * @param ek
	 */
	public final static void setConcreteExceptionKiller(ExceptionKiller ek){
		if(ek == null){
			throw new IllegalArgumentException("传入的异常处理类参数不能为空");
		}else{
			m_ins = ek;
		}
	}
	
	/**
	 * 分发处理ERROR和EXCEPTION
	 * 当然ERROR我们通常是无法处理的，主要针对EXCEPTION
	 * @param e
	 * @param request
	 * @param response
	 */
	public final static void killExceptionAndError(Throwable e, ServletRequest request, ServletResponse response){
		if(e instanceof Error){
			m_ins.killError((Error)e, request, response);
		}else{
			m_ins.killException((Exception)e, request, response);
		}
	}
	
	/**
	 * 处理异常，继承覆盖自行处理
	 * @param e
	 * @param request
	 * @param response
	 */
	public void killException(Exception e, ServletRequest request, ServletResponse response){
		this.killThrowable( e,  request,  response);
	}
	
	/**
	 * 处理错误，继承覆盖自行处理
	 * @param e
	 * @param request
	 * @param response
	 */
	public void killError(Error e, ServletRequest request, ServletResponse response){
		this.killThrowable( e,  request,  response);
	}
	
	/**
	 * 默认处理Exception和Error
	 * @param e
	 * @param request
	 * @param response
	 */
	private void killThrowable(Throwable e, ServletRequest request, ServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try{
			pw = response.getWriter();
			pw.println("");
			pw.println("我来自ExceptionKiller，看到我说明你写的代码有bug。");
			pw.println("顺便说下，如果你觉得我很丑，可以通过继承ExceptionKiller，重写异常处理，美化我，^_^");
			pw.println("简要异常信息：" + e.toString());
			pw.println("详细出错信息如下：");
			pw.println(this.getExceptionOrErrorDetail(e));
		}catch(Exception newe){
		}finally{
			if(pw != null){
				pw.close();
			}
		}
	}
	
	/**
	 * 将异常详细信息转换为字符串
	 * @param e
	 * @return
	 */
	public String getExceptionOrErrorDetail(Throwable e){
		//创建字符串流
		StringWriter sw = new StringWriter();
		//将字符串流绑定到文本输出流
		PrintWriter pw = new PrintWriter( sw );
		//将日志详细信息输出到文本输出流中
		e.printStackTrace( pw );
		pw.flush();
		sw.flush();
		//获取日志详细信息
		String detail = sw.toString();
		try{
			sw.close();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}finally{
			pw.close();
		}
		return detail;
	}
}
