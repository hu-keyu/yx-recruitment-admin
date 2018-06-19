package org.jypj.dev.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jypj.dev.entity.User;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.StringUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginStudentHandlerInterceptor extends HandlerInterceptorAdapter {
    //一般请求，只验证recruitId的action
    private String[] urls = new String[]{
                                         "dg/studentApplyInfo/register",
                                         "dg/studentApplyInfo/saveProfile",
                                         "dg/studentApplyInfo/loginVerify",
                                         "dg/studentApplyInfo/isExistSid",
                                         "dg/studentApplyInfo/verifyRegister"};
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	    response.setHeader("sessionoutUrl", request.getContextPath()+"/login/studentOutTime");
	    //判断是否是ajax
	    String requestType = request.getHeader("X-Requested-With");
	    if (StringUtil.isNotEmpty(requestType)) {
	        //session没有招聘主题， 则必须退出
	        if (request.getSession().getAttribute("recruitId") == null) {
                response.setHeader("sessionstatus", "timeout");
                response.setStatus(500);
                return false;
	        }
	        //考生id没找到，也要退出   
	        if(request.getSession().getAttribute("sid") == null && !isNeedRequest(request)) {
                response.setHeader("sessionstatus", "timeout");
                response.setStatus(500);
                return false;
	        }
	    } else {
	        //session没有招聘主题， 则必须退出
	        if (request.getSession().getAttribute("recruitId") == null) {
	            response.sendRedirect(request.getContextPath()+"/login/studentOutTime");
	            return false;
	        }
	        //考生id没找到，也要退出   
	        if(request.getSession().getAttribute("sid") == null && !isNeedRequest(request)) {
	             response.sendRedirect(request.getContextPath()+"/login/studentOutTime");
	             return false;
	        }
	    }
		return true;
	}
	
	/**
	 * 返回true 表示这些action不用验证session里的sid
	 * @param request
	 * @return
	 */
	public boolean isNeedRequest(HttpServletRequest request) {
	    String url = request.getRequestURI();
	    String urlStr = url.substring(url.indexOf("dg/"), url.length());
	    for (String s : urls) {
	        if (s.equals(urlStr)) {
	            return true;
	        }
	    }
	    return false;
	}

}
