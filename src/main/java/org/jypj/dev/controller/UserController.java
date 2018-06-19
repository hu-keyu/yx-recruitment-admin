package org.jypj.dev.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Role;
import org.jypj.dev.entity.User;
import org.jypj.dev.entity.UserRole;
import org.jypj.dev.service.RoleService;
import org.jypj.dev.service.UserRoleService;
import org.jypj.dev.service.UserService;
import org.jypj.dev.util.MD5Utils;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.RandomPasswd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dg/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleService userRoleService;
	/**
	 * list Page
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/sys/user/list.vm";
	}

	/**
	 * add Page
	 */
	@RequestMapping("/addPage")
	public String addPage(Model model) {
		return "/sys/user/user_add.vm";
	}

	/**
	 * edit Page
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, String id) {
		User user = userService.selectUserById(id);
		model.addAttribute("user", user);
		return "/sys/user/user_edit.vm";
	}
	/**
	 * user Role Page
	 */
	@RequestMapping("/userRole")
	public String userRolePage(Model model, String id) {
		User user = userService.selectUserById(id);
		List<UserRole> userRoleList = userRoleService.setUserRoleByUserId(user.getId());
		List<Role> roleList = roleService.selectAllByMap(new HashMap<String,Object>());
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		model.addAttribute("userRole", userRoleList.size() > 0 ? userRoleList.get(0) : null);
		return "/sys/user/user_role.vm";
	}
	

	/**
	 * 列表分页 page List
	 * @param pageSize
	 * @param currentPage
	 * @return List<Theme>
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Map<String, Object> dataGrid(int pageSize, int currentPage, String loginName, String userName) {
		Page page = new Page(pageSize, currentPage);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(userName)) {
			condition.put("userName", userName);
		}
		if (StringUtils.isNotEmpty(loginName)) {
			condition.put("loginName", loginName);
		}
		page = userService.selectOnePageByMap(page, condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalRows());
		map.put("rows", page.getResult());
		map.put("currentPage", page.getCurrentPage());
		return map;
	}

	/**
	 * 添加
	 * @return Result
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(User user) {
		Result r = new Result();
		try {
			List<User> tmp = userService.selectRepeatUserByLoginName(user);
			if(null != tmp && tmp.size()>0){
				r.setSuccess(false);
				r.setMsg("登录名不能重复,保存失败！");
			}else{
				userService.saveUserByField(user);
				r.setSuccess(true);
				r.setMsg("保存成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setSuccess(false);
			r.setMsg("保存失败");
		}
		return r;
	}

	/**
	 * 登录名重复验证
	 * @return Result
	 */
	@RequestMapping("/isRepeat")
	@ResponseBody
	public Result isRepeat(String loginName, String id) {
		Result r = new Result();
		User user = new User();
		try {
			r.setSuccess(true);
			if(null != id){
				user.setId(id);
			}
			user.setLoginName(loginName);
			List<User> tmp = userService.selectRepeatUserByLoginName(user);
			if(null != tmp && tmp.size()>0){
				r.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setSuccess(false);
		}
		return r;
	}
	
	/**
	 * 修改
	 * @param postset
	 * @return Result
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(User user) {
		Result r = new Result();
		try {
			userService.updateUser(user);
			r.setSuccess(true);
			r.setMsg("修改成功！");
		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg("修改失败！");
		}
		return r;
	}

	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String id) {
		Result r = new Result();
		try {
			userService.deleteUserById(id);
			r.setSuccess(true);
			r.setMsg("删除成功！");
		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg("删除失败！");
		}
		return r;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return Result
	 */
	@RequestMapping("/deleteBatch")
	@ResponseBody
	public Result deleteBatch(String ids) {
		Result r = new Result();
		try {
			userService.deleteUserByIds(ids.split(","));
			r.setSuccess(true);
			r.setMsg("删除成功！");
		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg("删除失败！");
		}
		return r;
	}
	
	/**
	 * 添加角色
	 * @return Result
	 */
	@RequestMapping("/saveUserRole")
	@ResponseBody
	public Result saveUserRole(User user) {
		Result r = new Result();
		try {
			if(user.getRoleIds()!=null){
				Date currentDate=new Date();
	    		List<UserRole> userRoles=new ArrayList<>();
	    		for(String roleId:user.getRoleIds()){
	    			UserRole userRole=new UserRole();
	    			userRole.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    			userRole.setUserId(user.getId());
	    			userRole.setRoleId(roleId);
					userRole.setCreateDate(currentDate);
					userRole.setModifyDate(currentDate);
	    			userRoles.add(userRole);
	    		}
	    		int d = userRoleService.deleteUserRoleByUserId(user.getId());
	    		int x = userRoleService.saveUserRoles(userRoles);
	    		System.out.println("save User Role" + (d+x));
			}
			r.setSuccess(true);
			r.setMsg("角色添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			r.setSuccess(false);
			r.setMsg("角色添加失败");
		}
		return r;
	}
	
	//重置密码
	@RequestMapping("/resetPasswd")
	@ResponseBody
	public Result restPasswd(String id){
		Result r = new Result() ;
		String newPasswd = RandomPasswd.getStringRandom(8) ;
		try {
			userService.resetPasswd(id,newPasswd,MD5Utils.md5Encrypt(newPasswd)) ;
			r.setSuccess(true);
			r.setMsg(newPasswd);
			return r ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setMsg("重置密码失败，请重试！");
			r.setSuccess(false);
			return r ;
		}
	}

	
}