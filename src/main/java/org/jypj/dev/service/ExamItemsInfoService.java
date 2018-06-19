package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.ExamItemsInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.jypj.dev.vo.LimitPositionVo;

public interface ExamItemsInfoService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param examItemsInfo
	 * @return 
	 */	
	public int saveExamItemsInfoByField(ExamItemsInfo examItemsInfo);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param examItemsInfo 
	 * @return 
	 */	
	public String saveExamItemsInfo(ExamItemsInfo examItemsInfo,User user);
	
	/**
	 * 验证字段是否符合条件(保存)
	 * @param examItemsInfo 
	 * @return 
	 */	
	public String verifyExamItemsInfo(ExamItemsInfo examItemsInfo,User user);
	
	/**
	 * 验证字段是否符合条件（修改）
	 * @param examItemsInfo 
	 * @return 
	 */	
	public String verifyUpdateExamItemsInfo(ExamItemsInfo examItemsInfo,User user);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteExamItemsInfoById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteExamItemsInfoByObject(ExamItemsInfo examItemsInfo);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param examItemsInfo 
	 * @return 保存后的对象包括ID
	 */	
    public String updateExamItemsInfoByField(ExamItemsInfo examItemsInfo,User user);
    
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
   	 * 查询考点
   	 * @return  List<ExamItemsInfo>
   	 */	
      public List<ExamItemsInfo> selectItems(String year,String itemsid);
    
    /**
   	 * 查询已删除的编码
   	 * @parm num 
   	 * @return String 
   	 */	
    public String selectIdNum(String num);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<ExamItemsInfo>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByExamItemsInfo(Page page,ExamItemsInfo examItemsInfo);
    
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
  	 * 查询最新的考室号
  	 * @return  List<ExamItemsInfo>
  	 */	
       public String selectTestNum(String status,String projectId);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamItemsInfo
	 */	
     public ExamItemsInfo selectObjectByMap(Map<String,Object> map);
     
     /**
      * 查询已选岗位
     * @param itemsid
     * @return
     */
     public List<ExamItemsInfo> selectsPerGw(String itemsid,String testid,String type);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  ExamItemsInfo
	 */	
     public ExamItemsInfo selectObjectByExamItemsInfo(ExamItemsInfo examItemsInfo);
     
     /**
      * <!-- 查询限制岗位数量 (统一笔试)-->
      * @param map
      * @return
      */
     public List<LimitPositionVo> selectLimitPositionNum(String itemsId,String subject,String postType,String jobName);
     
     /**
      * <!-- 查询限制岗位数量 （统一试讲）-->
      * @param map
      * @return
      */
     public List<LimitPositionVo> selectLimitPositionNumTrial(String itemsId,String subject,String postType,String jobName);
     
    
}
