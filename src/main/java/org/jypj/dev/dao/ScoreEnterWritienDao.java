package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreEnterWritien;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterWritiendao数据接口层
* 统一笔试入围表
* @author
*
*/


public interface ScoreEnterWritienDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterWritien
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterWritienByField(ScoreEnterWritien scoreEnterWritien);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterWritien 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterWritienById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreEnterWritien
	 * @return 
	 */	
    public int deleteScoreEnterWritienByObject(ScoreEnterWritien scoreEnterWritien);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterWritien 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterWritienByField(ScoreEnterWritien scoreEnterWritien);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterWritien 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterWritien 
	 */	
    public ScoreEnterWritien selectScoreEnterWritienById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterWritien 查询对象
	 * @return List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectOnePageByScoreEnterWritien(Page page,ScoreEnterWritien scoreEnterWritien);
    
     /**
      * 批量插入
      * @param list
      */
     public Integer addBatchWritien(@Param(value="list") List<ScoreEnterWritien> list);
     
     /**
      * 查询进入统一笔试的入围名单（调整和删除名单）
      * @param page
      * @param map
      * @return
      */
     public List<ScoreEnterWritien> selectWritienEnterPageByMap(Page page,Map<String,Object> map);
     
     /**
      * 查询进入统一笔试的入围名单（发布名单）
      * @param page
      * @param map
      * @return
      */
     public List<ScoreEntersOutVo> selectAllWritien(Map<String,Object> map);
     
     
     /**按条件查询全部的(导出专用)
  	 * @param map  查询条件  
  	 * @return  List<ScoreEnterPhysical>
  	 */	
     public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectAllByMap(Map<String,Object> map);
     
     /**按条件查询全部的入围名单(导出专用)
	 * @param map  查询条件  
	 * @return  List<ScoreEntersOutVo>
	 */	
     public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
     
     
     /**
      *  批量统一笔试入围名单相关信息
     * @param list
     */
    public void updateEnterList(@Param(value="list") List<ScoreEnterWritien> list);
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectAllByScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterWritien
	 */	
     public ScoreEnterWritien selectObjectByMap(Map<String,Object> map);
     
     
     /**
      * 根据准考证号和姓名还有项目ID查询考生是否存在
     * @param scoreEntersOutVo
     * @return
     */
     public ScoreEntersOutVo selectByStu(ScoreEntersOutVo scoreEntersOutVo);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterWritien
	 */	
     public ScoreEnterWritien selectObjectByScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);
     
     /**
      * 查询所有名单
     * @param scoreEntersOutVo
     * @return
     */
     public List<ScoreEntersOutVo> selectByStuCondition(ScoreEntersOutVo scoreEntersOutVo);
     
     /**
      * 批量保存笔试入围名单表
      * @param map
      */
     public void batchSaveScoreEnterWritien(Map<String , Object> map);
     
     /**
      * 
      * @param scoreEnterWritien
      */
     public void deleteScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);

     /**
      * 统计报表中查询笔试成绩
      * @param page
      * @param condition
      * @return
      */
	public List<ScoreEntersOutVo> selectWritienScore(Page page, Map<String, Object> condition);
	
	/**
     * 统计报表中查询笔试成绩无分页
     * @param page
     * @param condition
     * @return
     */
	public List<ScoreEntersOutVo> selectWritienScore(Map<String, Object> condition);
}
