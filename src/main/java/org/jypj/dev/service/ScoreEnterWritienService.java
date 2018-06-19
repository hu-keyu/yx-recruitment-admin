package org.jypj.dev.service;

import org.jypj.dev.entity.ScoreEnterWritien;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterWritien业务接口层
* 统一笔试入围表
* @author
*
*/

public interface ScoreEnterWritienService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterWritien
	 * @return 
	 */	
	public int saveScoreEnterWritienByField(ScoreEnterWritien scoreEnterWritien);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterWritien 
	 * @return 
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
	 * @param id 主键ID
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
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterWritien(Page page,ScoreEnterWritien scoreEnterWritien);
     
     /**
      * 查询进入统一笔试的入围名单
      * @param page
      * @param map
      * @return
      */
     public Page selectWritienEnterPageByMap(Page page,Map<String,Object> map);
    
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
     
     /**
      * 批量插入进入统一笔试名单
      * @param list
      */
     public String addBatchWritien(Map<String, Object> condition,Page page,User user);
     
     /**
      * 调整入围名单
     * @param chk
     * @param projectId
     * @param positionid
     * @param user
     * @param jsonMap
     * @throws Exception
     */
    public void enterlist(String flag,String chks,String projectId,String positionid, User user, JSONObject jsonMap) throws Exception;
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterWritien>
	 */	
     public List<ScoreEnterWritien> selectAllByScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);
     
     /**
      * 查询进入统一笔试的入围名单（发布名单）
      * @param page
      * @param map
      * @return
      */
     public List<ScoreEntersOutVo> selectAllWritien(Map<String,Object> map);
     
     /**按条件查询全部的入围名单(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
      public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterWritien
	 */	
     public ScoreEnterWritien selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterWritien
	 */	
     public ScoreEnterWritien selectObjectByScoreEnterWritien(ScoreEnterWritien scoreEnterWritien);

     /**
      * 统计报表中查询笔试成绩
      * @param page
      * @param condition
      * @return
      */
	public Page selectWritienScore(Page page, Map<String, Object> condition);
	
	/**
     * 统计报表中查询笔试成绩无分页
     * @param page
     * @param condition
     * @return
     */
	public List<ScoreEntersOutVo> selectWritienScore(Map<String, Object> condition);

}
