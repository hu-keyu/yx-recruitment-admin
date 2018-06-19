/**
 * 29 Aug 2016
 */
package org.jypj.dev.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Menu;
import org.jypj.dev.entity.User;
import org.jypj.dev.intercept.SessionListener;
import org.jypj.dev.service.UserService;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.MD5Utils;
import org.jypj.dev.util.StringUtil;
import org.jypj.dev.vo.Node;
import org.jypj.dev.vo.TreeBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ChenYu
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Resource
	private UserService userService;

	/**
	 * 跳转到登录页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "toLogin")
	public String toLogin(HttpSession session,HttpServletRequest request,Model m) {
		if(request.getSession().getAttribute("user")==null)
		{
			return "login.vm";
		}else
		{
			m.addAttribute("toUrl", FtpUploadUtil.getOutUrl());
			return "/index/index.vm";
		}
		
		
	}

	/**
	 * 检查用户名密码 并设置session中
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("checkUser")
	@ResponseBody
	public Map<String, Object> checkUser(String loginname, String password, HttpServletRequest request) {
		HttpSession session=request.getSession();
		Map<String, Object> map = new HashMap<>();
		int code = 0;
		if (StringUtil.isNotEmpty(loginname)) {
			List<User> users = userService.selectAllByUser(new User(loginname, ""));
			if (users.size() == 0) {
				code = -1;
			} else {
				User user = users.get(0);
				String password2 = user.getPassword();
				String md5Encrypt = MD5Utils.md5Encrypt(password);
				if (!password2.equals(md5Encrypt)) {
					code = -2;
				} else {
					//菜单
				    Map<String,List<Menu>> menuMap=userService.selectMenuByUserId(user.getId());
				    List<Node> nodeList=new ArrayList<Node>();
				    for (Map.Entry<String,List<Menu>> entry : menuMap.entrySet()) {  
	                  List<Menu> menuList=entry.getValue();
	                  for(Menu m:menuList)
	                  {
	                	  Node d=new Node();
	                	  d.setId(m.getId());
	                	  d.setParentId(m.getParentId());
	                	  d.setName(m.getMenuName());
	                	  d.setUrl(request.getContextPath()+m.getUrl());
	                	  d.setIndex(m.getSortOrder().intValue());
	                	  if("002".equals(user.getUserType()))
	                	  {
	                		  if(!d.getName().contains("首页"))
	                		  {
	                			  nodeList.add(d);
	                		  }
	                	  }else if("000".equals(user.getUserType()))
	                	  {
	                		  nodeList.add(d);
	                	  }
	                  }
			        }  
				    TreeBuilder treeBuilder=new TreeBuilder();
				    nodeList=treeBuilder.buildListToTree(nodeList);
				    String fristUlr="";
				    for(int i=0;i<nodeList.size();i++)
				    {
				    	 Node parentNode=nodeList.get(i);
				    	 parentNode.setUrl(handleMenuURL(parentNode.getUrl(),i,0));
				    	 parentNode.setIndex(i);
				    	 List<Node> sonList=parentNode.getMenuSecond();
				    	 for(int j=0;j<sonList.size();j++)
				    	 {
				    		 Node sonNode=sonList.get(j);
				    		 sonNode.setUrl(handleMenuURL(sonNode.getUrl(),i,j));
				    		 sonNode.setIndex(j);
				    		 if(j==0)
				    		 {
				    			 fristUlr=sonNode.getUrl();
				    		 }
				    	 }
				    	 if("002".equals(user.getUserType()))
				    	 {
				    		 parentNode.setUrl(fristUlr);
				    	 }
				    }
				    JSONObject objectJson=new JSONObject();
				    request.getSession().setAttribute("objectJson", objectJson.toJSON(nodeList).toString());
					ServletContext application = request.getSession().getServletContext();
					List<Dictionary> all =DictionaryCache.getDicCache();
					Map<String, List<Dictionary>> dataMap = new HashMap<String, List<Dictionary>>();
					if(	application.getAttribute("codeMap")==null)
					{
					if (all != null && all.size() > 0) {
						DictionaryCache.setDicCache(all);
						// 安学科进行分组
						Set<Dictionary> set = new HashSet<Dictionary>();
						List<Dictionary> tempList = null;
						for (Dictionary s : all) {
							if (set.add(s)) {
								tempList = new ArrayList<Dictionary>();
								tempList.add(s);
								dataMap.put(s.getCode(), tempList);
							} else {
								if (dataMap.containsKey(s.getCode())) {
									tempList = dataMap.get(s.getCode());
									tempList.add(s);
									dataMap.put(s.getCode(), tempList);
								}
							}
						}
					}
					application.setAttribute("codeMap", dataMap);
					}
					if(user.getSkin()==null)
					{
						user.setSkin("blue");//默认蓝色主题
					}
					request.getSession().setAttribute("user", user);
					if ( null != SessionListener.sessionMap.get(user.getId())&&SessionListener.sessionMap.get(user.getId())!=session) {   
		                //将第一次登录用户的信息从map中移除 且第一次登录的用户session销毁
		        		SessionListener.forceLogoutUser(user.getId());
		                //本次登录用户添加到map中 
		                SessionListener.sessionMap.put(user.getId(), session);
		            } else{      
		                //以用户id为key键存入map中，以判断下一次登录的人
		                SessionListener.sessionMap.put(user.getId(), session);
		            }
				}
			}
		} else {
			code = -2;
		}
		map.put("code", code);
		return map;
	}

	@RequestMapping(value = "outTime")
	public String outTime() {
		return "sessionout.jsp";
	}
	
    @RequestMapping(value = "studentOutTime")
    public String studentOutTime() {
        return "sessionStudentout.jsp";
    }

	@RequestMapping(value = "loginout")
	public String loginout(HttpServletRequest request, HttpServletResponse response) {

		try {
			User user=(User)request.getSession().getAttribute("user");
			if(user!=null)
			{
				SessionListener.forceLogoutUser(user.getId());
				request.getSession().invalidate();
			}
			response.sendRedirect(request.getContextPath() + "/login/toLogin");
			return "";
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "loginoutStudent")
	public String loginoutStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
		    if (request.getSession().getAttribute("sid") == null) {
				response.sendRedirect(FtpUploadUtil.getStudentOutUrl());
		        return null;
		    }
		    String sid = request.getSession().getAttribute("sid").toString();
		    SessionListener.forceLogoutUser(sid);
			response.sendRedirect(FtpUploadUtil.getStudentOutUrl());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 处理菜单url
	 * @param menuStr
	 * @param i
	 * @param k
	 * @return
	 */
	private String handleMenuURL(String menuStr,int i,int k)
	{
		String paramUrl=menuStr.substring(menuStr.indexOf("?")+1,menuStr.length());
		String url=menuStr.substring(0,menuStr.indexOf("?"))+"?";
		String tempStr[]=paramUrl.split("&");
		for(int j=0;j<tempStr.length;j++)
		{
			String s=tempStr[j];
			if(s!="")
			{
				String value=s.substring(s.indexOf("=")+1,s.length());
				String key=s.substring(0,s.indexOf("="));
				if("firstIndex".equals(key))
				{
					value=i+"";
				}
				if("secondIndex".equals(key))
				{
					value=k+"";
				}
				if(j==0)
				{
					url=url+key+"="+value;	
				}else
				{
					url=url+"&"+key+"="+value;
				}
				
			}
		}
		return url;
	}
	
}
