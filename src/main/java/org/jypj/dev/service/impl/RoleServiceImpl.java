package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.Role;
import org.jypj.dev.entity.RoleMenu;
import org.jypj.dev.dao.RoleDao;
import org.jypj.dev.service.RoleMenuService;
import org.jypj.dev.service.RoleService;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;

/**
* Role业务接口实现层
* 用户表
* @author
*
*/

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
    @Resource 
    private RoleDao roleDao;
    
    @Resource 
    private RoleMenuService roleMenuService;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param role
	 * @return 保存后的对象包括ID
	 */	
	public int saveRoleByField(Role role){
	
		return roleDao.saveRoleByField(role);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param role 
	 * @return 保存后的对象包括ID
	 */	
	public int saveRole(Role role){
		if(StringUtil.isEmpty(role.getId())){
			role.setId(UUID.randomUUID().toString().replace("-", ""));
			return roleDao.saveRole(role);
		}else{
			return roleDao.updateRole(role);
		}
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteRoleById(String id){
    
    	return roleDao.deleteRoleById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteRoleByObject(Role role){
    
    	return roleDao.deleteRoleByObject(role);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param role 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRoleByField(Role role){
    
    	return roleDao.updateRoleByField(role);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param role 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRole(Role role){
    
    	return roleDao.updateRole(role);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Role 
	 */	
    public Role selectRoleById(String id){
    
    	return roleDao.selectRoleById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Role>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<Role> roles =roleDao.selectOnePageByMap(page,map);
	     	if(roles!=null&&roles.size()>0){
	     		page.setResult(roles);
	     	}else{
	     		page.setResult(new ArrayList<Role>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param role  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByRole(Page page,Role role){
 		 List<Role> roles =roleDao.selectOnePageByRole(page,role);
	     	if(roles!=null&&roles.size()>0){
	     		page.setResult(roles);
	     	}else{
	     		page.setResult(new ArrayList<Role>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Role>
	 */	
     public List<Role> selectAllByMap(Map<String,Object> map){
     	return roleDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Role>
	 */	
     public List<Role> selectAllByRole(Role role){
     
    	 return roleDao.selectAllByRole(role);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Role
	 */	
     public Role selectObjectByMap(Map<String,Object> map){
     
    	 return roleDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Role
	 */	
     public Role selectObjectByRole(Role role){
     
     	return roleDao.selectObjectByRole(role);
     }
     
     /**
  	 * 根据ID删除
  	 * @param id 主键ID数组
  	 * @return 删除记录数
  	 */	
 	public int deleteRoleByIds(String[] ids) {
 		return roleDao.deleteRoleByIds(ids);
 	}
 	
 	/* (non-Javadoc)
 	 * @see org.jypj.dev.service.RoleService#saveRoleMenus(org.jypj.dev.entity.Role)
 	 */
 	@Override
	public int saveRoleMenus(Role role) {
		int x=roleMenuService.deleteRoleMenuByRoleId(role.getId());
		int y=0;
		if(null != role.getMenuIds()){
			List<RoleMenu> list = new ArrayList<RoleMenu>();
			for(String menuId:role.getMenuIds()){
				RoleMenu roleMenu=new RoleMenu();
				roleMenu.setId(UUID.randomUUID().toString().replace("-", ""));
				roleMenu.setRoleId(role.getId());
				roleMenu.setMenuId(menuId);
				roleMenu.setCreateUser(role.getModifyUser());
				roleMenu.setModifyUser(role.getModifyUser());
				list.add(roleMenu);
			}
			y = roleMenuService.saveRoleMenuForBatch(list);
		}
		return x+y;
	}
 	
 	/**
	 * 角色编号重复检查
	 * @param role
	 * @return
	 */
	public List<Role> selectRepeatRoleByCode(Role role) {
		return roleDao.selectRepeatRoleByCode(role);
	}
	
}