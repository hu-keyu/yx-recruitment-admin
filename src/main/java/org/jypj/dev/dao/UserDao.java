package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

public interface UserDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param user
	 * @return 保存后的对象包括ID
	 */	
	public int saveUserByField(User user);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param user 
	 * @return 保存后的对象包括ID
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
	 * @param user
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
     public List<User> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param user 查询对象
	 * @return List<User>
	 */	
     public List<User> selectOnePageByUser(Page page,User user);
    
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
	public int deleteUserByIds(@Param("ids")String[] ids);

	/**
 	 * 根据登录名查询用户
 	 * @param String loginname
 	 * @return User
 	 */
	public User selectUserByLoginName(String loginname);
	
	public List<User> selectRepeatUserByLoginName(User user);

	/**
	 * 重置密码
	 * @param id
	 * @param md5Encrypt
	 * @return
	 */
	public void resetPasswd(Map<String,String> map);
}
