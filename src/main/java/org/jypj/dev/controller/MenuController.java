package org.jypj.dev.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jypj.dev.cache.DictionaryCache;
import org.jypj.dev.code.Result;
import org.jypj.dev.entity.Dictionary;
import org.jypj.dev.entity.Menu;

import javax.annotation.Resource;
import org.jypj.dev.service.MenuService;
import org.jypj.dev.service.RoleService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Menu控制器 菜单表
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/dg/menu")
public class MenuController {

	@Resource
	private MenuService menuService;

	@Resource
	private RoleService roleService;

	/**
	 * list Page
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/sys/menu/list.vm";
	}

	/**
	 * add Page
	 */
	@RequestMapping("/addPage")
	public String addPage(Model model) {
		List<Dictionary> xllxDics = DictionaryCache.getDictionaryByCode("xllx");//学历
        model.addAttribute("xklbDics", xllxDics);
		return "/sys/menu/menu_add.vm";
	}

	/**
	 * edit Page
	 */
	@RequestMapping("/editPage")
	public String editPage(Model model, String id) {
		Menu menu = menuService.selectMenuById(id);
		Menu parent = menuService.selectMenuById(menu.getParentId());
		if(null != parent){
			menu.setParentName(parent.getMenuName());
		}
		model.addAttribute("menu", menu);
		return "/sys/menu/menu_edit.vm";
	}
	
	/**
	 * select Parent Menu Page
	 */
	@RequestMapping("/parentmenu")
	public String parentMenu(Model model, String id) {
		Menu menu = menuService.selectMenuById(id);
		model.addAttribute("Parent", menu);
		return "/sys/menu/menu_parent.vm";
	}
	
	/**
	 * select Parent Menu Page
	 */
	@RequestMapping("/menus")
	@ResponseBody
	public Map<String, Object> allMenus(Model model, String id) {
		Menu menu = menuService.selectMenuById(id);
		List<Menu> list = menuService.selectAllByMenu(new Menu());
		model.addAttribute("allMenu", list);
		model.addAttribute("Parent", menu);
		Menu treeRoot = new Menu();
		treeRoot.setId("MENUTREEROOT");
		treeRoot.setMenuName("东莞教育局招聘");
		treeRoot.setSortOrder(new BigDecimal("0"));
		list.add(treeRoot);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allMenu", list);
		return map;
	}
	

	/**
	 * 列表分页 page List
	 * @param pageSize
	 * @param currentPage
	 * @return List<Theme>
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Map<String, Object> dataGrid(int pageSize, int currentPage, String menuName, String url) {
		Page page = new Page(pageSize, currentPage);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(menuName)) {
			condition.put("menuName", menuName);
		}
		if (StringUtils.isNotEmpty(url)) {
			condition.put("url", url);
		}
		page = menuService.selectOnePageByMap(page, condition);
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
	public Result add(Menu menu) {
		Result r = new Result();
		try {
			menuService.saveMenu(menu);
			r.setSuccess(true);
			if (StringUtils.isNotEmpty(menu.getId())) {
				r.setMsg("修改成功！");
			} else {
				r.setMsg("添加成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setSuccess(false);
			if (StringUtils.isNotEmpty(menu.getId())) {
				r.setMsg("修改失败！");
			} else {
				r.setMsg("添加成功！");
			}
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
	public Result update(Menu menu) {
		Result r = new Result();
		try {
			menuService.updateMenu(menu);
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
			menuService.deleteMenuById(id);
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
			menuService.deleteAllByIds(ids);
			r.setSuccess(true);
			r.setMsg("删除成功！");
		} catch (Exception e) {
			r.setSuccess(false);
			r.setMsg("删除失败！");
		}
		return r;
	}
}