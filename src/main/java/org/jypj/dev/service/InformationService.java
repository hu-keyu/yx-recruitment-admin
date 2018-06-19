package org.jypj.dev.service;

import org.jypj.dev.entity.Information;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface InformationService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param information
	 * @return 
	 */	
	public int saveInformationByField(Information information);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param information 
	 * @param jsonMap 
	 * @param user 
	 * @return 
	 */	
	public int saveInformation(Information information, User user, JSONObject jsonMap);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteInformationById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteInformationByObject(Information information);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param information 
	 * @return 保存后的对象包括ID
	 */	
    public int updateInformationByField(Information information);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param information 
	 * @return 保存后的对象包括ID
	 */	
    public int updateInformation(Information information);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Information 
	 */	
    public Information selectInformationById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Information>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByInformation(Page page,Information information);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Information>
	 */	
     public List<Information> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Information>
	 */	
     public List<Information> selectAllByInformation(Information information);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Information
	 */	
     public Information selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Information
	 */	
     public Information selectObjectByInformation(Information information);
     
     /**
      * 修改资料面试资料信息
      * @param information
      * @param user
      * @param jsonMap
      */
	 public void updateInformationOper(Information information, User user, JSONObject jsonMap) throws Exception ;
	 
	 /**
	  * 删除资料面试信息
	  * @param chk
	  * @param jsonMap
	  */
	 public void deleteInformationOper(String chk, JSONObject jsonMap);
	 
	 /**
	  * 发布资料面试信息
	  * @param id
	  * @param jsonMap
	 * @param user 
	  */
     public void publishInformationOper(String id, JSONObject jsonMap, User user) throws Exception;

}
