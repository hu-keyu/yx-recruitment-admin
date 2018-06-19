package org.jypj.dev.service;

import org.jypj.dev.entity.ScoreEnterPhysical;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterPhysical业务接口层
* 体检入围表
* @author
*
*/

public interface ScoreEnterPhysicalService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterPhysical
	 * @return 
	 */	
	public int saveScoreEnterPhysicalByField(ScoreEnterPhysical scoreEnterPhysical);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterPhysical 
	 * @return 
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
	 * @param id 主键ID
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
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ScoreEnterPhysical 
	 */	
    public ScoreEnterPhysical selectScoreEnterPhysicalById(String id);
    
    /**
	 * 查询进入体检的入围名单
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public Page selectPhysicalEnterPageByMap(Page page,Map<String,Object> map);
     
     /**
      * 调整入围名单
     * @param chk
     * @param projectId
     * @param positionid
     * @param user
     * @param jsonMap
     * @throws Exception
     */
    public void enterPhysicallist(String flag,String chks,String projectId,String positionid, User user, JSONObject jsonMap) throws Exception;
    
    /**
     * 批量插入进入体检的名单
     * @param list
     */
    public String addBatchPhysical(Map<String, Object> condition,Page page,User user);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterPhysical(Page page,ScoreEnterPhysical scoreEnterPhysical);
     
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
     public List<ScoreEnterPhysical> selectAllByMap(Map<String,Object> map);
     
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
     
     /**按条件查询全部的入围名单(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
      public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterPhysical>
	 */	
     public List<ScoreEnterPhysical> selectAllByScoreEnterPhysical(ScoreEnterPhysical scoreEnterPhysical);
     
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
