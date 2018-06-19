package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.Menu;
import org.jypj.dev.dao.MenuDao;
import org.jypj.dev.service.MenuService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;

/**
* Menu业务接口实现层
* 菜单表
* @author
*
*/

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
    @Resource 
    private MenuDao menuDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param menu
	 * @return 保存后的对象包括ID
	 */	
	public int saveMenuByField(Menu menu){
		if(StringUtil.isEmpty(menu.getId())){
			menu.setId(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
		}
		return menuDao.saveMenuByField(menu);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param menu 
	 * @return 保存后的对象包括ID
	 */	
	public int saveMenu(Menu menu){
		if(StringUtil.isEmpty(menu.getId())){
			menu.setId(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
			return menuDao.saveMenu(menu);
		}else{
			return menuDao.updateMenu(menu);
		}
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteMenuById(String id){
    
    	return menuDao.deleteMenuById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteMenuByObject(Menu menu){
    
    	return menuDao.deleteMenuByObject(menu);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param menu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateMenuByField(Menu menu){
    
    	return menuDao.updateMenuByField(menu);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param menu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateMenu(Menu menu){
    
    	return menuDao.updateMenu(menu);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Menu 
	 */	
    public Menu selectMenuById(String id){
    
    	return menuDao.selectMenuById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Menu>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Menu> menus =menuDao.selectOnePageByMap(page,map);
	     	if(menus!=null&&menus.size()>0){
	     		page.setResult(menus);
	     	}else{
	     		page.setResult(new ArrayList<Menu>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param menu  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByMenu(Page page,Menu menu){
 		 List<Menu> menus =menuDao.selectOnePageByMenu(page,menu);
	     	if(menus!=null&&menus.size()>0){
	     		page.setResult(menus);
	     	}else{
	     		page.setResult(new ArrayList<Menu>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Menu>
	 */	
     public List<Menu> selectAllByMap(Map<String,Object> map){
     	return menuDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Menu>
	 */	
     public List<Menu> selectAllByMenu(Menu menu){
     
    	 return menuDao.selectAllByMenu(menu);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Menu
	 */	
     public Menu selectObjectByMap(Map<String,Object> map){
     
    	 return menuDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Menu
	 */	
     public Menu selectObjectByMenu(Menu menu){
     
     	return menuDao.selectObjectByMenu(menu);
     }
     
     /**
      * 批量删除
      */
 	public int deleteAllByIds(String ids) {
 		int code =0;
 		try {
 			if(StringUtil.isNotEmpty(ids)){
 				String[] idArr = ids.split(",");
 				for(String id:idArr){
 					menuDao.deleteMenuById(id);
 				}
 			}
 		} catch (Exception e) {
 			code=-1;
 			e.printStackTrace();
 		}
 		return code;
 	}
 	
 	/**
 	 *  查询菜单根据角色id进行分组
 	 */
 	public Map<String,List<Menu>> selectMenuGroupbyRole(Map<String,Object> map){
 		List<Menu> list = menuDao.selectMenuGroupbyRole(map);
 		Map<String,List<Menu>> rtn = new HashMap<String, List<Menu>>();
 		if(null != list){
 			List<Menu> tmp = null;
 			for(Menu m : list){
 				if(rtn.containsKey(m.getRoleId())){
 					tmp = rtn.get(m.getRoleId());
 					tmp.add(m);
 				}else{
 					tmp = new ArrayList<Menu>();
 					tmp.add(m);
 					rtn.put(m.getRoleId(), tmp);
 				}
 			}
 		}
 		return rtn;
 	}
}