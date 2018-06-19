package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ScoreEnterInformation;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreEnterInformation业务接口层
* 
* @author
*
*/

public interface ScoreEnterInformationService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterInformation
	 * @return 
	 */	
	public int saveScoreEnterInformationByField(ScoreEnterInformation scoreEnterInformation);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterInformation 
	 * @return 
	 */	
	public int saveScoreEnterInformation(ScoreEnterInformation scoreEnterInformation);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterInformationById(String id);
    
    /** 按条件查询全部的(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEnterPhysical>
 	 */	
    public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
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
     * 批量插入进入单位面试名单
     * @param list
     */
    public String addBatchScore(Map<String, Object> map,Page page,User user,JSONObject jsonMap) throws Exception ;
    
    /**按条件查询全部的入围名单(导出专用)
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
     public Page selectOnePageByMap(Page page, Map<String, Object> map);
     
     /**
   	 * 分页查询 包含条件
   	 * @param page 分页对象
   	 * @param map  查询条件  
   	 * @return  Page
   	 */	
     public Page selectPageByMap(Page page, Map<String, Object> map);
     
     /**
	 * @param map  查询条件  
	 * @return  List<ScoreEntersOutVo>
	 */	
      public List<ScoreEntersOutVo> selectByMap(Map<String, Object> map);
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterInformation(Page page, ScoreEnterInformation scoreEnterInformation);
    
     /**
  	 * 分页查询 包含条件查询入围名单
  	 * @param page 分页对象
  	 * @param map  查询条件  
  	 * @return  List<ScoreEnterInformation>
  	 */	
     public Page selectEnterPageByMap(Page page, Map<String, Object> map);
     
     /**
	 * 分页查询 包含条件查询入围名单
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public Page selectGradePageByMap(Page page, Map<String, Object> map);
     
     /**
 	 * 分页查询 包含条件查询入围名单
 	 * @param page 分页对象
 	 * @param map  查询条件  
 	 * @return  List<ScoreEnterInformation>
 	 */	
      public Page searchsGradePageByMap(Page page, Map<String, Object> map);
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterInformation>
	 */	
     public List<ScoreEnterInformation> selectAllByMap(Map<String, Object> map);
     
      
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

}
