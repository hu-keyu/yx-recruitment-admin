package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.EaxmLabsRecord;
import org.jypj.dev.util.Page;

/**
* EaxmLabsRecorddao数据接口层
* 试室分配考生记录表
* @author
*
*/


public interface EaxmLabsRecordDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param eaxmLabsRecord
	 * @return 保存后的对象包括ID
	 */	
	public int saveEaxmLabsRecordByField(EaxmLabsRecord eaxmLabsRecord);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param eaxmLabsRecord 
	 * @return 保存后的对象包括ID
	 */	
	public int saveEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteEaxmLabsRecordById(String id);
    
   	/**
	 * 根据对象删除
	 * @param eaxmLabsRecord
	 * @return 
	 */	
    public int deleteEaxmLabsRecordByObject(EaxmLabsRecord eaxmLabsRecord);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param eaxmLabsRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsRecordByField(EaxmLabsRecord eaxmLabsRecord);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param eaxmLabsRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return EaxmLabsRecord 
	 */	
    public EaxmLabsRecord selectEaxmLabsRecordById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<EaxmLabsRecord>
	 */	
     public List<EaxmLabsRecord> selectOnePageByMap(Page page, Map<String, Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param eaxmLabsRecord 查询对象
	 * @return List<EaxmLabsRecord>
	 */	
     public List<EaxmLabsRecord> selectOnePageByEaxmLabsRecord(Page page, EaxmLabsRecord eaxmLabsRecord);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsRecord>
	 */	
     public List<EaxmLabsRecord> selectAllByMap(Map<String, Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<EaxmLabsRecord>
	 */	
     public List<EaxmLabsRecord> selectAllByEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsRecord
	 */	
     public EaxmLabsRecord selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  EaxmLabsRecord
	 */	
     public EaxmLabsRecord selectObjectByEaxmLabsRecord(EaxmLabsRecord eaxmLabsRecord);
     
     /**
      * 批量存储
      * @param list
      */
     public void addBatch(List<EaxmLabsRecord> list);

     /**
      * 试室调整更新
      * @param map
      */
     public void updateEaxmLabsRecordAdjust(Map<String,Object> map);
     
     /**
      * 批量更新
      * @param list
      */
     public void updateBatchEaxmLabsRecordList(List<EaxmLabsRecord> list);

}
