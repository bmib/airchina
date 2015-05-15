/**
 * @title PathUtil.java
 * @package com.mybase.util
 * @date 2013-12-10 上午09:56:54
 */
package com.mybase.util;

import java.io.File;

/**
 * 路径工具类
 * @author Mr.liuyong
 */
public class PathUtil {
	private static String webRootPath;
    private static String rootClassPath;
    
    /**
     * 获取类路径
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
	public static String getPath(Class clazz) {
        String path = clazz.getResource("").getPath();
        System.out.println(path);
        return new File(path).getAbsolutePath();
    }
    
    /**
     * 获取class文件所在根目录
     * @return
     */
    public static String getRootClassPath() {
        if (rootClassPath == null) {
            try {
                String path = PathUtil.class.getClassLoader().getResource("").toURI().getPath();
                rootClassPath = new File(path).getAbsolutePath();
            }catch (Exception e) {
                String path = PathUtil.class.getClassLoader().getResource("").getPath();
                rootClassPath = new File(path).getAbsolutePath();
            }
        }
        return rootClassPath;
    }
    
    /**
     * 获取包路径
     * @param object
     * @return
     */
    public static String getPackagePath(Object object) {
        Package p = object.getClass().getPackage();
        return p != null ? p.getName().replaceAll("\\.", "/") : "";
    }
    
    /**
     * 获取WebRoot路径
     * @return
     */
    public static String getWebRootPath() {
        if (webRootPath == null){
        	webRootPath = detectWebRootPath();
        }
        return webRootPath;
    }
    
    /**
     * 设置WebRoot路径
     * @param webRootPath
     */
    public static void setWebRootPath(String webRootPath) {
        if (webRootPath == null){
        	return ;
        }
        if (webRootPath.endsWith(File.separator)){
        	webRootPath = webRootPath.substring(0, webRootPath.length() - 1);
        }
        PathUtil.webRootPath = webRootPath;
    }
    
    /**
     * 检测WebRoot路径
     * @return
     */
    private static String detectWebRootPath() {
        try {
            String path = PathUtil.class.getResource("/").toURI().getPath();
            return new File(path).getParentFile().getParentFile().getCanonicalPath();
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
}
