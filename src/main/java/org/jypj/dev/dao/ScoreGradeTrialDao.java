package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreGradeTrial;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScorePostNumVo;

import java.util.List;
import java.util.Map;

/**
* ScoreGradeTrialdao数据接口层
* 统一试讲成绩表
* @author
*
*/


public interface ScoreGradeTrialDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeTrial
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeTrialByField(ScoreGradeTrial scoreGradeTrial);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
	
	/**
     * 批量插入
     * @param list
     */
    public Integer saveTrialGradesList(@Param(value="list") List<ScoreGradeTrial> list);
    
    /**
     * 批量更新数据
     * @param studentApplyInfos
     */
    public void updateTrialList(@Param(value="list") List<ScoreGradeTrial> list);
    
    /**
     * 查询过了试讲比例和分数线的考生
     * @param map
     * @return
     */
    public List<ScoreGradeTrial> selectGradeTrialLine(Map<String,Object> map);
    
    /**
     * 查询进入试讲的总人数
     * @param map
     * @return
     */
    public Integer selectGradeTrialCount(Map<String,Object> map);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeTrialById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreGradeTrial
	 * @return 
	 */	
    public int deleteScoreGradeTrialByObject(ScoreGradeTrial scoreGradeTrial);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeTrialByField(ScoreGradeTrial scoreGradeTrial);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeTrial 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
    
    /**
     * 批量更新数据(导入)
     * @param studentApplyInfos
     */
    public int updateTrialBatchs(@Param(value="list") List<ScoreGradeTrial> list);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeTrial 
	 */	
    public ScoreGradeTrial selectScoreGradeTrialById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
  	 * 按条件查询总数
  	 * @param map  查询条件  
  	 * @return  Integer
  	 */	
      public Integer selectCountByMap(Map<String,Object> map);
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradeTrial 查询对象
	 * @return List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectOnePageByScoreGradeTrial(Page page,ScoreGradeTrial scoreGradeTrial);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectAllByMap(Map<String,Object> map);
     
     /**
 	 * 查询全部的(id集合)
 	 * @param map  查询条件  
 	 * @return  List<ScoreGradeTrial>
 	 */	
      public List<ScoreGradeTrial> selectTrialByMap(Map<String,Object> map);
     
      /**
	 * 查询综合成绩
	 * @param map  查询条件  
	 * @return  Integer
	 */	
      public List<ScoreGradeTrial> selectScoreSyns(Map<String,Object> map);
      
      /**
  	 * 查询综合成绩
  	 * @param map  查询条件  
  	 * @return  Integer
  	 */	
      public List<ScoreGradeTrial> selectScoreSpecial(Map<String,Object> map);
      
      
      /**
  	 * 查询每个学校招聘的岗位数量
  	 * @param map  查询条件  
  	 * @return  Integer
  	 */	
     public List<ScorePostNumVo> selectPostNum(Map<String,Object> map);
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeTrial>
	 */	
     public List<ScoreGradeTrial> selectAllByScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeTrial
	 */	
     public ScoreGradeTrial selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeTrial
	 */	
     public ScoreGradeTrial selectObjectByScoreGradeTrial(ScoreGradeTrial scoreGradeTrial);
}
