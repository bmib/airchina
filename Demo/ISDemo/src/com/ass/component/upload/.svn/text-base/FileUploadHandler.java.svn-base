/**
 * @title FileUploadHandler.java
 * @package com.isf.component.upload
 * @date 2013-7-3 下午03:21:02
 */
package com.ass.component.upload;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.mybase.config.DBConfig;
import com.mybase.db.DBToolkit;
import com.mybase.db.Record;
import com.mybase.util.DateTimeUtil;

/**
 * 文件上传处理
 * @author Mr.liuyong
 */
public class FileUploadHandler {
	/**
	 * 日志记录
	 */
	private static Logger log = Logger.getLogger(FileUploadHandler.class);
	
	/**
	 * 文件上传
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String uploadFile(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置最多只允许在内存中存储的数据,单位:字节 ,这里设置为50M
		factory.setSizeThreshold(50*1024*1024);
		//临时缓冲文件目录,此处系统默认   
	    File tempfile = new File(System.getProperty("java.io.tmpdir"));  
	    //设置缓冲区目录,一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录   
	    factory.setRepository(tempfile);   
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置编码，解决上传文件名乱码  
		upload.setHeaderEncoding("UTF-8");   
		//设置一共最多能上传文件大小,单位:字节 5G
		upload.setSizeMax(5000*1024*1024);
		try {
			//文件信息在数据库中的UUID
			StringBuffer uuids = new StringBuffer();
			String storeFolder=request.getParameter("storeFolder");
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iterator = fileItems.iterator();
			while (iterator.hasNext()) {
				FileItem item = iterator.next();
				if (!item.isFormField()) {
					//文件写入硬盘，文件信息写入数据库
					Record fileRecord=this.storeFile(storeFolder,item);
					//将文件存储相关信息返回到页面
					uuids.append("{\"UUID\":\"")
						 .append(fileRecord.getString("UUID"))
						 .append("\",\"fileName\":\"")
						 .append(fileRecord.getString("FILENAME"))
						 .append("\"}");
				}
			}
			return new StringBuffer().append("[").append(uuids.toString().replaceAll("\\}\\{", "},{")).append("]").toString();
		} catch (Exception e) {
			log.error("上传文件",e);
			throw new RuntimeException("上传文件",e);
		}
	}
	
	/**
	 * 文件写入硬盘，文件信息写入数据库
	 * @param storeFolder
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public Record storeFile(String storeFolder,FileItem item) throws Exception{
		//上传根目录
		String rootPath=DBConfig.getProperty("STORE_FILE_BASE","/ass");
		//上传最终目录
		String finalPath=rootPath;
		if(storeFolder!=null && !"".equals(storeFolder)){
			finalPath=finalPath+File.separator+storeFolder+File.separator+DateTimeUtil.getSDateBy7();
		}else{
			finalPath=finalPath+File.separator+DateTimeUtil.getSDateBy7();
		}
		File folderPath = new File(finalPath);
		if (!folderPath.exists()) {
			folderPath.mkdirs();
		}
		//文件名
		String fileName = item.getName();
		//文件后缀名及大小
		String[] strs = fileName.split("\\.");
		String fileSuffix=strs.length<2?"":strs[strs.length-1];
		String fileSize=String.valueOf(item.getSize());
		//新文件名
		String uuid=UUID.randomUUID().toString();
		String storeFileName =uuid +"."+fileSuffix;
		//存储路径及文件名
		String dbFilePath=finalPath+File.separator+storeFileName;
		item.write(new File(dbFilePath));
		//文件数据返回
		Connection conn=null;
		try {
			conn=DBToolkit.getConnection();
			Record record=new Record(conn,"ASS_FILEINFO");
			record.put("UUID",uuid);
			record.put("FILENAME",fileName);
			record.put("STOREFILENAME",storeFileName);
			record.put("FILESUFFIX",fileSuffix);
			record.put("FILESIZE",fileSize);
			record.put("PATH",dbFilePath);
			record.put("STATUS",0);
			record.put("CREATETIME",DateTimeUtil.getCurTimestamp());
			record.create();
			return record;
		}catch (SQLException e) {
			log.error("文件信息写入数据库中",e);
			throw new RuntimeException("文件信息写入数据库中",e);
		}finally{
			DBToolkit.close(conn);
		}
	}
	
}
