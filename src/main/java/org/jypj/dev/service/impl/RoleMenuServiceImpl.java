package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.RoleMenu;
import org.jypj.dev.dao.RoleMenuDao;
import org.jypj.dev.service.RoleMenuService;
import org.jypj.dev.util.Page;

/**
* RoleMenu业务接口实现层
* 角色表
* @author
*
*/

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {
	
    @Resource 
    private RoleMenuDao roleMenuDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param roleMenu
	 * @return 保存后的对象包括ID
	 */	
	public int saveRoleMenuByField(RoleMenu roleMenu){
	
		return roleMenuDao.saveRoleMenuByField(roleMenu);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param roleMenu 
	 * @return 保存后的对象包括ID
	 */	
	public int saveRoleMenu(RoleMenu roleMenu){
	
		return roleMenuDao.saveRoleMenu(roleMenu);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteRoleMenuById(String id){
    
    	return roleMenuDao.deleteRoleMenuById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteRoleMenuByObject(RoleMenu roleMenu){
    
    	return roleMenuDao.deleteRoleMenuByObject(roleMenu);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param roleMenu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRoleMenuByField(RoleMenu roleMenu){
    
    	return roleMenuDao.updateRoleMenuByField(roleMenu);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param roleMenu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRoleMenu(RoleMenu roleMenu){
    
    	return roleMenuDao.updateRoleMenu(roleMenu);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return RoleMenu 
	 */	
    public RoleMenu selectRoleMenuById(String id){
    
    	return roleMenuDao.selectRoleMenuById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<RoleMenu>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<RoleMenu> roleMenus =roleMenuDao.selectOnePageByMap(page,map);
	     	if(roleMenus!=null&&roleMenus.size()>0){
	     		page.setResult(roleMenus);
	     	}else{
	     		page.setResult(new ArrayList<RoleMenu>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param roleMenu  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByRoleMenu(Page page,RoleMenu roleMenu){
 		 List<RoleMenu> roleMenus =roleMenuDao.selectOnePageByRoleMenu(page,roleMenu);
	     	if(roleMenus!=null&&roleMenus.size()>0){
	     		page.setResult(roleMenus);
	     	}else{
	     		page.setResult(new ArrayList<RoleMenu>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<RoleMenu>
	 */	
     public List<RoleMenu> selectAllByMap(Map<String,Object> map){
     	return roleMenuDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<RoleMenu>
	 */	
     public List<RoleMenu> selectAllByRoleMenu(RoleMenu roleMenu){
     
    	 return roleMenuDao.selectAllByRoleMenu(roleMenu);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  RoleMenu
	 */	
     public RoleMenu selectObjectByMap(Map<String,Object> map){
     
    	 return roleMenuDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  RoleMenu
	 */	
     public RoleMenu selectObjectByRoleMenu(RoleMenu roleMenu){
     
     	return roleMenuDao.selectObjectByRoleMenu(roleMenu);
     }
     
     /**
      * 根据角色ID删除多个对象
      * @param id
      * @return
      */
	public int deleteRoleMenuByRoleId(String roleId){
		return roleMenuDao.deleteRoleMenuByRoleId(roleId);
	}
	
	/**
	 * 批量保存角色菜单
	 * @param list
	 * @return
	 */
	public int saveRoleMenuForBatch(List<RoleMenu> list){
		return roleMenuDao.saveRoleMenuForBatch(list);
	}
	
}