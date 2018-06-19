package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.entity.ScoreGradeStudy;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* ScoreGradeStudydao数据接口层
* 考察成绩表
* @author
*
*/


public interface ScoreGradeStudyDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeStudy
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeStudyByField(ScoreGradeStudy scoreGradeStudy);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
	
	/**
     * 批量插入
     * @param list
     */
    public Integer saveStudyGradesList(@Param(value="list") List<ScoreGradeStudy> list);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeStudyById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreGradeStudy
	 * @return 
	 */	
    public int deleteScoreGradeStudyByObject(ScoreGradeStudy scoreGradeStudy);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeStudyByField(ScoreGradeStudy scoreGradeStudy);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeStudy 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
    
    /**
     * 批量更新数据
     * @param studentApplyInfos
     */
    public void updateStudyList(@Param(value="list") List<ScoreGradeStudy> list);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeStudy 
	 */	
    public ScoreGradeStudy selectScoreGradeStudyById(String id);
    
    /**
     * 批量更新数据(导入)
     * @param studentApplyInfos
     */
    public int updateStudyBatchs(@Param(value="list") List<ScoreGradeStudy> list);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradeStudy 查询对象
	 * @return List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectOnePageByScoreGradeStudy(Page page,ScoreGradeStudy scoreGradeStudy);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectAllByMap(Map<String,Object> map);
     
     /**
 	 * 查询全部的(id的集合)
 	 * @param map  查询条件  
 	 * @return  List<ScoreGradeStudy>
 	 */	
      public List<ScoreGradeStudy> selectStudyByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeStudy>
	 */	
     public List<ScoreGradeStudy> selectAllByScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeStudy
	 */	
     public ScoreGradeStudy selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeStudy
	 */	
     public ScoreGradeStudy selectObjectByScoreGradeStudy(ScoreGradeStudy scoreGradeStudy);
}
