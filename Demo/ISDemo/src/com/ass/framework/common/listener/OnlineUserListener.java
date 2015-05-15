/**
 * @title OnlineUserListener.java
 * @package com.ass.framework.common.listener
 * @date 2013-9-30 上午10:55:38
 */
package com.ass.framework.common.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ass.framework.common.config.Constants;
import com.ass.framework.common.entity.LoginUser;
import com.ass.framework.common.util.OnlineUserUtil;

/**
 * session监听,在session失效时，从"在线用户"中移除当前用户
 * @author Mr.liuyong
 */
public class OnlineUserListener implements HttpSessionListener {
	 
    public void sessionCreated(HttpSessionEvent event) {}
    
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute(Constants.LOGIN_USER_FLAG);
        if(loginUser!=null){
        	OnlineUserUtil.removeUser(loginUser);
        }
    }
}