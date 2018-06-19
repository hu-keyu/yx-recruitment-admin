package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.LimitPositionVo;

public interface ExamItemsInfoDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param examItemsInfo
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamItemsInfoByField(ExamItemsInfo examItemsInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
	public int saveExamItemsInfo(ExamItemsInfo examItemsInfo);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteExamItemsInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param examItemsInfo
	 * @return 
	 */	
    public int deleteExamItemsInfoByObject(ExamItemsInfo examItemsInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamItemsInfoByField(ExamItemsInfo examItemsInfo);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public int updateExamItemsInfo(ExamItemsInfo examItemsInfo);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return ExamItemsInfo 
	 */	
    public ExamItemsInfo selectExamItemsInfoById(String id);
    
    /**
	 * 查询已删除的编码
	 * @parm num 
	 * @return String 
	 */	
    public String selectIdNum(@Param("num")String num);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public List<ExamItemsInfo> selectOnePageByMap(Page page,Map<String,Object> map);
     
     /**
      * 查询已选岗位
     * @param itemsid
     * @return
     */
     public List<ExamItemsInfo> selectsPerGw(@Param("itemsid")String itemsid,@Param("testid")String testid,@Param("type")String type);
     
     /**
      * 查询试室号是否已经存在
      * @param itemsId
      * @param testNum
      * @return
      */
     public Integer selectCountByNum(@Param("itemsId")String itemsId,@Param("testNum")String testNum);
     
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param examItemsInfo 查询对象
	 * @return List<ExamItemsInfo>
	 */	
     public List<ExamItemsInfo> selectOnePageByExamItemsInfo(Page page,ExamItemsInfo examItemsInfo);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public List<ExamItemsInfo> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public List<ExamItemsInfo> selectAllByExamItemsInfo(ExamItemsInfo examItemsInfo);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamItemsInfo
	 */	
     public ExamItemsInfo selectObjectByMap(Map<String,Object> map);
     
     /**
 	 * 查询最新的考室号
 	 * @return  List<ExamItemsInfo>
 	 */	
      public String selectTestNum(@Param("status")String status,@Param("projectId")String projectId);
      
     /**
   	 * 查询考点
   	 * @return  List<ExamItemsInfo>
   	 */	
      public List<ExamItemsInfo> selectItems(@Param("year")String year,@Param("itemsid")String itemsid);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamItemsInfo
	 */	
     public ExamItemsInfo selectObjectByExamItemsInfo(ExamItemsInfo examItemsInfo);
     
     /**
      * <!-- 查询限制岗位数量（统一笔试） -->
      * @param map
      * @return
      */
     public List<LimitPositionVo> selectLimitPositionNum(@Param("itemsId")String itemsId,
    		 @Param("subject")String subject,@Param("postType")String postType,@Param("jobName")String jobName);
     
     /**
      * <!-- 查询限制岗位数量（统一试讲） -->
      * @param map
      * @return
      */
     public List<LimitPositionVo> selectLimitPositionNumTrial(@Param("itemsId")String itemsId,
    		 @Param("subject")String subject,@Param("postType")String postType,@Param("jobName")String jobName);
}
