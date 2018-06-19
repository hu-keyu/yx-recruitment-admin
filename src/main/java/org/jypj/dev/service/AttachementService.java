package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Attachement;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.util.Page;

/**
* Attachement业务接口层
* 上传附件信息表
* @author
*
*/

public interface AttachementService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param attachement
	 * @return 
	 */	
	public int saveAttachementByField(Attachement attachement);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param attachement 
	 * @return 
	 */	
	public int saveAttachement(Attachement attachement);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteAttachementById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteAttachementByObject(Attachement attachement);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param attachement 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAttachementByField(Attachement attachement);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param attachement 
	 * @return 保存后的对象包括ID
	 */	
    public int updateAttachement(Attachement attachement);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Attachement 
	 */	
    public Attachement selectAttachementById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Attachement>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByAttachement(Page page,Attachement attachement);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Attachement>
	 */	
     public List<Attachement> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Attachement>
	 */	
     public List<Attachement> selectAllByAttachement(Attachement attachement);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Attachement
	 */	
     public Attachement selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Attachement
	 */	
     public Attachement selectObjectByAttachement(Attachement attachement);
     
     /**
      * 更新或者保存附件资料
      * @param attId
      */
     public Attachement saveOrUpdateAttachement(Map<String,Object> map);
     
     /**
      * 根据申报信息保存附件
      * @param sai
      */
     public void saveByStudentApply(StudentApplyInfo sai);

}
