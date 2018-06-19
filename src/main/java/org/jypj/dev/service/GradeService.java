package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.Grade;
import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.StudentApplyInfo;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

public interface GradeService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param grade
	 * @return 
	 */	
	public int saveGradeByField(Grade grade);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param grade 
	 * @return 
	 */	
	public int saveGrade(Grade grade);
    
	/**
	 * 批量保存    
	 * 所有字段全都保存
	 * @param gradesList 
	 * @return 保存后的对象包括ID
	 */	
	public Integer saveGradesList(List<Grade> gradesList);
	
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteGradeById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deleteGradeByObject(Grade grade);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param grade 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeByField(Grade grade);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param grade 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGrade(Grade grade);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return Grade 
	 */	
    public Grade selectGradeById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<Grade>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByGrade(Page page,Grade grade);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Grade>
	 */	
     public List<Grade> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<Grade>
	 */	
     public List<Grade> selectAllByGrade(Grade grade);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Grade
	 */	
     public Grade selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  Grade
	 */	
     public Grade selectObjectByGrade(Grade grade);
     
     /**
  	 * 按照分数和成绩查询通过的名单
  	 * @param map  查询条件  
  	 * @return  Grade
  	 */	
     public List<Grade> selectScoreByGrade(Map<String,Object> map);
     
     /**
   	 * 按照分数和成绩查询通过的名单
   	 * @param map  查询条件  
   	 * @return  Grade
   	 */	
     public Integer selectScoreCount(Map<String,Object> map);
     
     /**
      * 导入面试成绩
      * @param file
      * @param projectId
      * @param user
      * @param jsonMap
      */
	 public void saveImportGrade(CommonsMultipartFile file, String projectId, User user, JSONObject jsonMap) throws Exception;
	 
	 /**
	  * 保存学校面试成绩
	  * @param planApply
	  * @param user
	  * @param jsonMap
	  */
	 public void saveGrade(PlanApply planApply, User user, JSONObject jsonMap) throws Exception;
	 
	 /**
	  * 
	  * @param projectId
	  * @param user
	  * @param jsonMap
	 * @throws Exception 
	  */
	 public void savePublishGrade(String projectId, User user, JSONObject jsonMap) throws Exception;
	 
	 /**
	  * 
	  * @param projectId
	  * @param user
	  * @param jsonMap
	  */
	 public void saveCancelPublishGrade(String projectId, User user, JSONObject jsonMap) throws Exception;
	 
	 /**
	  * 查询考生的面试、笔试、试讲成绩
	  * @param grade
	  * @return
	  */
	 public Grade queryScore(StudentApplyInfo sai, String type);
	 
	 /**
      * 查询是否有未录入成绩的考生
      * @param map
      * @return
      */
     public List<Grade> queryHasNoGrade(Map<String,Object> map);

}
