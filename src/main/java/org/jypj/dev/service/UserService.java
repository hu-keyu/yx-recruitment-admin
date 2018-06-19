package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Menu;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

public interface UserService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param user
	 * @return 
	 */	
	public int saveUserByField(User user);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param user 
	 * @return 
	 */	
	public int saveUser(User user);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteUserById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteUserByObject(User user);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param user 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUserByField(User user);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param user 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUser(User user);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return User 
	 */	
    public User selectUserById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<User>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByUser(Page page,User user);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<User>
	 */	
     public List<User> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<User>
	 */	
     public List<User> selectAllByUser(User user);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  User
	 */	
     public User selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  User
	 */	
     public User selectObjectByUser(User user);

     /**
 	 * 根据ID批量删除
 	 * @param ids 主键ID数组
 	 * @return 删除记录数
 	 */	
	public int deleteUserByIds(String[] ids);
	
	/**
 	 * 根据登录名查询用户
 	 * @param String loginname
 	 * @return User
 	 */
	public User selectUserByLoginName(String loginname) ;
	
	/**
	 * 根据用户Id查询所有角色及对应的菜单
	 * @param userId
	 * @return
	 */
	public Map<String, List<Menu>> selectMenuByUserId(String userId);
	
	/**
	 * 根据用户Id查询所有角色及对应的菜单
	 * @param userId
	 * @return
	 */
	public Map<String, List<Menu>> selectMenuByLoginName(String loginName);
	
	/**
 	 * 根据登录名查询用户
 	 * @param String loginname
 	 * @return User
 	 */
	public List<User> selectRepeatUserByLoginName(User user) ;

	
	/**
	 * 重置密码
	 * @param id
	 * @param md5Encrypt
	 */
	public void resetPasswd(String id,String newpasswd, String md5Encrypt);
}
