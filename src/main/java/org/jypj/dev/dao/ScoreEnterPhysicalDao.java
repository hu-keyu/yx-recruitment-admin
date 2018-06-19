package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreEnterPhysical;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterPhysicaldao数据接口层
* 体检入围表
* @author
*
*/


public interface ScoreEnterPhysicalDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterPhysical
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterPhysicalByField(ScoreEnterPhysical scoreEnterPhysical);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterPhysical 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterPhysicalById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreEnterPhysical
	 * @return 
	 */	
    public int deleteScoreEnterPhysicalByObject(ScoreEnterPhysical scoreEnterPhysical);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterPhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterPhysicalByField(ScoreEnterPhysical scoreEnterPhysical);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterPhysical 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical);
    
    /**
     * 批量插入
     * @param list
     */
    public Integer savePhysicalsList(@Param(value="list") List<ScoreEnterPhysical> list);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterPhysical 
	 */	
    public ScoreEnterPhysical selectScoreEnterPhysicalById(String id);
    
    /**
     * 根据学科类型和项目ID查询
     * @param map
     * @return
     */
    public List<ScoreEnterPhysical> selectPhysicalById(Map<String,Object> map);
    
    /**
	 * 根据id批量删除
	 * @param idlist
	 */
    public void deletePhysicalBatch(@Param(value="list")List<ScoreEnterPhysical> list);
    
    /**
	 * 查询进入体检的入围名单（调整和删除名单）
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectPhysicalEnterPageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterPhysical 查询对象
	 * @return List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectOnePageByScoreEnterPhysical(Page page,ScoreEnterPhysical scoreEnterPhysical);
     
     /**
  	 * 按条件查询全部的(导出专用)
  	 * @param map  查询条件  
  	 * @return  List<ScoreEnterPhysical>
  	 */	
     public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
     
     /**
   	 * 按条件查询所有岗位并分组(导出专用)
   	 * @param map  查询条件  
   	 * @return  List<ScoreEnterPhysical>
   	 */	
      public List<ScoreEntersOutVo> selectPostCount(Map<String,Object> map);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectAllByMap(Map<String,Object> map);
     
     /**
      *  批量更新体检入围名单相关信息
     * @param list
     */
     public void updatePhysicalEnterList(@Param(value="list") List<ScoreEnterPhysical> list);
     
    /**
     * 查询进入体检的入围名单（发布名单）
     * @param page
     * @param map
     * @return
     */
    public List<ScoreEntersOutVo> selectAllPhysical(Map<String,Object> map);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectAllByScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical);
     
     /**
      * 根据准考证号和姓名还有项目ID查询考生是否存在
     * @param scoreEntersOutVo
     * @return
     */
     public ScoreEntersOutVo selectByStu(ScoreEntersOutVo scoreEntersOutVo);
     
     /**按条件查询全部的入围名单(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
      public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
    
      /**
       * 查询所有名单
      * @param scoreEntersOutVo
      * @return
      */
      public List<ScoreEntersOutVo> selectByStuCondition(ScoreEntersOutVo scoreEntersOutVo);
      
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterPhysical
	 */	
     public ScoreEnterPhysical selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterPhysical
	 */	
     public ScoreEnterPhysical selectObjectByScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical);
}
