package org.jypj.dev.service;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.entity.User;
import org.jypj.dev.util.Page;

import com.alibaba.fastjson.JSONObject;

public interface PlanApplyService {
	
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param planApply
	 * @return 
	 */	
	public int savePlanApplyByField(PlanApply planApply);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param planApply 
	 * @return 
	 */	
	public int savePlanApply(PlanApply planApply);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deletePlanApplyById(String id);
    
   	/**
	 * 根据对象删除
	 * @param id 主键ID
	 * @return 
	 */	
    public int deletePlanApplyByObject(PlanApply planApply);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param planApply 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePlanApplyByField(PlanApply planApply);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param planApply 
	 * @return 保存后的对象包括ID
	 */	
    public int updatePlanApply(PlanApply planApply);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return PlanApply 
	 */	
    public PlanApply selectPlanApplyById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<PlanApply>
	 */	
     public Page selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return Page
	 */	
     public Page selectOnePageByPlanApply(Page page,PlanApply planApply);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PlanApply>
	 */	
     public List<PlanApply> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<PlanApply>
	 */	
     public List<PlanApply> selectAllByPlanApply(PlanApply planApply);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PlanApply
	 */	
     public PlanApply selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  PlanApply
	 */	
     public PlanApply selectObjectByPlanApply(PlanApply planApply);

	/**
	 * 批量保存对象
	 * @param list
     */
	 public void saveBatch(List<PlanApply> list) ;

	/**
	 * 通过招聘主题id把这批招聘主题id下面的计划申报学校删除掉
	 * @param id
     */
	void deleteByProjectId(String id);

	Page selectPlanForCheck(Page page, String themeId);
	
	/**
	 * 岗位申报上报
	 * @param planApplyId
	 * @param user
	 * @throws Exception
	 */
	public void saveReport(String planApplyId,User user,JSONObject jsonMap) throws Exception;
	
	/**
	 * 岗位申报撤销
	 * @param planApplyId
	 * @param user
	 * @param jsonMap
	 * @throws Exception
	 */
	public void saveRecall(String planApplyId, User user, JSONObject jsonMap) throws Exception;
	
	/**
	 * 根据学校查询各个招聘年份
	 */
	public List<String> getPlanApplyYears(Map<String,Object> queryParameter);

	/**
	 * 通过id查询到planApply
	 * @param id
	 * @return
     */
	PlanApply selectById(String id);
	
	/**
	 * 面试人员审核
	 * @param chks
	 * @param projectId 
	 * @param reason 
	 * @param isPass 
	 * @param user
	 * @param jsonMap
	 */
	public void checkInterview(String chks, String projectId, String reason, String isPass, User user, JSONObject jsonMap) throws Exception;

	/**
	 * 通过themeid查询到这一批planApply
	 * @param themeId
	 * @return
     */
	public List<PlanApply> findPlanApplyListByThemeId(String themeId) ;
	
	/**
	 * 撤销审核
	 * @param chk
	 * @param projectId
	 * @param user
	 * @param status 
	 * @param reason 
	 * @param jsonMap
	 * @throws Exception
	 */
	public void checkCancel(String chk, String projectId, User user, String status, String reason, JSONObject jsonMap) throws Exception;
	
	
	/**
	 * 查找还没有审核的招聘计划
	 * @param projectId
	 * @return
	 */
	public List<PlanApply> selectUncheckedByprojectId(String projectId) ;
	
	/**
	 * 查找已经审核的招聘计划
	 * @param projectId
	 * @return
	 */
	public List<PlanApply> selectCheckedByprojectId(String projectId) ;
}
