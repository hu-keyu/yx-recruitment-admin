package org.jypj.dev.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Menu;
import org.jypj.dev.entity.Role;
import javax.annotation.Resource;

import org.jypj.dev.service.MenuService;
import org.jypj.dev.service.RoleService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Role控制器
 * 用户表
 * @author
 *
 */
@Controller
@RequestMapping("/dg/role")
public class RoleController {
	
    @Resource 
    private RoleService roleService;
    
    @Resource
    private MenuService menuService;
    
    /**
	 * list Page
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/sys/role/list.vm";
	}

	/**
	 * add Page
	 */
	@RequestMapping("/addPage")
	public String addPage(Model model) {
		return "/sys/role/role_add.vm";
	}

	/**
	 * edit Page
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, String id) {
		Role role = roleService.selectRoleById(id);
		model.addAttribute("role", role);
		return "/sys/role/role_edit.vm";
	}

	/**
	 * add role menu Page
	 */
	@RequestMapping("/addRoleMenu")
	public String addRoleMenu(Model model, String id) {
		Role role = roleService.selectRoleById(id);
		model.addAttribute("role", role);
		return "/sys/role/role_menu.vm";
	}
	
	/**
	 * 列表分页 page List
	 * @param pageSize
	 * @param currentPage
	 * @return List<Theme>
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Map<String, Object> dataGrid(int pageSize, int currentPage, String roleName, String roleCode) {
		Page page = new Page(pageSize, currentPage);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(roleName)) {
			condition.put("roleName", roleName);
		}
		if (StringUtils.isNotEmpty(roleCode)) {
			condition.put("roleCode", roleCode);
		}
		page = roleService.selectOnePageByMap(page, condition);
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
	public Result add(Role role) {
		Result r = new Result();
		try {
			List<Role> tmp = roleService.selectRepeatRoleByCode(role);
			if(null != tmp && tmp.size()>0){
				r.setSuccess(false);
				r.setMsg("角色编号不能重复,保存失败！");
			}else{
				roleService.saveRole(role);
				r.setSuccess(true);
				r.setMsg("保存成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setSuccess(false);
			r.setMsg("保存失败！");
		}
		return r;
	}

	/**
	 * 角色编号重复检查
	 * @return Result
	 */
	@RequestMapping("/isRepeat")
	@ResponseBody
	public Result isRepeat(String roleCode, String id) {
		Result r = new Result();
		Role role = new Role();
		try {
			if(null != id){
				role.setId(id);
			}
			role.setRoleCode(roleCode);
			r.setSuccess(true);
			List<Role> tmp = roleService.selectRepeatRoleByCode(role);
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
	public Result update(Role role) {
		Result r = new Result();
		try {
			roleService.updateRole(role);
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
			roleService.deleteRoleById(id);
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
			roleService.deleteRoleByIds(ids.split(","));
			r.setSuccess(true);
			r.setMsg("删除成功！");
		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg("删除失败！");
		}
		return r;
	}
    
    /**
     * ================================================================================================================
     */
   
	@RequestMapping("/allMenus")
	@ResponseBody
	public Map<String, Object> allMenus(Model model,String roleId, String roleName) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("roleId", StringUtils.isEmpty(roleId) ? "null" : roleId);
		List<Menu> ownMenus = menuService.selectAllByMap(condition);
		List<Menu> list = menuService.selectAllByMenu(new Menu());
		Menu treeRoot = new Menu();
		treeRoot.setId("MENUTREEROOT");
		treeRoot.setMenuName("东莞教育局招聘");
		treeRoot.setSortOrder(new BigDecimal("0"));
		list.add(treeRoot);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allMenu", list);
		map.put("ownMenu", ownMenus);
		return map;
	}
	
	@RequestMapping("/roleOwnMenu")
	@ResponseBody
	public Map<String, Object> roleOwnMenu(String roleId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(roleId)) {
			condition.put("roleId", roleId);
		}
		List<Menu> list = menuService.selectAllByMap(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allMenu", list);
		return map;
	}
	
	/**
	 * 保存角色菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "saveRoleMenus")
	@ResponseBody
	public int saveRoleMenus(String id,@RequestParam(value="menuIds[]",required=false)String[] menuIds) {
		Role role=new Role();
		role.setId(id);
		role.setMenuIds(menuIds);
		System.out.println(role.getId());
		int code = roleService.saveRoleMenus(role);
		return code;
	}
}