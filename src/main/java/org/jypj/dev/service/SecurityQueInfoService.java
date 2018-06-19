package org.jypj.dev.service;

import org.jypj.dev.entity.SecurityQueInfo;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* SecurityQueInfo业务接口层
* 密保问题信息
* @author
*
*/

public interface SecurityQueInfoService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param securityQueInfo
	 * @return 
	 */	
	public int saveSecurityQueInfoByField(SecurityQueInfo securityQueInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param securityQueInfo 
	 * @return 
	 */	
	public int saveSecurityQueInfo(SecurityQueInfo securityQueInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteSecurityQueInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteSecurityQueInfoByObject(SecurityQueInfo securityQueInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param securityQueInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSecurityQueInfoByField(SecurityQueInfo securityQueInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param securityQueInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSecurityQueInfo(SecurityQueInfo securityQueInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return SecurityQueInfo 
	 */	
    public SecurityQueInfo selectSecurityQueInfoById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<SecurityQueInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageBySecurityQueInfo(Page page,SecurityQueInfo securityQueInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<SecurityQueInfo>
	 */	
     public List<SecurityQueInfo> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<SecurityQueInfo>
	 */	
     public List<SecurityQueInfo> selectAllBySecurityQueInfo(SecurityQueInfo securityQueInfo);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  SecurityQueInfo
	 */	
     public SecurityQueInfo selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  SecurityQueInfo
	 */	
     public SecurityQueInfo selectObjectBySecurityQueInfo(SecurityQueInfo securityQueInfo);
     
     /**
      * 批量修改密保
      * @param map
      */
     public void updateMultiSecret(Map<String,Object> map);

}
