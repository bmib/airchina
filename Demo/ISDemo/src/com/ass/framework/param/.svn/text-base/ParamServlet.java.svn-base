/**
 * @title ParamServlet.java
 * @package com.ass.framework.param
 * @date 2013-7-3 下午02:52:58
 */
package com.ass.framework.param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybase.base.AbstractBaseServlet;
import com.mybase.db.Pagination;
import com.mybase.db.Record;

/**
 * 参数配置管理
 * @author Mr.liuyong
 */
public class ParamServlet extends AbstractBaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		getParamList(request,response);
	}

	/**
	 * 查询参数配置列表
	 * @param request
	 * @param response
	 */
	public void getParamList(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		Pagination pagination=this.getPagination();
		Record paramRecord=this.getParameterWithPrefix("SEARCH_");
		request.setAttribute("paramRecordSet", handler.getParamList(paramRecord,pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/param/paramList.jsp");
	}
	
	/**
	 * 进入参数配置维护页面
	 * @param request
	 * @param response
	 */
	public void toMaintainParam(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		String UUID=this.getParameter("UUID");
		Record paramRecord=(UUID==null || "".equals(UUID))?new Record():handler.getParam(UUID);
		request.setAttribute("paramRecord", paramRecord);
		this.forwardIgnoreException("/jsp/framework/param/paramMaintain.jsp");
	}
	
	/**
	 * 新增参数配置
	 * @param request
	 * @param response
	 */
	public void createParam(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		Record storeRecord=this.getParameterWithPrefix("STORE_");
		this.appendStringIgnoreException(handler.createParam(storeRecord));
	}
	
	/**
	 * 修改参数配置
	 * @param request
	 * @param response
	 */
	public void modifyParam(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		Record storeRecord=this.getParameterWithPrefix("STORE_");
		this.appendStringIgnoreException(handler.modifyParam(storeRecord));
	}
	
	/**
	 * 删除参数配置
	 * @param request
	 * @param response
	 */
	public void deleteParam(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		String UUIDs=this.getParameter("UUIDs");
		this.appendStringIgnoreException(handler.deleteParam(UUIDs));
	}
	
	/**
	 * 参数配置代码唯一性校验
	 * @param request
	 * @param response
	 */
	public void verifyParamCode(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		String paramRecord=this.getParameter("paramCode");
		String UUID=this.getParameter("UUID");
		this.appendStringIgnoreException(handler.verifyParamCode(paramRecord,UUID));
	}
	
	/**
	 * 重载参数
	 * @param request
	 * @param response
	 */
	public void reloadParam(HttpServletRequest request,HttpServletResponse response){
		ParamHandler handler=new ParamHandler();
		this.appendStringIgnoreException(handler.reloadParam());
	}
	
}
