package org.jypj.dev.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jypj.dev.entity.User;
import org.jypj.dev.intercept.SessionListener;
import org.jypj.dev.service.ThemeService;
import org.jypj.dev.service.UserService;
import org.jypj.dev.util.FtpUploadUtil;
import org.jypj.dev.util.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 * @author QiCai
 *
 */
@Controller
@RequestMapping("/dg/index")
public class IndexController {
	@Resource
	private UserService userService;
	@Resource
	private ThemeService themeService ;
	@RequestMapping(value="main")
	public String main(Model model,HttpServletRequest request){
		model.addAttribute("toUrl", FtpUploadUtil.getOutUrl());
		User user=(User)request.getSession().getAttribute("user");
		if("002".equals(user.getUserType()))
		{
			 return "forward:/dg/planApply/planApplyList?&firstIndex=0&secondIndex=0&topMenu=menu";
		}else
		{
			return "/index/index.vm";
		}
		
	}
	
	@RequestMapping(value="skin")
	public String skin(){
		return "/common/skin.vm";
	}

	@RequestMapping(value="demo")
	public String demo(){
		return "/dialog-demo.vm";
	}
	
	@RequestMapping(value="index1")
	public String index1(){
		return "/index/index1.vm";
	}
	
	@RequestMapping(value="table")
	public String table(){
		return "/index/table.vm";
	}
	
	@RequestMapping(value="table2")
	public String table2(){
		return "/index/table2.vm";
	}
	@RequestMapping(value="toUpwd")
	public String toUpwd(){
		return "upwd.vm";
	}
	
	@RequestMapping(value="updpassword")
	@ResponseBody
	public Map<String,Object> updpassword(String newPwd,String oldPwd,HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		User user=(User)request.getSession().getAttribute("user");
		String md5Encrypt = MD5Utils.md5Encrypt(oldPwd);
		if(!md5Encrypt.equals(user.getPassword()))
		{
			resultMap.put("msg", "error");
		}else
		{   
			user.setPassword(MD5Utils.md5Encrypt(newPwd));
			user.setPasswordReal(newPwd);
			userService.updateUserByField(user);
			SessionListener.forceLogoutUser(user.getId());
			request.getSession().invalidate();
		}
		return  resultMap;
	}
	
	
	@RequestMapping(value="changeSikn")
	@ResponseBody
	public Map<String,Object> changeSikn(String skin,HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		User user=(User)request.getSession().getAttribute("user");
		user.setSkin(request.getParameter("skin"));
		userService.updateUser(user);
		resultMap.put("msg", "success");
		return  resultMap;
	}
	
	@RequestMapping(value = "printLoginName")
	public String printLoginName(HttpServletRequest request,HttpServletResponse response)
	{
		
		try {
			String loginname="";
			if(request.getSession().getAttribute("user")!=null)
			{
			   User user=(User)request.getSession().getAttribute("user");
			   List<User> users = userService.selectAllByUser(new User(loginname, ""));
			   response.getWriter().print(user.getLoginName());
			   return null;
			   
			}else
			{
				 response.getWriter().print("未登陆!");
				 return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//重置考生密码页面
	@RequestMapping("/resetPasswdList")
    public String restPassedList(Model model){
    	List<String> years =  themeService.selectYears() ;
        model.addAttribute("years",years) ;
    	return "/student/resetPasswdList.vm" ;
    }
}
