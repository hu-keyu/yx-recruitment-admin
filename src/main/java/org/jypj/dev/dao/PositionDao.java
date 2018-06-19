package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.Position;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.PositionVo;

/**
* Positiondao数据接口层
* 学校招聘岗位信息表
* @author
*
*/


public interface PositionDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param position
	 * @return 保存后的对象包括ID
	 */	
	public int savePositionByField(Position position);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param position 
	 * @return 保存后的对象包括ID
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
	 * @param position
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
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Position>
	 */	
     public List<Position> selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
 	 * @param map  查询条件  
 	 * @return  List<Position>
 	 */	
      public List<Position> selectByPosition(@Param("year")String year,@Param("themeid")String themeid);
      
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param position 查询对象
	 * @return List<Position>
	 */	
     public List<Position> selectOnePageByPosition(Page page,Position position);
     
     /**
  	 * @param map  查询条件  
  	 * @return  List<Position>
  	 */	
     public Position selectByPosName(Map<String,String> map);
    
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
 	 * 按条件查询岗位数量（发布试讲成绩专用）
 	 * @param map  查询条件  
 	 * @return  List<Position>
 	 */	
      public Integer selectTrailPosCount(@Param(value="projectId")String projectId,@Param(value="postId")String postId);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Position
	 */	
     public Position selectObjectByPosition(Position position);
     
     /**
      * 获取可供考生选择的岗位
      * @param map
      * @return
      */
     public List<Position> selectOptionalPosition(Map<String,Object> map);

	/**
	 * 审批的时候批量更新审批人数
	 * @param positionList
	 */
	void checkBatch(List<Position> positionList);
	
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
	List<Map<String,String>> selectPostByPorjectId(String projectId);

	/**
	 * 查询出某个招聘计划下的某个岗位总共计划招多少人
	 * @param map
	 * @return
	 */
	int selectApproveCount(Map<String, String> map);
	
	/**
	 * 获取招聘项目下的可用的岗位
	 * @param recruitId
	 * @return
	 */
	List<Position> selectPostByRecruitId(String recruitId);
	
	/**
	 * 查询统计-->学科分岗位分页
	 * @param queryParameter
	 * @param page
	 * @return
	 */
	public List<String> getPositionVoIds(Page page,Map<String,Object> queryParameter);
	
	/**
	 * 查询统计-->学科分岗位分页
	 * @param queryParameter
	 * @param page
	 * @return
	 */
	public List<PositionVo> getPositionVoSubjectList(Map<String,Object> queryParameter);
	
	/**
	 * 查询统计-->单位分岗位分页
	 * @param queryParameter
	 * @param Page page
	 * @return
	 */
	public List<PositionVo> getPositionVoUnitList(Map<String,Object> queryParameter);
	
	/**
	 * 查询统计-->单位分岗位分页
	 * @param queryParameter
	 * @return
	 */
	public List<String> getPositionVoUnitIds(Page page,Map<String,Object> queryParameter);
	
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
