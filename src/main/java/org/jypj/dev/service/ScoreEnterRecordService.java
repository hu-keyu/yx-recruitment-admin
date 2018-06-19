package org.jypj.dev.service;

import org.jypj.dev.entity.ScoreEnterRecord;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterRecord业务接口层
* 入围情况记录表
* @author
*
*/

public interface ScoreEnterRecordService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterRecord
	 * @return 
	 */	
	public int saveScoreEnterRecordByField(ScoreEnterRecord scoreEnterRecord);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterRecord 
	 * @return 
	 */	
	public int saveScoreEnterRecord(ScoreEnterRecord scoreEnterRecord);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterRecordById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterRecordByObject(ScoreEnterRecord scoreEnterRecord);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterRecordByField(ScoreEnterRecord scoreEnterRecord);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterRecord 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterRecord(ScoreEnterRecord scoreEnterRecord);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterRecord 
	 */	
    public ScoreEnterRecord selectScoreEnterRecordById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterRecord>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterRecord(Page page,ScoreEnterRecord scoreEnterRecord);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterRecord>
	 */	
     public List<ScoreEnterRecord> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterRecord>
	 */	
     public List<ScoreEnterRecord> selectAllByScoreEnterRecord(ScoreEnterRecord scoreEnterRecord);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterRecord
	 */	
     public ScoreEnterRecord selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterRecord
	 */	
     public ScoreEnterRecord selectObjectByScoreEnterRecord(ScoreEnterRecord scoreEnterRecord);

}
