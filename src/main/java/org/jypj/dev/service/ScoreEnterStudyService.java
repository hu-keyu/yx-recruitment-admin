package org.jypj.dev.service;

import org.jypj.dev.entity.ScoreEnterStudy;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterStudy业务接口层
* 考察入围表
* @author
*
*/

public interface ScoreEnterStudyService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterStudy
	 * @return 
	 */	
	public int saveScoreEnterStudyByField(ScoreEnterStudy scoreEnterStudy);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterStudy 
	 * @return 
	 */	
	public int saveScoreEnterStudy(ScoreEnterStudy scoreEnterStudy);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterStudyById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteScoreEnterStudyByObject(ScoreEnterStudy scoreEnterStudy);
    
    /**
	 * 查询进入考察的入围名单
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public Page selectStudyEnterPageByMap(Page page,Map<String,Object> map);
     
     /**
      * 调整入围名单
     * @param chk
     * @param projectId
     * @param positionid
     * @param user
     * @param jsonMap
     * @throws Exception
     */
    public void enterStudylist(String flag,String chks,String projectId,String positionid, User user, JSONObject jsonMap) throws Exception;
    
    /**
     * 批量插入进入入围的名单
     * @param list
     */
    public String addBatchStudy(Map<String, Object> condition,Page page,User user);
    
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterStudyByField(ScoreEnterStudy scoreEnterStudy);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterStudy(ScoreEnterStudy scoreEnterStudy);
    
    /**
  	 * 按条件查询全部的(导出专用)
  	 * @param map  查询条件  
  	 * @return  List<ScoreEnterPhysical>
  	 */	
     public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
     
     /**按条件查询全部的入围名单(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
     public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterStudy 
	 */	
    public ScoreEnterStudy selectScoreEnterStudyById(String id);
    
    /**
     * 查询进入考察的入围名单（发布名单）
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEntersOutVo> selectAllStudy(Map<String,Object> map); 
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterStudy(Page page,ScoreEnterStudy scoreEnterStudy);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectAllByScoreEnterStudy(ScoreEnterStudy scoreEnterStudy);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterStudy
	 */	
     public ScoreEnterStudy selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterStudy
	 */	
     public ScoreEnterStudy selectObjectByScoreEnterStudy(ScoreEnterStudy scoreEnterStudy);

}
