/**
 * @title PFConfig.java
 * @package com.mybase.config
 * @date 2013-8-25 下午02:57:50
 */
package com.mybase.config;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Properties资源文件配置管理
 * @author Mr.liuyong
 */
public class PFConfig {
	
	private static Map<String,String> configsSystemMap = new ConcurrentHashMap<String,String>();
    private static PFConfig cu=new PFConfig();
    
    private PFConfig(){}
    
	/**
	 * 得到一个实例
	 * @return
	 */
    public static PFConfig getInstance(){
		if(cu==null) cu= new PFConfig();
		return cu;
	}
    
    /**
     * 根据传入键获取对应配置文件中的值
     * @param key
     * @return
     */
    public static String getProperty(String key){
		return PFConfig.getProperty(key, "");
	}
    
    /**
     * 根据传入键获取对应配置文件中的值
     * @param key
     * @return
     */
    public static int getPropertyInt(String key){
    	return Integer.parseInt(PFConfig.getProperty(key, "0"));
    }
    
    /**
     * 根据传入键获取对应配置文件中的值
     * @param key
     * @return
     */
    public static float getPropertyFloat(String key){
    	return Float.parseFloat(PFConfig.getProperty(key, "0"));
    }
    
    /**
     * 根据传入键获取对应配置文件中的值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key,String defaultValue){
    	String tem=PFConfig.getInstance().getSystemConfig().get(key);
		return tem==null?defaultValue:tem;
	}
    
    /**
     * 得到基础参数的configsSystemMap
     * @return
     */
	private  Map<String,String> getSystemConfig(){
		return configsSystemMap;
	}
	
	/**
	 * 加载基础参数放入configsSystemMap中
	 * @param fileName SRC目录下配置文件，例如**.properties
	 * @throws ConfigurationException 
	 */
	public void initConfig(String fileName) throws ConfigurationException{
		Configuration config;
		try {
			config = new PropertiesConfiguration(fileName);
			Iterator<String> it=config.getKeys();
			while(it.hasNext()){
				String key=it.next();
				configsSystemMap.put(key, config.getString(key));
			}
		} catch (ConfigurationException e) {
			throw e;
		}
	}
}
