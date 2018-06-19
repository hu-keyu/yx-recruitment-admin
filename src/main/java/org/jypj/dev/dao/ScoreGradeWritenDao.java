package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreGradeWriten;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* ScoreGradeWritendao数据接口层
* 统一笔试成绩表
* @author
*
*/


public interface ScoreGradeWritenDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreGradeWriten
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeWritenByField(ScoreGradeWriten scoreGradeWriten);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreGradeWritenById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreGradeWriten
	 * @return 
	 */	
    public int deleteScoreGradeWritenByObject(ScoreGradeWriten scoreGradeWriten);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeWritenByField(ScoreGradeWriten scoreGradeWriten);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreGradeWriten 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
    
    /**
     * 批量插入
     * @param list
     */
    public Integer saveGradesList(@Param(value="list") List<ScoreGradeWriten> list);
    
    /**
     * 批量更新数据
     * @param studentApplyInfos
     */
    public void updateWritenList(@Param(value="list") List<ScoreGradeWriten> list);
    
    /**
     * 查询进入笔试的总人数
     * @param map
     * @return
     */
    public Integer selectGradeWritienCount(Map<String,Object> map);
    
    /**
     * 查询过了分数线的考生
     * @param map
     * @return
     */
    public List<ScoreGradeWriten> selectGradeLine(Map<String,Object> map);
    
    /**
     * 批量更新数据(导入)
     * @param studentApplyInfos
     */
    public int updateWritenBatchs(@Param(value="list") List<ScoreGradeWriten> list);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreGradeWriten 
	 */	
    public ScoreGradeWriten selectScoreGradeWritenById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreGradeWriten 查询对象
	 * @return List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectOnePageByScoreGradeWriten(Page page,ScoreGradeWriten scoreGradeWriten);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectAllByMap(Map<String,Object> map);
     
     /**
 	 * 查询全部的(id集合)
 	 * @param map  查询条件  
 	 * @return  List<ScoreGradeWriten>
 	 */	
      public List<ScoreGradeWriten> selectWritenByMap(Map<String,Object> map); 
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreGradeWriten>
	 */	
     public List<ScoreGradeWriten> selectAllByScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeWriten
	 */	
     public ScoreGradeWriten selectObjectByMap(Map<String,Object> map);
     
     /**
      * 查询笔试的试室是否发布完成
     * @param map
     * @return
     */
     public Integer selectLabsCount(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreGradeWriten
	 */	
     public ScoreGradeWriten selectObjectByScoreGradeWriten(ScoreGradeWriten scoreGradeWriten);
}
