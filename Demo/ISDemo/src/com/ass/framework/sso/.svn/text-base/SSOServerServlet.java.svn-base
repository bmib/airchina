/**
 * @title SSOServerServlet.java
 * @package com.ass.framework.sso
 * @date 2013-9-26 下午03:09:41
 */
package com.ass.framework.sso;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;

import com.ass.framework.common.util.OnlineUserUtil;
import com.mybase.base.AbstractBaseServlet;

/**
 * SIMPLE SSO Server
 * @author Mr.liuyong
 */
public class SSOServerServlet extends AbstractBaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void defaultMethod(HttpServletRequest request,HttpServletResponse response){
		verifyLogin(request,response);
	}
	
	/**
	 * SSO Server
	 * @param request
	 * @param response
	 */
	public void verifyLogin(HttpServletRequest request,HttpServletResponse response){
		//登录成功回跳地址
		String isfBackUrl=request.getParameter("isfBackUrl");
		if(isfBackUrl!=null){
			try {
				HttpSession session=request.getSession();
				//从服务器登录用户数据中判断当前sessionID是否已登录，如果登录返回登录用户的personUUID
				String personUUID=OnlineUserUtil.getUser(session.getId());
				if(personUUID!=null){
					//已登录
					String ISFUserToken=this.produceUserToken(personUUID);
					isfBackUrl=new String(URLCodec.decodeUrl(isfBackUrl.getBytes()));
					if(isfBackUrl.indexOf("?")>=0){
						isfBackUrl=isfBackUrl+"&ISFUserToken="+ISFUserToken;
					}else{
						isfBackUrl=isfBackUrl+"?ISFUserToken="+ISFUserToken;
					}
					this.redirectIgnoreException(isfBackUrl);
				}else{
					//未登录
					this.redirectIgnoreException(request.getContextPath());
				}
			} catch (Exception e) {
				this.redirectIgnoreException(request.getContextPath());
			}
		}else{
			this.redirectIgnoreException(request.getContextPath());
		}
	}
	
	/**
	 * 根据用户personUUID封装用户令牌，基于自定义的规则
	 * @param personUUID
	 * @return
	 */
	private String produceUserToken(String personUUID){
		long oTime=new Date().getTime();
		long newTime=oTime/2;
		if(newTime*2==oTime){
			personUUID=personUUID+"Y0"+"@"+oTime*3+"@"+newTime;
		}else{
			personUUID="XO"+personUUID+"@"+oTime*3+"@"+newTime;
		}
		return new String(Base64.encodeBase64(personUUID.getBytes(),true,true)).trim();
	}
}
