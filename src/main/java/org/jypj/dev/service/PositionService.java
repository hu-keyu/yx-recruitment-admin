package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Position;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.PositionVo;

import com.alibaba.fastjson.JSONObject;

public interface PositionService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param position
	 * @return 
	 */	
	public int savePositionByField(Position position);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param position 
	 * @return 
	 */	
	public int savePosition(Position position);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePositionById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePositionByObject(Position position);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param position 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePositionByField(Position position);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param position 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePosition(Position position);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Position 
	 */	
    public Position selectPositionById(String id);
    
    /**
 	 * @param map  查询条件  
 	 * @return  List<Position>
 	*/	
    public List<Position> selectByPosition(String year,String themeid);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPosition(Page page,Position position);
    
     /**
   	 * @param map  查询条件  
   	 * @return  List<Position>
   	 */	
      public Position selectProPosName(String projectId, String postName);
     
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public List<Position> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public List<Position> selectAllByPosition(Position position);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Position
	 */	
     public Position selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Position
	 */	
     public Position selectObjectByPosition(Position position);
     
     /**
      * 保存岗位操作
      * @param position
      * @param user
     *  @param jsonMap 
      */
     public void savePositionOper(Position position, User user, JSONObject jsonMap);
     
     /**
      * 删除岗位操作
      * @param id
      */
     public void deletePositionOper(String id);
     
     /**
      * 更新岗位操作
      * @param position
      * @param user
      */
     public void updatePositionOper(Position position, User user);
     
     /**
      * 取消岗位操作
      * @param id
      * @param projectId
      * @param stationId
      * @param user
      * @param jsonMap
      * @throws Exception
      */
	 public void cancelPosition(String id, String projectId, String stationId, User user, JSONObject jsonMap) throws Exception;
	 
	 /**
	  * 启用岗位
	  * @param id
	 * @param projectId 
	  * @param user
	  * @param jsonMap
	  */
	 public void enabledPosition(String id, String projectId, User user, JSONObject jsonMap) throws Exception;

	/**
	 * 审批的时候批量更新审批人数
	 * @param positionList
     */
	void checkBatch(List<Position> positionList);
	 /**
	  * 获取可供考生选择的报考单位
	  * @param map
	  * @return
	  */
	 public List<Position> selectOptionalPosition(Map<String,Object> map);
	 
	/**
	 * 查询有效的岗位信息（岗位是启用的、招聘主题已发布的、已审核的）
	 * @param map
	 * @return
	 */
	public List<Position> queryValidPosition(Map<String,Object> map);

	/**
	 * 查询出某个招聘主题下有多少岗位上报
	 * @param projectId
	 * @return
     */
	public List<Position> selectPostByPorjectId(String projectId) ;
	
   /**
     * 查询出某个招聘主题下有多少岗位上报
     * @param projectId
     * @return
     */
    public List<Position> selectPostByRecruitId(String recruitId) ;

	/**
	 * 查询出某个招聘计划下的某个岗位总共计划招多少人
	 * @param projectId
	 * @param station_id
     * @return
     */
	public int selectApproveCount(String projectId,String station_id) ;
	
	/**
	 * 查询统计-->学科分岗位分页
	 * @param queryParameter
	 * @param page
	 * @return
	 */
	public Page getPositionVoSubjectList(Page page,Map<String,Object> queryParameter);
	
	/**
	 * 查询统计-->单位分岗位分页
	 * @param queryParameter
	 * @param Page page
	 * @return
	 */
	public Page getPositionVoUnitList(Page page,Map<String,Object> queryParameter);
	
	/**
	 * 查询统计-->学科分岗位不分页查所有(导出用)
	 * @param queryParameter
	 * @return
	 */
	public List<PositionVo> queryPositionVoSubjectList(Map<String,Object> queryParameter);
	
	/**
	 * 查询统计-->单位分岗位不分页查所有(导出用)
	 * @param queryParameter
	 * @return
	 */
	public List<PositionVo> queryPositionVoUnitList(Map<String,Object> queryParameter);

	/**
	 *根据岗位id查询对象，用来判断该岗位
	 * @param s
	 * @return
	 */
	int selectByApplyJobId(String s);
}
