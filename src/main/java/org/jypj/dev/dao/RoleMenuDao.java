package org.jypj.dev.dao;

import org.jypj.dev.entity.RoleMenu;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* RoleMenudao数据接口层
* 角色表
* @author
*
*/


public interface RoleMenuDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param roleMenu
	 * @return 保存后的对象包括ID
	 */	
	public int saveRoleMenuByField(RoleMenu roleMenu);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param roleMenu 
	 * @return 保存后的对象包括ID
	 */	
	public int saveRoleMenu(RoleMenu roleMenu);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteRoleMenuById(String id);
    
   	/**
	 * 根据对象删除
	 * @param roleMenu
	 * @return 
	 */	
    public int deleteRoleMenuByObject(RoleMenu roleMenu);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param roleMenu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRoleMenuByField(RoleMenu roleMenu);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param roleMenu 
	 * @return 保存后的对象包括ID
	 */	
    public int updateRoleMenu(RoleMenu roleMenu);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return RoleMenu 
	 */	
    public RoleMenu selectRoleMenuById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<RoleMenu>
	 */	
     public List<RoleMenu> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param roleMenu 查询对象
	 * @return List<RoleMenu>
	 */	
     public List<RoleMenu> selectOnePageByRoleMenu(Page page,RoleMenu roleMenu);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<RoleMenu>
	 */	
     public List<RoleMenu> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<RoleMenu>
	 */	
     public List<RoleMenu> selectAllByRoleMenu(RoleMenu roleMenu);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  RoleMenu
	 */	
     public RoleMenu selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  RoleMenu
	 */	
     public RoleMenu selectObjectByRoleMenu(RoleMenu roleMenu);
     
     /**
      * 根据角色ID删除多个对象
      * @param id
      * @return
      */
	public int deleteRoleMenuByRoleId(String roleId);
	
	
	/**
	 * 批量保存角色菜单
	 * @param list
	 * @return
	 */
	public int saveRoleMenuForBatch(List<RoleMenu> list);
	
	
}
