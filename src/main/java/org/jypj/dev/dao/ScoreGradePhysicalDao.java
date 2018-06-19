package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreGradePhysical;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* ScoreGradePhysicaldao数据接口层
* 体检成绩表
* @author
*
*/


public interface ScoreGradePhysicalDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradePhysical
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradePhysicalByField(ScoreGradePhysical scoreGradePhysical);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradePhysicalById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreGradePhysical
	 * @return 
	 */	
    public int deleteScoreGradePhysicalByObject(ScoreGradePhysical scoreGradePhysical);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradePhysicalByField(ScoreGradePhysical scoreGradePhysical);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradePhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
    
    /**
     * 批量插入
     * @param list
     */
    public Integer savePhysicalGradesList(@Param(value="list") List<ScoreGradePhysical> list);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradePhysical 
	 */	
    public ScoreGradePhysical selectScoreGradePhysicalById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
      * 批量更新数据
      * @param studentApplyInfos
      */
     public void updatePhysicalList(@Param(value="list") List<ScoreGradePhysical> list);
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradePhysical 查询对象
	 * @return List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectOnePageByScoreGradePhysical(Page page,ScoreGradePhysical scoreGradePhysical);
    
   /**
    * 
	 * 按条件查询全部的也可以查询通过和不通过的
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectAllByMap(Map<String,Object> map);
     
     /**
      * 
  	 * 按查询全部(id集合)
  	 * @param map  查询条件  
  	 * @return  List<ScoreGradePhysical>
  	 */	
       public List<ScoreGradePhysical> selectPhysicalByMap(Map<String,Object> map);
     
     /**
      * 批量更新数据(导入)
      * @param studentApplyInfos
      */
     public int updatePhysicalBatchs(@Param(value="list") List<ScoreGradePhysical> list);
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradePhysical>
	 */	
     public List<ScoreGradePhysical> selectAllByScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradePhysical
	 */	
     public ScoreGradePhysical selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradePhysical
	 */	
     public ScoreGradePhysical selectObjectByScoreGradePhysical(ScoreGradePhysical scoreGradePhysical);
}
