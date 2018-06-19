package org.jypj.dev.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jypj.dev.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		
		if(user!=null&&request.getParameter("topMenu")!=null)
		{
			request.getSession().setAttribute("firstIndex", request.getParameter("firstIndex")==null?0: request.getParameter("firstIndex"));
			request.getSession().setAttribute("secondIndex", request.getParameter("secondIndex")==null?0: request.getParameter("secondIndex"));
		}
		if(user==null){
			 response.sendRedirect(request.getContextPath()+"/login/outTime");
			 return false;
		}
		return true;
	}
}
