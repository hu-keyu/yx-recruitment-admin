package org.jypj.dev.intercept;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jypj.dev.entity.User;



/**
 * 
 * @author QiCai
 *
 */
public class SessionListener implements HttpSessionListener{
	
	public static HashMap<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();
	
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext context = session.getServletContext();
		Integer count = (Integer) context.getAttribute("peopleOnline");
		if(count!=null){
			count++;
		}else {
			count=1;
		}
		context.setAttribute("peopleOnline", count);
    }
	
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
		Integer count = (Integer) context.getAttribute("peopleOnline");
		count--;
		this.DelSession(session);
        context.setAttribute("peopleOnline", count);
    }
     
    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            // 删除单一登录中记录的变量
            if(session.getAttribute("user")!=null){
                  User user =  (User)session.getAttribute("user");
                  SessionListener.sessionMap.remove(user.getId());
            }
            
            //考生端
            if(session.getAttribute("sid")!=null){
                String sid =  session.getAttribute("sid").toString();
                SessionListener.sessionMap.remove(sid);
            }
        }
    }
    
   /**
    * @author 
    * @params uid 要强行退出的用户的ID
    * @return
    * @description 强行把已经在线的用户的session注销
    */
    public static void forceLogoutUser(String uid) {
        // 删除单一登录中记录的变量
        if (SessionListener.sessionMap.get(uid) != null) {
        	HttpSession session = (HttpSession) SessionListener.sessionMap.get(uid);
        	SessionListener.sessionMap.remove(uid);
        	session.invalidate();
        }
    }
}
