package org.jypj.dev.service;

import org.jypj.dev.entity.ScoreEnterNotice;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.ScoreEntersOutVo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
* ScoreEnterNotice业务接口层
* 公示表
* @author
*
*/

public interface ScoreEnterNoticeService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param scoreEnterNotice
	 * @return 
	 */	
	public int saveScoreEnterNoticeByField(ScoreEnterNotice scoreEnterNotice);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param scoreEnterNotice 
	 * @return 
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
	 * @param id 主键ID
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
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
      * 调整入围名单
     * @param chk
     * @param projectId
     * @param positionid
     * @param user
     * @param jsonMap
     * @throws Exception
     */
     public void enterNoticelist(String flag, String chks, String projectId, String positionid, User user,
   			JSONObject jsonMap) throws Exception;
     
     /**
      * 批量插入进入入围的名单
      * @param list
      */
     public String addBatchNotice(Map<String, Object> condition,Page page,User user);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByScoreEnterNotice(Page page,ScoreEnterNotice scoreEnterNotice);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectAllByMap(Map<String,Object> map);
     
     /**按条件查询全部的(导出专用)
   	 * @param map  查询条件  
   	 * @return  List<ScoreEnterPhysical>
   	 */	
      public List<ScoreEntersOutVo> selectExportByMap(Map<String,Object> map);
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ScoreEnterNotice>
	 */	
     public List<ScoreEnterNotice> selectAllByScoreEnterNotice(ScoreEnterNotice scoreEnterNotice);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterNotice
	 */	
     public ScoreEnterNotice selectObjectByMap(Map<String,Object> map);
     
     /**按条件查询全部的入围名单(导出专用)
 	 * @param map  查询条件  
 	 * @return  List<ScoreEntersOutVo>
 	 */	
      public List<ScoreEntersOutVo> selectListExportByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ScoreEnterNotice
	 */	
     public ScoreEnterNotice selectObjectByScoreEnterNotice(ScoreEnterNotice scoreEnterNotice);

}
