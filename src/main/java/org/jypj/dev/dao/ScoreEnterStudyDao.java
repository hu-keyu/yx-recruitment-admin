package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreEnterStudy;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterStudydao数据接口层
* 考察入围表
* @author
*
*/


public interface ScoreEnterStudyDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterStudy
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterStudyByField(ScoreEnterStudy scoreEnterStudy);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterStudy 
	 * @return 保存后的对象包括ID
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
	 * @param scoreEnterStudy
	 * @return 
	 */	
    public int deleteScoreEnterStudyByObject(ScoreEnterStudy scoreEnterStudy);
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
     * 批量插入
     * @param list
     */
    public Integer saveStudysList(@Param(value="list") List<ScoreEnterStudy> list);
    
    /**
	 * 根据项目ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteStudyByProjectId(@Param(value="projectId")String projectId);
    
    /**
	 * 查询进入考察的入围名单（调整和删除名单）
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectStudyEnterPageByMap(Page page,Map<String,Object> map);
     
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
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterStudy 查询对象
	 * @return List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectOnePageByScoreEnterStudy(Page page,ScoreEnterStudy scoreEnterStudy);
    
     /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectAllByMap(Map<String,Object> map);
     
     /**
      *  批量更新体检入围名单相关信息
     * @param list
     */
     public void updateStudyEnterList(@Param(value="list") List<ScoreEnterStudy> list);
     
     /**
      * 查询进入考察的入围名单（发布名单）
      * @param page
      * @param map
      * @return
      */
     public List<ScoreEntersOutVo> selectAllStudy(Map<String,Object> map); 
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterStudy>
	 */	
     public List<ScoreEnterStudy> selectAllByScoreEnterStudy(ScoreEnterStudy scoreEnterStudy);
     
     /**
      * 根据准考证号和姓名还有项目ID查询考生是否存在
     * @param scoreEntersOutVo
     * @return
     */
     public ScoreEntersOutVo selectByStu(ScoreEntersOutVo scoreEntersOutVo);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterStudy
	 */	
     public ScoreEnterStudy selectObjectByMap(Map<String,Object> map);
     
     /**
      * 查询所有名单
     * @param scoreEntersOutVo
     * @return
     */
     public List<ScoreEntersOutVo> selectByStuCondition(ScoreEntersOutVo scoreEntersOutVo);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterStudy
	 */	
     public ScoreEnterStudy selectObjectByScoreEnterStudy(ScoreEnterStudy scoreEnterStudy);
}
