package org.jypj.dev.dao;

import org.jypj.dev.entity.GradeAdjustLog;
import org.jypj.dev.util.Page;

import java.util.List;
import java.util.Map;

/**
* GradeAdjustLogdao数据接口层
* 招聘成绩调整日志表
* @author
*
*/


public interface GradeAdjustLogDao {
    
 	/**
	 * 保存    
	 * 字段为空的不存防止覆盖存在默认值的字段
	 * @param gradeAdjustLog
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeAdjustLogByField(GradeAdjustLog gradeAdjustLog);
	
	/**
	 * 保存    
	 * 所有字段全都保存
	 * @param gradeAdjustLog 
	 * @return 保存后的对象包括ID
	 */	
	public int saveGradeAdjustLog(GradeAdjustLog gradeAdjustLog);
    
    /**
	 * 根据ID删除
	 * @param id 主键ID
	 * @return 删除记录数
	 */	
    public int deleteGradeAdjustLogById(String id);
    
   	/**
	 * 根据对象删除
	 * @param gradeAdjustLog
	 * @return 
	 */	
    public int deleteGradeAdjustLogByObject(GradeAdjustLog gradeAdjustLog);
    /**
	 * 更新  
	 * 只更新值不为空的字段
	 * @param gradeAdjustLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeAdjustLogByField(GradeAdjustLog gradeAdjustLog);
    
    /**
	 * 更新  
	 * 更新所有字段
	 * @param gradeAdjustLog 
	 * @return 保存后的对象包括ID
	 */	
    public int updateGradeAdjustLog(GradeAdjustLog gradeAdjustLog);
    
   /**
	 * 按ID查询
	 * @parm id 主键ID
	 * @return GradeAdjustLog 
	 */	
    public GradeAdjustLog selectGradeAdjustLogById(String id);
    
    /**
	 * 分页查询 包含条件
	 * @param page 分页对象
	 * @param map  查询条件  
	 * @return  List<GradeAdjustLog>
	 */	
     public List<GradeAdjustLog> selectOnePageByMap(Page page,Map<String,Object> map);
    /**
	 * 分页查询 包含对象条件
	 * @param page 分页对象
	 * @param gradeAdjustLog 查询对象
	 * @return List<GradeAdjustLog>
	 */	
     public List<GradeAdjustLog> selectOnePageByGradeAdjustLog(Page page,GradeAdjustLog gradeAdjustLog);
    
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeAdjustLog>
	 */	
     public List<GradeAdjustLog> selectAllByMap(Map<String,Object> map);
     
      
   /**
	 * 按条件查询全部的
	 * @param map  查询条件  
	 * @return  List<GradeAdjustLog>
	 */	
     public List<GradeAdjustLog> selectAllByGradeAdjustLog(GradeAdjustLog gradeAdjustLog);
    
	/**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeAdjustLog
	 */	
     public GradeAdjustLog selectObjectByMap(Map<String,Object> map);
     
     /**
	 * 按条件查询单个对象
	 * @param map  查询条件  
	 * @return  GradeAdjustLog
	 */	
     public GradeAdjustLog selectObjectByGradeAdjustLog(GradeAdjustLog gradeAdjustLog);
     
     /**
      * 批量保存成绩调整日志表
      * @param gradeAdjustLog
      */
     public void batchSaveList(List<GradeAdjustLog> gradeAdjustLog);
}
