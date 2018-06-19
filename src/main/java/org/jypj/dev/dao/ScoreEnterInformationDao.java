package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreEnterInformation;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

/**
* ScoreEnterInformationdao数据接口层
* 
* @author
*
*/


public interface ScoreEnterInformationDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterInformation
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterInformationByField(ScoreEnterInformation scoreEnterInformation);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterInformation 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterInformation(ScoreEnterInformation scoreEnterInformation);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterInformationById(String id);
    
    
    /**按条件查询全部的(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
    public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
    
   	/**
	 * 根据对象删除
	 * @param scoreEnterInformation
	 * @return 
	 */	
    public int deleteScoreEnterInformationByObject(ScoreEnterInformation scoreEnterInformation);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterInformation 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterInformationByField(ScoreEnterInformation scoreEnterInformation);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterInformation 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterInformation(ScoreEnterInformation scoreEnterInformation);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterInformation 
	 */	
    public ScoreEnterInformation selectScoreEnterInformationById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectOnePageByMap(Page page, Map<String, Object> map);
     
     /**
  	 * 分页查询 包含条件
  	 * @param page 分页对象
  	 * @param map  查询条件  
  	 * @return  List<ScoreEntersOutVo>
  	 */	
     public List<ScoreEntersOutVo> selectPageByMap(Page page, Map<String, Object> map);
     
     /**
   	 * @param map  查询条件  
   	 * @return  List<ScoreEntersOutVo>
   	 */	
     public List<ScoreEntersOutVo> selectPageByMap(Map<String, Object> map);
     
     /**按条件查询全部发布前的入围名单(导出专用)
  	 * @param map  查询条件  
  	 * @return  List<ScoreEntersOutVo>
  	 */	
     public List<ScoreEntersOutVo> listExportByMap(Map<String,Object> map);
     
     /**按条件查询全部发布后的入围名单(导出专用)
   	 * @param map  查询条件  
   	 * @return  List<ScoreEntersOutVo>
   	 */	
      public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
     
     /**
 	 * 分页查询 包含条件查询入围名单
 	 * @param page 分页对象
 	 * @param map  查询条件  
 	 * @return  List<ScoreEnterInformation>
 	 */	
     public List<ScoreEnterInformation> selectEnterPageByMap(Page page, Map<String, Object> map);
      
      /**
   	 * 分页查询 包含条件查询入围名单
   	 * @param page 分页对象
   	 * @param map  查询条件  
   	 * @return  List<ScoreEnterInformation>
   	 */	
     public List<ScoreEnterInformation> selectGradePageByMap(Page page, Map<String, Object> map);
      
     /**
 	 * 分页查询 包含条件查询入围名单
 	 * @param page 分页对象
 	 * @param map  查询条件  
 	 * @return  List<ScoreEnterInformation>
 	 */	
     public List<ScoreEnterInformation> searchsGradePageByMap(Page page, Map<String, Object> map);
    
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterInformation 查询对象
	 * @return List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectOnePageByScoreEnterInformation(Page page, ScoreEnterInformation scoreEnterInformation);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectAllByMap(Map<String, Object> map);
     
     /**
      * 批量插入
      * @param list
      */
     public Integer addBatchScore(@Param(value="list") List<ScoreEnterInformation> list);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectAllByScoreEnterInformation(ScoreEnterInformation scoreEnterInformation);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterInformation
	 */	
     public ScoreEnterInformation selectObjectByMap(Map<String, Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterInformation
	 */	
     public ScoreEnterInformation selectObjectByScoreEnterInformation(ScoreEnterInformation scoreEnterInformation);
     
     /**
      * 
      * @param map
      * @return
      */
     public ScoreEnterInformation selectValidateInfo(Map<String, Object> map);
}
