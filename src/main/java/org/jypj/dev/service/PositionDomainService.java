package org.jypj.dev.service;

import org.jypj.dev.entity.PositionDomain;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* PositionDomain业务接口层
* 学校岗位专业要求表
* @author
*
*/

public interface PositionDomainService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param positionDomain
	 * @return 
	 */	
	public int savePositionDomainByField(PositionDomain positionDomain);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param positionDomain 
	 * @return 
	 */	
	public int savePositionDomain(PositionDomain positionDomain);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePositionDomainById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePositionDomainByObject(PositionDomain positionDomain);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param positionDomain 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePositionDomainByField(PositionDomain positionDomain);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param positionDomain 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePositionDomain(PositionDomain positionDomain);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return PositionDomain 
	 */	
    public PositionDomain selectPositionDomainById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<PositionDomain>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPositionDomain(Page page,PositionDomain positionDomain);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PositionDomain>
	 */	
     public List<PositionDomain> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PositionDomain>
	 */	
     public List<PositionDomain> selectAllByPositionDomain(PositionDomain positionDomain);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PositionDomain
	 */	
     public PositionDomain selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PositionDomain
	 */	
     public PositionDomain selectObjectByPositionDomain(PositionDomain positionDomain);
     
     /**
      * 批量保存岗位对应的专业限制
      * @param positionDomains
      */
     public void saveList(List<PositionDomain> positionDomains);

	/**
	 * 通过专业名称查找，是否已经使用
	 * @param specialty
	 * @return
	 */
	int selectCountBySpecialty(String specialty) ;

}
