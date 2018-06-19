package org.jypj.dev.dao;

import org.jypj.dev.entity.AuditReason;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* AuditReasondao数据接口层
* 审核原因表
* @author
*
*/


public interface AuditReasonDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param auditReason
	 * @return 保存后的对象包括ID
	 */	
	public int saveAuditReasonByField(AuditReason auditReason);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param auditReason 
	 * @return 保存后的对象包括ID
	 */	
	public int saveAuditReason(AuditReason auditReason);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteAuditReasonById(String id);
    
   	/**
	 * 根据对象删除
	 * @param auditReason
	 * @return 
	 */	
    public int deleteAuditReasonByObject(AuditReason auditReason);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param auditReason 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAuditReasonByField(AuditReason auditReason);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param auditReason 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAuditReason(AuditReason auditReason);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return AuditReason 
	 */	
    public AuditReason selectAuditReasonById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<AuditReason>
	 */	
     public List<AuditReason> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param auditReason 查询对象
	 * @return List<AuditReason>
	 */	
     public List<AuditReason> selectOnePageByAuditReason(Page page,AuditReason auditReason);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<AuditReason>
	 */	
     public List<AuditReason> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<AuditReason>
	 */	
     public List<AuditReason> selectAllByAuditReason(AuditReason auditReason);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  AuditReason
	 */	
     public AuditReason selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  AuditReason
	 */	
     public AuditReason selectObjectByAuditReason(AuditReason auditReason);

	/**
	 * 查询所有的注意事项
	 * @return
     */
	List<AuditReason> selectAllAttention();
}
