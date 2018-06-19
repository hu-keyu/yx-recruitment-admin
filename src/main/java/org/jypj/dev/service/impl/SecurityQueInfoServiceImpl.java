package org.jypj.dev.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.jypj.dev.entity.SecurityQueInfo;
import org.jypj.dev.dao.SecurityQueInfoDao;
import org.jypj.dev.service.SecurityQueInfoService;
import org.jypj.dev.util.Page;

/**
* SecurityQueInfo业务接口实现层
* 密保问题信息
* @author
*
*/

@Service("securityQueInfoService")
public class SecurityQueInfoServiceImpl implements SecurityQueInfoService {
	
    @Resource 
    private SecurityQueInfoDao securityQueInfoDao;
    
    /**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param securityQueInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveSecurityQueInfoByField(SecurityQueInfo securityQueInfo){
	
		return securityQueInfoDao.saveSecurityQueInfoByField(securityQueInfo);
	}
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param securityQueInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveSecurityQueInfo(SecurityQueInfo securityQueInfo){
	
		return securityQueInfoDao.saveSecurityQueInfo(securityQueInfo);
	}
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteSecurityQueInfoById(String id){
    
    	return securityQueInfoDao.deleteSecurityQueInfoById(id);
    }
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteSecurityQueInfoByObject(SecurityQueInfo securityQueInfo){
    
    	return securityQueInfoDao.deleteSecurityQueInfoByObject(securityQueInfo);
    }
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param securityQueInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSecurityQueInfoByField(SecurityQueInfo securityQueInfo){
    
    	return securityQueInfoDao.updateSecurityQueInfoByField(securityQueInfo);
    }
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param securityQueInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateSecurityQueInfo(SecurityQueInfo securityQueInfo){
    
    	return securityQueInfoDao.updateSecurityQueInfo(securityQueInfo);
    }
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return SecurityQueInfo 
	 */	
    public SecurityQueInfo selectSecurityQueInfoById(String id){
    
    	return securityQueInfoDao.selectSecurityQueInfoById(id);
    }
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<SecurityQueInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map){
     	 List<SecurityQueInfo> securityQueInfos =securityQueInfoDao.selectOnePageByMap(page,map);
	     	if(securityQueInfos!=null&&securityQueInfos.size()>0){
	     		page.setResult(securityQueInfos);
	     	}else{
	     		page.setResult(new ArrayList<SecurityQueInfo>());
	     	}
	     	return page;
     }
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param securityQueInfo  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageBySecurityQueInfo(Page page,SecurityQueInfo securityQueInfo){
 		 List<SecurityQueInfo> securityQueInfos =securityQueInfoDao.selectOnePageBySecurityQueInfo(page,securityQueInfo);
	     	if(securityQueInfos!=null&&securityQueInfos.size()>0){
	     		page.setResult(securityQueInfos);
	     	}else{
	     		page.setResult(new ArrayList<SecurityQueInfo>());
	     	}
	     	return page;
     }
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<SecurityQueInfo>
	 */	
     public List<SecurityQueInfo> selectAllByMap(Map<String,Object> map){
     	return securityQueInfoDao.selectAllByMap(map);
     }
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<SecurityQueInfo>
	 */	
     public List<SecurityQueInfo> selectAllBySecurityQueInfo(SecurityQueInfo securityQueInfo){
     
    	 return securityQueInfoDao.selectAllBySecurityQueInfo(securityQueInfo);
     }
		
		/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  SecurityQueInfo
	 */	
     public SecurityQueInfo selectObjectByMap(Map<String,Object> map){
     
    	 return securityQueInfoDao.selectObjectByMap(map);
     }
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  SecurityQueInfo
	 */	
     public SecurityQueInfo selectObjectBySecurityQueInfo(SecurityQueInfo securityQueInfo){
     
     	return securityQueInfoDao.selectObjectBySecurityQueInfo(securityQueInfo);
     }

    @Override
    public void updateMultiSecret(Map<String, Object> map) {
        List<SecurityQueInfo> secretList = (List)map.get("secretList");
        securityQueInfoDao.updateMultiSecret(secretList);
    }
}