package org.jypj.dev.service;

import org.jypj.dev.entity.Role;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* Role业务接口层
* 用户表
* @author
*
*/

public interface RoleService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param role
	 * @return 
	 */	
	public int saveRoleByField(Role role);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param role 
	 * @return 
	 */	
	public int saveRole(Role role);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteRoleById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteRoleByObject(Role role);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param role 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRoleByField(Role role);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param role 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRole(Role role);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Role 
	 */	
    public Role selectRoleById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Role>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByRole(Page page,Role role);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Role>
	 */	
     public List<Role> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Role>
	 */	
     public List<Role> selectAllByRole(Role role);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Role
	 */	
     public Role selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Role
	 */	
     public Role selectObjectByRole(Role role);

     
     /**
  	 * 根据ID删除
  	 * @param ids 主键ID数组
  	 * @return 删除记录数
  	 */	
 	public int deleteRoleByIds(String[] ids);
 
 	/**
	 * 保存角色菜单
	 * @param role 含有角色id和菜单id数组
	 * @return 影响记录数
	 */
	public int saveRoleMenus(Role role);
	
	/**
	 * 角色编号重复检查
	 * @param role
	 * @return
	 */
	public List<Role> selectRepeatRoleByCode(Role role);
}
