package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.Postset;
import org.jypj.dev.entity.ScoreEnterTrial;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterTrialdao数据接口层
* 统一试讲入围表
* @author
*
*/


public interface ScoreEnterTrialDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterTrial
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterTrialByField(ScoreEnterTrial scoreEnterTrial);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterTrial 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
	
	 /**
     * 批量插入
     * @param list
     */
    public Integer saveTrialsList(@Param(value="list") List<ScoreEnterTrial> list);
    
    /**
     * 查询进入统一试讲的入围名单（调整和删除名单）
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEnterTrial> selectTrialEnterPage(Page page,Map<String,Object> map);
    
    /**
     * 查询综合成绩
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEntersOutVo> selectSynthesizePageByMap(Page page,Map<String,Object> map);
    
    /**
     *  批量统一试讲入围名单相关信息
     * @param list
     */
    public void updateTrialEnterList(@Param(value="list") List<ScoreEnterTrial> list);
    
    /**
     * 查询进入统一笔试的入围名单（发布名单）
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEntersOutVo> selectAllTrial(Map<String,Object> map);
    
    /**
  	 * 按条件查询全部的(导出专用)
  	 * @param map  查询条件  
  	 * @return  List<ScoreEnterPhysical>
  	 */	
     public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterTrialById(String id);
    
    /**
	 * 根据项目ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteTrialByProjectId(@Param(value="projectId")String projectId);
    
    /**按条件查询全部的入围名单(导出专用)
	 * @param map  查询条件  
	 * @return  List<ScoreEntersOutVo>
	 */	
     public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
    
   	/**
	 * 根据对象删除
	 * @param scoreEnterTrial
	 * @return 
	 */	
    public int deleteScoreEnterTrialByObject(ScoreEnterTrial scoreEnterTrial);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterTrialByField(ScoreEnterTrial scoreEnterTrial);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterTrial 
	 */	
    public ScoreEnterTrial selectScoreEnterTrialById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterTrial 查询对象
	 * @return List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectOnePageByScoreEnterTrial(Page page,ScoreEnterTrial scoreEnterTrial);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectAllByMap(Map<String,Object> map);
     
     /**
      * 根据准考证号和姓名还有项目ID查询考生是否存在
     * @param scoreEntersOutVo
     * @return
     */
     public ScoreEntersOutVo selectByStu(ScoreEntersOutVo scoreEntersOutVo);
     
     /**
  	 * 按条件查询总数(试室是否发布完成)
  	 * @param map  查询条件  
  	 * @return  Integer
  	 */	
      public Integer selectLabsCount(Map<String,Object> map);
      
      /**
	 * 查询总人数
	 * @param map  查询条件  
	 * @return  Integer
	 */	
      public Integer selectEntersCount(Map<String,Object> map);
      
      /**
       * 查询所有名单
      * @param scoreEntersOutVo
      * @return
      */
      public List<ScoreEntersOutVo> selectByStuCondition(ScoreEntersOutVo scoreEntersOutVo);
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectAllByScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterTrial
	 */	
     public ScoreEnterTrial selectObjectByMap(Map<String,Object> map);
     
     /**
 	 * 按条件查询总数
 	 * @param map  查询条件  
 	 * @return  Integer
 	 */	
     public Integer selectCountByMap(Map<String,Object> map);
     
     /**
      * 批量保存试讲入围名单表
      * @param map
      */
     public void batchSaveScoreEnterTrial(Map<String , Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterTrial
	 */	
     public ScoreEnterTrial selectObjectByScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
     
     /**
      * 批量更新
      * @param scoreEnterTrial
      */
     public void updateBatchScoreEnterTrialList(List<ScoreEnterTrial> scoreEnterTrial);
     
     /**
      * 根据对象删除试讲入围名单
      * @param scoreEnterTrial
      */
     public int deleteScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);

     
     /**
      * 导出成绩中的导出试讲成绩
      * @param page
      * @param map
      * @return
      */
	public List<ScoreEntersOutVo> selectPersonLecture(Page page, Map<String, Object> map);
	
	/**
     * 导出成绩中的导出试讲成绩无分页
     * @param page
     * @param map
     * @return
     */
	public List<ScoreEntersOutVo> selectPersonLecture(Map<String, Object> map);
	
	/**
	 * 根据岗位和项目判断入围试讲名单是否发布
	 * @param setl
	 * @return
	 */
	public Integer selectCountOfEnterTrial(ScoreEnterTrial setl);

}
