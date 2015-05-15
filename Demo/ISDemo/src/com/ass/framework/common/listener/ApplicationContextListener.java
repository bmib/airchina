/**  
 * @Title: ApplicationContextListener.java
 * @Package com.ibase.common.listener
 * @date 2012-11-19 下午03:18:26
 */ 
package com.ass.framework.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ass.framework.common.exception.MyExceptionKiller;
import com.mybase.config.DBConfig;
import com.mybase.exception.ExceptionKiller;

/**
 * 启动预处理
 * @author Mr.liuyong
 */
public class ApplicationContextListener implements ServletContextListener{
	
	private Logger log = Logger.getLogger(ApplicationContextListener.class);
	
	public void contextDestroyed(ServletContextEvent arg0){
		//本方法会阻塞应用的停止过程，直到此方法执行完毕，停止应用的操作才会继续。
		//应用停止TODO
	}

	public void contextInitialized(ServletContextEvent event){
		try{
			log.info("系统配置加载开始");
			DBConfig.getInstance().initConfig("ASS_CONFIG","PARAMCODE","PARAMVALUE");
			log.info("成功加载数据库系统基础参数配置ASS_CONFIG");
			ExceptionKiller.setConcreteExceptionKiller(new MyExceptionKiller());
			log.info("成功绑定错误处理MyExceptionKiller");
			log.info("系统配置加载完成");
		} catch (Exception e){
			log.error("系统配置加载失败，请排查！"+e);
		}
		//本方法会阻塞应用的启动过程，直到此方法执行完毕，应用才会继续启动。
	}

}
