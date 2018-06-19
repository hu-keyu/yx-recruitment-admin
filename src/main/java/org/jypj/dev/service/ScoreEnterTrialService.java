package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ScoreEnterTrial;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import com.alibaba.fastjson.JSONObject;

/**
* ScoreEnterTrial业务接口层
* 统一试讲入围表
* @author
*
*/

public interface ScoreEnterTrialService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterTrial
	 * @return 
	 */	
	public int saveScoreEnterTrialByField(ScoreEnterTrial scoreEnterTrial);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterTrial 
	 * @return 
	 */	
	public int saveScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterTrialById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
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
     * 查询进入统一试讲的入围名单
     * @param page
     * @param map
     * @return
     */
    public Page selectTrialEnterPage(Page page,Map<String,Object> map);
    
    /**
     * 调整入围名单
    * @param chk
    * @param projectId
    * @param positionid
    * @param user
    * @param jsonMap
    * @throws Exception
    */
    public void enterTriaList(String flag,String chks, String projectId, String positionid, User user, JSONObject jsonMap)
			throws Exception;
    
    /**
     * 批量插入进入统一试讲名单
     * @param list
     */
    public String addBatchTrials(Map<String, Object> condition,Page page,User user,String flag);
    
    /**
     * 查询综合成绩
     * @param page
     * @param map
     * @return
     */
    public Page selectSynthesizePageByMap(Page page,Map<String,Object> map);
    
    /**
     * 成绩统计中的导出试讲成绩
     * @param page
     * @param map
     * @return
     */
    public Page selectPersonLecture(Page page,Map<String,Object> map);
    
    /**
     * 成绩统计中的导出试讲成绩，无分页
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEntersOutVo> selectPersonLecture(Map<String,Object> map);
    
    /**
     * 查询进入统一笔试的入围名单（发布名单）
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEntersOutVo> selectAllTrial(Map<String,Object> map);
    
    /**按条件查询全部的(导出专用)
  	 * @param map  查询条件  
  	 * @return  List<ScoreEnterPhysical>
  	 */	
     public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
     
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
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterTrial 
	 */	
    public ScoreEnterTrial selectScoreEnterTrialById(String id);
    
    /**按条件查询全部的入围名单(导出专用)
	 * @param map  查询条件  
	 * @return  List<ScoreEntersOutVo>
	 */	
     public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
     
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterTrial(Page page,ScoreEnterTrial scoreEnterTrial);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterTrial>
	 */	
     public List<ScoreEnterTrial> selectAllByScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
     
     /**
  	 * 按条件查询总数
  	 * @param map  查询条件  
  	 * @return  Integer
  	 */	
     public Integer selectCountByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterTrial
	 */	
     public ScoreEnterTrial selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterTrial
	 */	
     public ScoreEnterTrial selectObjectByScoreEnterTrial(ScoreEnterTrial scoreEnterTrial);
     
     /**
      * 根据考生报考信息获取入围情况
      * @param sai
      * @return
      */
     public ScoreEnterTrial selectEnterObject(StudentApplyInfo sai);
     
     /**
      * 根据招聘主题，岗位判断入围试讲名单是否发布
      * @param setl
      * @return
      */
     public Integer selectCountOfEnterTrial(ScoreEnterTrial setl);

}
