package org.jypj.dev.dao;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ScoreEnterNotice;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterNoticedao数据接口层
* 公示表
* @author
*
*/


public interface ScoreEnterNoticeDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterNotice
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterNoticeByField(ScoreEnterNotice scoreEnterNotice);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterNotice 
	 * @return 保存后的对象包括ID
	 */	
	public int saveScoreEnterNotice(ScoreEnterNotice scoreEnterNotice);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteScoreEnterNoticeById(String id);
    
   	/**
	 * 根据对象删除
	 * @param scoreEnterNotice
	 * @return 
	 */	
    public int deleteScoreEnterNoticeByObject(ScoreEnterNotice scoreEnterNotice);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param scoreEnterNotice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterNoticeByField(ScoreEnterNotice scoreEnterNotice);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param scoreEnterNotice 
	 * @return 保存后的对象包括ID
	 */	
    public int updateScoreEnterNotice(ScoreEnterNotice scoreEnterNotice);
    
    /**
     * 批量插入
     * @param list
     */
    public Integer saveNoticesList(@Param(value="list") List<ScoreEnterNotice> list);
    
    /**
	 * 根据项目ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteNoticeByProjectId(@Param(value="projectId")String projectId);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterNotice 
	 */	
    public ScoreEnterNotice selectScoreEnterNoticeById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param scoreEnterNotice 查询对象
	 * @return List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectOnePageByScoreEnterNotice(Page page,ScoreEnterNotice scoreEnterNotice);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectAllByMap(Map<String,Object> map);
     
     /**
      *  批量更新公示入围名单相关信息
     * @param list
     */
     public void updateNoticeEnterList(@Param(value="list") List<ScoreEnterNotice> list);
     
     /**
      * 查询进入公示的入围名单（发布名单）
      * @param page
      * @param map
      * @return
      */
     public List<ScoreEntersOutVo> selectAllNotice(Map<String,Object> map); 
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectAllByScoreEnterNotice(ScoreEnterNotice scoreEnterNotice);
     
     /**按条件查询全部的(导出专用)
   	 * @param map  查询条件  
   	 * @return  List<ScoreEnterPhysical>
   	 */	
     public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterNotice
	 */	
     public ScoreEnterNotice selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterNotice
	 */	
     public ScoreEnterNotice selectObjectByScoreEnterNotice(ScoreEnterNotice scoreEnterNotice);
     
     /**按条件查询全部的入围名单(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
     public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
}
