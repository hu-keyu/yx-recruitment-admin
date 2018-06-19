package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.UserRole;
import org.jypj.dev.util.Page;

public interface UserRoleService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param userRole
	 * @return 
	 */	
	public int saveUserRoleByField(UserRole userRole);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param userRole 
	 * @return 
	 */	
	public int saveUserRole(UserRole userRole);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteUserRoleById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteUserRoleByObject(UserRole userRole);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param userRole 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUserRoleByField(UserRole userRole);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param userRole 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUserRole(UserRole userRole);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return UserRole 
	 */	
    public UserRole selectUserRoleById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<UserRole>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByUserRole(Page page,UserRole userRole);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<UserRole>
	 */	
     public List<UserRole> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<UserRole>
	 */	
     public List<UserRole> selectAllByUserRole(UserRole userRole);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  UserRole
	 */	
     public UserRole selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  UserRole
	 */	
     public UserRole selectObjectByUserRole(UserRole userRole);
     
     /**
      * 根据用户ID查询角色
     * @param userId
     * @return
     */
    public List<UserRole> setUserRoleByUserId(String userId);
    
    /**
     * 根据用户ID和角色id添加用户角色
     * @param userRoles
     * @return
     */
    public int saveUserRoles(List<UserRole> userRoles);
    

    /**
	 * 根据用户Id删除角色
	 * @param userId 用户Id
	 * @return 
	 */	
    public int deleteUserRoleByUserId(String userId);
}
