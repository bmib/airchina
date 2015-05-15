/**
 * @title EhcacheUtil.java
 * @package com.mybase.util
 * @date 2013-10-7 下午06:32:43
 */
package com.mybase.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * EHCache 操作类
 * @author Mr.liuyong
 */
public class EhcacheUtil {
	  
    private CacheManager manager;  
  
    private static EhcacheUtil ehCache;  
    
    /**
     * 加载EHCache SRC目录下默认配置文件名ehcache.xml
     */
    private EhcacheUtil() {  
    	CacheManager.create();   
        manager= CacheManager.getInstance(); 
    } 
    
    /**
     * 获取实例
     * @return
     */
    public static EhcacheUtil getInstance() {  
        if (ehCache== null) {  
            ehCache= new EhcacheUtil();  
        }  
        return ehCache;  
    } 
    
    /**
     * 向缓存写入数据
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = new Element(key, value);  
        cache.put(element);  
    }  
  
    /**
     * 获取缓存中数据
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
  
    /**
     * 获取缓存对象
     * @param cacheName
     * @return
     */
    public Cache get(String cacheName) {  
        return manager.getCache(cacheName);  
    }  
  
    /**
     * 移除缓存中的数据
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        cache.remove(key);  
    }  
}
