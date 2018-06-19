package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.jypj.dev.dao.UserDao;
import org.jypj.dev.dao.UserRoleDao;
import org.jypj.dev.entity.Menu;
import org.jypj.dev.entity.User;
import org.jypj.dev.entity.UserRole;
import org.jypj.dev.service.MenuService;
import org.jypj.dev.service.RoleService;
import org.jypj.dev.service.UserService;
import org.jypj.dev.util.MD5Utils;
import org.jypj.dev.util.Page;
import org.jypj.dev.util.StringUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
    @Resource 
    private UserDao userDao;
    @Resource 
    private UserRoleDao userRoleDao;
    
    @Resource
    RoleService roleService;
    
    @Resource
    MenuService menuService;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param user
	 * @return 保存后的对象包括ID
	 */	
	public int saveUserByField(User user){
		int x = 0;
		if(StringUtil.isEmpty(user.getId())){
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			user.setPasswordReal("123456");
			user.setPassword(MD5Utils.md5Encrypt(user.getPasswordReal()));
			x = userDao.saveUser(user);
		}else{
			x=userDao.updateUser(user);
		}
		int y=0;
    	if(user.getRoleIds()!=null){
			Date currentDate=new Date();
    		List<UserRole> userRoles=new ArrayList<>();
    		for(String roleId:user.getRoleIds()){
    			UserRole userRole=new UserRole();
    			userRole.setId(UUID.randomUUID().toString().replace("-", ""));
    			userRole.setUserId(user.getId());
    			userRole.setRoleId(roleId);
				userRole.setCreateDate(currentDate);
				userRole.setModifyDate(currentDate);
    			userRoles.add(userRole);
    		}
    		y=userRoleDao.saveUserRoles(userRoles);
    	}
    	return x+y;
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param user 
	 * @return 保存后的对象包括ID
	 */	
	public int saveUser(User user){
	
		return userDao.saveUser(user);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteUserById(String id){
    
    	return userDao.deleteUserById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteUserByObject(User user){
    
    	return userDao.deleteUserByObject(user);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param user 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUserByField(User user){
    	int x=userDao.updateUserByField(user);
    	int y=0;
    	if(user.getRoleIds()!=null){
			Date currentDate=new Date();
    		List<UserRole> userRoles=new ArrayList<>();
    		for(String roleId:user.getRoleIds()){
    			UserRole userRole=new UserRole();
    			userRole.setId(UUID.randomUUID().toString().replace("-", ""));
    			userRole.setUserId(user.getId());
    			userRole.setRoleId(roleId);
				userRole.setCreateDate(currentDate);
				userRole.setModifyDate(currentDate);
    			userRoles.add(userRole);
    		}
    		y=userRoleDao.deleteUserRolesByUserId(user.getId())+userRoleDao.saveUserRoles(userRoles);
    	}
    	return x+y;
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param user 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUser(User user){
    
    	return userDao.updateUser(user);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return User 
	 */	
    public User selectUserById(String id){
    
    	return userDao.selectUserById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<User>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<User> users =userDao.selectOnePageByMap(page,map);
	     	if(users!=null&&users.size()>0){
	     		page.setResult(users);
	     	}else{
	     		page.setResult(new ArrayList<User>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param user  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByUser(Page page,User user){
 		 List<User> users =userDao.selectOnePageByUser(page,user);
	     	if(users!=null&&users.size()>0){
	     		page.setResult(users);
	     	}else{
	     		page.setResult(new ArrayList<User>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<User>
	 */	
     public List<User> selectAllByMap(Map<String,Object> map){
     	return userDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<User>
	 */	
     public List<User> selectAllByUser(User user){
     
    	 return userDao.selectAllByUser(user);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  User
	 */	
     public User selectObjectByMap(Map<String,Object> map){
     
    	 return userDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  User
	 */	
     public User selectObjectByUser(User user){
     
     	return userDao.selectObjectByUser(user);
     }

     /**
 	 * 根据ID批量删除
 	 * @param ids 主键ID数组
 	 * @return 删除记录数
 	 */	
	@Override
	public int deleteUserByIds(String[] ids) {
		
    	return userDao.deleteUserByIds(ids);
	}

	/**
 	 * 根据登录名查询用户
 	 * @param String loginname
 	 * @return User
 	 */
	@Override
	public User selectUserByLoginName(String loginname) {
		// TODO Auto-generated method stub
		return userDao.selectUserByLoginName(loginname);
	}
	
	/**
	 * 根据用户Id查询所有角色及对应的菜单
	 * @param userId
	 * @return
	 */
	public Map<String, List<Menu>> selectMenuByUserId(String userId){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userId", userId);
		Map<String, List<Menu>> menuMap = menuService.selectMenuGroupbyRole(condition);
		return menuMap;
	}
	
	/**
	 * 根据用户Id查询所有角色及对应的菜单
	 * @param userId
	 * @return
	 */
	public Map<String, List<Menu>> selectMenuByLoginName(String loginName){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("loginName", loginName);
		Map<String, List<Menu>> menuMap = menuService.selectMenuGroupbyRole(condition);
		return menuMap;
	}
	
	/**
 	 * 根据登录名查询用户
 	 * @param String loginname
 	 * @return User
 	 */
	public List<User> selectRepeatUserByLoginName(User user){
		return userDao.selectRepeatUserByLoginName(user);
	}

	@Override
	public void resetPasswd(String id,String newPasswd, String md5Encrypt) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String,String>() ;
		map.put("id", id) ;
		map.put("newPasswd", newPasswd) ;
		map.put("encrypt", md5Encrypt) ;
		userDao.resetPasswd(map) ;
	}
	
}