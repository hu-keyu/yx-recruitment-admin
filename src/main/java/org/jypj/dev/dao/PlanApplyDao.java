package org.jypj.dev.dao;

import java.util.List;
import java.util.Map;

import org.jypj.dev.entity.PlanApply;
import org.jypj.dev.util.Page;

/**
* PlanApplydao数据接口层
* 招聘计划申报表
* @author
*
*/


public interface PlanApplyDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param planApply
	 * @return 保存后的对象包括ID
	 */	
	public int savePlanApplyByField(PlanApply planApply);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param planApply 
	 * @return 保存后的对象包括ID
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
	 * @param planApply
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
     public List<PlanApply> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param planApply 查询对象
	 * @return List<PlanApply>
	 */	
     public List<PlanApply> selectOnePageByPlanApply(Page page,PlanApply planApply);
    
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
	 * 批量保存
	 * @param list
     */
	void saveBatch(List<PlanApply> list);

	/**
	 * 通过招聘主题id把这批招聘主题id下面的计划申报学校删除掉
	 * @param id
	 */
	void deleteByProjectId(String id);

	/**
	 * 查询审批的
	 * @param page
	 * @param themeId
     * @return
     */
	List<PlanApply> selectPlanForCheck(Page page, String themeId);
	
	/**
	 * 根据学校查询各个招聘年份
	 * @param schoolId
	 * @return
	 */
	public List<String> getPlanApplyYears(Map<String,Object> queryParameter);

	/**
	 * 通过id查询到planApply
	 * @param id
	 * @return
	 */
	PlanApply selectById(String id);

	/**
	 * 通过themeid查询到这一批planApply
	 * @param themeId
	 * @return
	 */
	List<PlanApply> findPlanApplyListByThemeId(String themeId);

	/**
	 * 查找还没有审核的招聘计划
	 * @param projectId
	 * @return
	 */
	public List<PlanApply> selectUncheckedByprojectId(String projectId);

	/**
	 * 查找已经审核的招聘计划
	 * @param projectId
	 * @return
	 */
	public List<PlanApply> selectCheckedByprojectId(String projectId);
}
