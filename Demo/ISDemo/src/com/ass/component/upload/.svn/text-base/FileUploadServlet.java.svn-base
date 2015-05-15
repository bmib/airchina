/**
 * @title FileUploadServlet.java
 * @package com.isf.component.upload
 * @date 2013-7-3 下午03:21:02
 */
package com.ass.component.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybase.base.AbstractBaseServlet;

/**
 * 文件上传
 * @author Mr.liuyong
 */
public class FileUploadServlet extends AbstractBaseServlet{

	private static final long serialVersionUID = 1L;

	// 默认的执行方法
	public void defaultMethod(HttpServletRequest request,
			HttpServletResponse response) {
		upload(request,response);
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 */
	public void upload(HttpServletRequest request,HttpServletResponse response){
		FileUploadHandler fileUploadHandler = new FileUploadHandler();
		//保存文件到服务器中，上传完成后，将文件的UUID返回到页面
		String fileUUIDS = fileUploadHandler.uploadFile(request);
		this.appendStringIgnoreException(fileUUIDS);
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 */
	public void download(HttpServletRequest request,HttpServletResponse response){
		
	}
	
}
