package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jypj.dev.dao.UserRoleDao;
import org.jypj.dev.entity.UserRole;
import org.jypj.dev.service.UserRoleService;
import org.jypj.dev.util.Page;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	
    @Resource 
    private UserRoleDao userRoleDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param userRole
	 * @return 保存后的对象包括ID
	 */	
	public int saveUserRoleByField(UserRole userRole){
	
		return userRoleDao.saveUserRoleByField(userRole);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param userRole 
	 * @return 保存后的对象包括ID
	 */	
	public int saveUserRole(UserRole userRole){
	
		return userRoleDao.saveUserRole(userRole);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteUserRoleById(String id){
    
    	return userRoleDao.deleteUserRoleById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteUserRoleByObject(UserRole userRole){
    
    	return userRoleDao.deleteUserRoleByObject(userRole);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param userRole 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUserRoleByField(UserRole userRole){
    
    	return userRoleDao.updateUserRoleByField(userRole);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param userRole 
	 * @return 保存后的对象包括ID
	 */	
    public int updateUserRole(UserRole userRole){
    
    	return userRoleDao.updateUserRole(userRole);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return UserRole 
	 */	
    public UserRole selectUserRoleById(String id){
    
    	return userRoleDao.selectUserRoleById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<UserRole>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<UserRole> userRoles =userRoleDao.selectOnePageByMap(page,map);
	     	if(userRoles!=null&&userRoles.size()>0){
	     		page.setResult(userRoles);
	     	}else{
	     		page.setResult(new ArrayList<UserRole>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param userRole  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByUserRole(Page page,UserRole userRole){
 		 List<UserRole> userRoles =userRoleDao.selectOnePageByUserRole(page,userRole);
	     	if(userRoles!=null&&userRoles.size()>0){
	     		page.setResult(userRoles);
	     	}else{
	     		page.setResult(new ArrayList<UserRole>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<UserRole>
	 */	
     public List<UserRole> selectAllByMap(Map<String,Object> map){
     	return userRoleDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<UserRole>
	 */	
     public List<UserRole> selectAllByUserRole(UserRole userRole){
     
    	 return userRoleDao.selectAllByUserRole(userRole);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  UserRole
	 */	
     public UserRole selectObjectByMap(Map<String,Object> map){
     
    	 return userRoleDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  UserRole
	 */	
     public UserRole selectObjectByUserRole(UserRole userRole){
     
     	return userRoleDao.selectObjectByUserRole(userRole);
     }
     
     
	public List<UserRole> setUserRoleByUserId(String userId){
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 map.put("userId", userId);
    	 return userRoleDao.selectAllByMap(map);
     }
	
	public int saveUserRoles(List<UserRole> userRoles){
		return userRoleDao.saveUserRoles(userRoles);
	}
	
	/**
	 * 根据用户Id删除角色
	 * @param userId 用户Id
	 * @return 
	 */	
    public int deleteUserRoleByUserId(String userId){
    	return userRoleDao.deleteUserRoleByUserId(userId);
    }
}